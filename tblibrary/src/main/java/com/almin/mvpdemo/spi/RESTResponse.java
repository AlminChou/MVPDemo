package com.almin.mvpdemo.spi;

import java.util.List;
import java.util.Map;

/**
 * Created by dgao on 2/15/2016.
 */
public class RESTResponse {
    private int statusCode;
    private Map<String, List<String>> headers;
    private String errorMessage;
    private Exception exception;
    private String responseBodyJsonStr;

    public String getResponseBody() {
        return responseBodyJsonStr;
    }

    public void setResponseBody(String responseBody) {
        this.responseBodyJsonStr = responseBody;
    }



    public RESTResponse(int statusCode, Map<String, List<String>> headers) {
        this.statusCode = statusCode;
        this.headers = headers;
    }

    public RESTResponse(int statusCode, String errorMessage) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }

    public RESTResponse(String errorMessage, Exception exception) {
        if(exception != null && exception.getMessage() != null) {
            this.errorMessage = String.format("%s: %s", errorMessage ,exception.getMessage());
        } else {
            this.errorMessage = errorMessage;
        }
        this.exception = exception;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public Exception getException() {
        return exception;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isErrorFound() {
        return (exception != null || (statusCode != 200 && statusCode != 201));
    }
}
