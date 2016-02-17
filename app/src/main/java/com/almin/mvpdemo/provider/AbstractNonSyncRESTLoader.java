package com.almin.mvpdemo.provider;

import android.content.Context;
import android.util.Log;

import com.almin.mvpdemo.loaders.XOAbstractRESTLoader;
import com.almin.mvpdemo.spi.RESTResponse;
import com.almin.mvpdemo.spi.RESTfulApiClient;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedTransferQueue;

/**
 * Created by dgao on 2/17/2016.
 */
public abstract class AbstractNonSyncRESTLoader extends XOAbstractRESTLoader {

    RESTResponse mRESTResponse;

    public AbstractNonSyncRESTLoader(Context context, RESTfulApiClient.RESTfulApiCallback callback) {
        super(context, callback);
    }


    @Override
    protected void onStartLoading() {
        Log.e("","loader onStartLoading.-------------");
        if(mRESTResponse!=null){
            try {
                Log.e("","mRESTResponse!=null-------------");
                getRESTfulApiCallback().readResponseBody(mRESTResponse.getStatusCode(),mRESTResponse.getResponseBody());
            } catch (IOException e) {
            } catch (JSONException e) {
            }
        }else {
            super.onStartLoading();
        }
    }

    @Override
    public void deliverResult(RESTResponse response) {
        mRESTResponse=response;
        if(response!=null && response.getResponseBody()!=null){
            try {
                getRESTfulApiCallback().readResponseBody(response.getStatusCode(),response.getResponseBody());
            } catch (IOException e) {

            } catch (JSONException e) {

            }
        }
        super.deliverResult(response);
//        if(response != null && response.getException() != null){
//            mRESTfulApiCallback.onError(response.getErrorMessage());
//        }
    }

//    @Override
//    public RESTResponse loadInBackground() {
//        RESTResponse restResponse=new RESTResponse(200, new HashMap<String, List<String>>());
//        restResponse.setResponseBody("success");
//        return new RESTResponse(200,"");
//    }

    @Override
    protected void onReset() {
        Log.e("","onReset-------------");
        mRESTResponse=null;
        super.onReset();
    }



}
