package com.almin.mvpdemo.spi;

import com.almin.mvpdemo.XOConfiguration;
import com.almin.mvpdemo.XOConfigurationException;
import com.almin.mvpdemo.model.MemberProfile;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RESTfulApiClient {
    protected static final String REQUEST_METHOD_GET = "GET";
    protected static final String REQUEST_METHOD_POST = "POST";
    protected static final String REQUEST_METHOD_PUT = "PUT";
    protected static final String REQUEST_METHOD_DELETE = "DELETE";


    public static final int HTTP_STATUS_OK = 200;
    public static final int HTTP_STATUS_OK_CREATE = 201;
    public static final int HTTP_STATUS_UNPROCESSABLE_ENTITY = 422;
    public static final int HTTP_STATUS_BAD_REQUEST = 400;
    public static final int HTTP_STATUS_UNAUTHORIZED = 401;
    public static final int HTTP_STATUS_NOT_FOUND = 404;
    public static final int HTTP_STATUS_REQUEST_TIMEOUT = 408;
    public static final int HTTP_STATUS_INTERNAL_SERVER_ERROR = 500;
    public static final int HTTP_STATUS_SERVICE_UNAVAILABLE = 503;
    public static final int HTTP_STATUS_GATEWAY_TIMEOUT = 504;

    private XOConfiguration xoConfiguration;
    private String requestMethod;

    public static RESTfulApiClient getRESTfulGetClient(XOConfiguration xoConfiguration) {
        return new RESTfulApiClient(xoConfiguration, REQUEST_METHOD_GET);
    }

    public static RESTfulApiClient getRESTfulPostClient(XOConfiguration xoConfiguration) {
        return new RESTfulApiClient(xoConfiguration, REQUEST_METHOD_POST);
    }

    public static RESTfulApiClient getRESTfulPutClient(XOConfiguration xoConfiguration) {
        return new RESTfulApiClient(xoConfiguration, REQUEST_METHOD_PUT);
    }

    public static RESTfulApiClient getRESTDeleteClient(XOConfiguration xoConfiguration) {
        return new RESTfulApiClient(xoConfiguration, REQUEST_METHOD_DELETE);
    }

    private RESTfulApiClient(XOConfiguration xoConfiguration, String requestMethod) {
        this.xoConfiguration = xoConfiguration;
        this.requestMethod = requestMethod;
    }

    public RESTResponse sendRequest(String url, RESTfulApiCallback callback
//            , Logger logger
    ) {
        RESTResponse restResponse = null;
        if(!callback.isCanceled()) {
            HttpURLConnection httpURLConnection = null;
            OutputStream outputStream = null;
            try {
                System.out.println(String.format("(XOAppLibraryDebug) URL: %s", url));
                httpURLConnection = (HttpURLConnection)(new URL(url)).openConnection();
                httpURLConnection.setConnectTimeout(xoConfiguration.getConnectTimeout());
                httpURLConnection.setReadTimeout(xoConfiguration.getConnectTimeout());
                httpURLConnection.setRequestMethod(requestMethod);
                httpURLConnection.setRequestProperty("Accept", "application/json");
                httpURLConnection.setRequestProperty("Content-Type", "application/json");
                callback.setRequestHeader(httpURLConnection);
                if(REQUEST_METHOD_POST.equalsIgnoreCase(requestMethod)
                        || REQUEST_METHOD_PUT.equalsIgnoreCase(requestMethod)) {
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    outputStream = httpURLConnection.getOutputStream();
                    callback.writeRequestBody(outputStream);
                    outputStream.flush();
                    outputStream.close();
                }

                httpURLConnection.connect();
                int statusCode = 0;
                try {
                    statusCode = httpURLConnection.getResponseCode();
                } catch (IOException ioe) {
//                    logger.debug(ioe.getMessage());
                    // in system version of 4.1 ~ 4.3, getResponseCode() will throw a IOException.
                    //so add this code to handle it.
                    statusCode = httpURLConnection.getResponseCode();
                }
                if(!callback.isCanceled()) {
                    switch(statusCode) {
                        case HTTP_STATUS_OK:
                        case HTTP_STATUS_OK_CREATE:
//                            callback.readResponseBody(httpURLConnection.getResponseCode(), readResponseBodyAsString(httpURLConnection.getInputStream()));
                            restResponse = new RESTResponse(httpURLConnection.getResponseCode(), httpURLConnection.getHeaderFields());
                            restResponse.setResponseBody(readResponseBodyAsString(httpURLConnection.getInputStream()));
                            break;
                        case HTTP_STATUS_BAD_REQUEST:
                        case HTTP_STATUS_UNAUTHORIZED:
                        case HTTP_STATUS_NOT_FOUND:
                        case HTTP_STATUS_UNPROCESSABLE_ENTITY:
                        case HTTP_STATUS_REQUEST_TIMEOUT:
                        case HTTP_STATUS_INTERNAL_SERVER_ERROR:
                        case HTTP_STATUS_SERVICE_UNAVAILABLE:
                        case HTTP_STATUS_GATEWAY_TIMEOUT:
//                            callback.readResponseBody(httpURLConnection.getResponseCode(), readResponseBodyAsString(httpURLConnection.getErrorStream()));
                            String responseMessage = null;
                            try {
                                responseMessage = httpURLConnection.getResponseMessage();
                            } catch(IOException ioe) {
                                responseMessage = "Unknown error";
                            }
//                            logger.debug(String.format("(XOAppLibraryDebug) error: %s", responseMessage));
                            restResponse = new RESTResponse(statusCode, String.format("Error returned from API: %s", responseMessage));
                            break;
                    }
                }
            } catch(MalformedURLException mfurle) {
                restResponse = new RESTResponse("Incorrect RESTful API URL", new XOConfigurationException(String.format("Invalid URL for RESTful API call: %s", url)));
//                logger.debug(mfurle.getMessage());
            } catch(JSONException jsone) {
//                restResponse = new RESTResponse("Error in reading JSON response data", jsone);
//                logger.debug(jsone.getMessage());
            } catch(IOException ioe) {
                restResponse = new RESTResponse("Error in calling RESTful API call", ioe);
//                logger.debug(ioe.getMessage());
            } finally {
                if(outputStream != null) {
                    try {
                        outputStream.close();
                    } catch(IOException ioe) {
                        // no harm to ignore exception here
                    }
                }
                if(httpURLConnection != null) {
                    httpURLConnection.disconnect();
                    httpURLConnection = null;
                }
            }
        }

        return restResponse;
    }

    private static String readResponseBodyAsString(InputStream inStream)
            throws IOException {
        StringBuilder responseBody = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inStream));
            String line = null;
            while((line = bufferedReader.readLine()) != null) {
                responseBody.append(line);
            }
        } finally {
            if(bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch(IOException ioe) {
                    // no harm to ignore error here
                }
            }
        }

        return responseBody.toString();
    }

    public static abstract class RESTfulApiCallback {
//        private static Logger DEFAULT_LOGGER = new XOLogger();
        private static final String COMPARE_TEXT_HELPER = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
        private MemberProfile memberProfile;
//        private Logger logger;
        private boolean isCanceled;
        protected OnApiCallbackListener onApiCallbackListener;

        public RESTfulApiCallback() {
//            logger = DEFAULT_LOGGER;
        }

//        protected Logger getLogger() {
//            return logger;
//        }

//        public void setLogger(Logger logger) {
//            this.logger = logger;
//        }

        public void cancel() {
            isCanceled = true;
        }

        public boolean isCanceled() {
            return isCanceled;
        }

        public void writeRequestBody(OutputStream outputStream)
                throws IOException, JSONException {
        }

        public abstract void readResponseBody(int statusCode, String jsonStr)
                throws IOException, JSONException;

        public void onError(String message){}

        public RESTfulApiCallback(MemberProfile memberProfile, OnApiCallbackListener listener) {
            this.memberProfile = memberProfile;
            onApiCallbackListener = listener;
        }

        public RESTfulApiCallback(OnApiCallbackListener listener){
            onApiCallbackListener = listener;
        }

        protected MemberProfile getMemberProfile() {
            return memberProfile;
        }

        protected void setRequestHeader(HttpURLConnection httpURLConnection) {
            if(memberProfile != null) {
                httpURLConnection.setRequestProperty(memberProfile.getTokenName(), memberProfile.getTokenValue());
            }
        }

        public static boolean isTextEmptyOrNull(String text) {
            return text == null || text.isEmpty() || "null".equalsIgnoreCase(text);
        }

        public static String getSortNum(String name) {
            StringBuilder sb = new StringBuilder();
            if ("".equals(name)) {
                sb.append("99");
            } else {
                char[] cList = name.toCharArray();
                for (char c1: cList) {
                    int index = COMPARE_TEXT_HELPER.indexOf(c1);
                    if (index == -1) {
                        sb = new StringBuilder("98");
                        break;
                    }
                    if (index < 10) {
                        sb.append("0");
                    }
                    sb.append(index);
                }
            }
            return sb.toString();
        }
    }

    public static abstract class OnApiCallbackListener<T> {
        public abstract void onSuccess(T t);
        public void onProgressUpdate(int progress){};
        public void onError(String message){};
        public abstract void onFailure(String reason);
    }
}
