package com.almin.mvpdemo.provider;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;


/**
 * Created by Almin on 2016/1/24.
 */
public class UserProvider {



    public void loadUsers(Callback callback){
//        OkHttpClient mOkHttpClient = new OkHttpClient();
//        final Request request = new Request.Builder()
//                .url("https://www.baidu.com")
//                .build();
//        Call call = mOkHttpClient.newCall(request);
//        call.enqueue(callback);
    }

    public void updateName(String name,Callback callback){
        OkHttpClient mOkHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("https://www.baidu.com")
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(callback);
    }



}
