package com.almin.mvpdemo.provider;

import com.almin.mvpdemo.BuildConfig;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class BaseApiProvider<T> {

    protected T mService;
    protected static RestAdapter sRestAdapter;
    private static final BeanWrapperConvert sBeanWrapperConvert = new BeanWrapperConvert();

//    protected static RequestInterceptor mRequestInterceptor = new RequestInterceptor() {
//        @Override
//        public void intercept(RequestFacade request) {
//            request.addQueryParam("apikey", ShineConfiguration.getInstance().getShineWebServiceApiKey());
//            request.addHeader("Content-Type", "application/json");
//            request.addHeader("User-Agent",ShineConfiguration.getInstance().getShineWebServiceUserAgent());
//            request.addHeader("CLIENT_APP",ShineConfiguration.getInstance().getShineWebServiceClientApp());
//            request.addHeader("CLIENT_VERSION", BuildConfig.VERSION_NAME);
//            String authToken = UserProfileFactory.readMemberTokenSharedPreferences();
//            request.addHeader("auth_token", authToken);
//        }
//    };
//
//    public BaseApiProvider(Class<T> cls) {
//        if (sRestAdapter == null) {
//            final okHttpClient
//            okHttpClient.setConnectTimeout(ShineConfiguration.getInstance().getConnectTimeout(), TimeUnit.SECONDS);
//            RestAdapter.Builder build = new RestAdapter.Builder();
//            ShineConfiguration configuration = ShineConfiguration.getInstance();
//            build.setEndpoint(String.format("http://%s/%s", configuration.getShineWebServiceHost(),configuration.getShineWebServiceApiVersion()))
//                    .setRequestInterceptor(mRequestInterceptor)
//                    .setConverter(sBeanWrapperConvert)
//                    .setClient(new OkClient())
//                    .setLogLevel(RestAdapter.LogLevel.FULL)
//                    .setErrorHandler(new BaseErrorHandler());
//            sRestAdapter = build.build();
//        }
//        mService = sRestAdapter.create(cls);
//    }

    public static void setRestAdapter(RestAdapter restAdapter) {
        sRestAdapter = restAdapter;
    }

}