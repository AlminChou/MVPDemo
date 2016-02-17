package com.almin.mvpdemo.loaders;



import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;


import com.almin.mvpdemo.spi.RESTResponse;
import com.almin.mvpdemo.spi.RESTfulApiClient;

import java.util.HashMap;
import java.util.List;

//import org.slf4j.Logger;

public abstract class XOAbstractRESTLoader
extends AsyncTaskLoader<RESTResponse> implements LoaderManager.LoaderCallbacks<RESTResponse> {
//	private Logger mLogger;

	private RESTfulApiClient.RESTfulApiCallback mRESTfulApiCallback;

	public XOAbstractRESTLoader(Context context, RESTfulApiClient.RESTfulApiCallback callback) {
        super(context);
        mRESTfulApiCallback = callback;
	}

//	public void setLogger(Logger logger) {
//		mLogger = logger;
//	}
//
	@Override
	protected void onStartLoading() {
		forceLoad();
	}

	public Bundle getLoaderArguments() {
		return null;
	}
	
	@Override
	public RESTResponse loadInBackground() {
//		RESTResponse restResponse = null;
//		if(XOApplication.isNetworkUnavailable(getContext())) {
//			mRESTfulApiCallback.setLogger(mLogger);
//			restResponse = new RESTResponse("Network is not available", new NetworkNotAvailableException());
//		} else {
//            mRESTfulApiCallback.setLogger(mLogger);
//			restResponse = getRESTfulApiClient().sendRequest(getURL(), mRESTfulApiCallback);
//		}

		Log.e("","loadInBackground-----------");

		RESTResponse restResponse=new RESTResponse(200, new HashMap<String, List<String>>());
		restResponse.setResponseBody("success");

		return restResponse;
	}

    public void cancel() {
        mRESTfulApiCallback.cancel();
    }

	protected RESTfulApiClient.RESTfulApiCallback getRESTfulApiCallback(){
		return mRESTfulApiCallback;
	}

	@Override
	public void deliverResult(RESTResponse response) {
		super.deliverResult(response);
		if(response != null && response.getException() != null){
			mRESTfulApiCallback.onError(response.getErrorMessage());
		}
	}

	protected abstract String getURL();
	protected abstract RESTfulApiClient getRESTfulApiClient();


	@Override
	public Loader<RESTResponse> onCreateLoader(int id, Bundle args) {
		return this;
	}

	@Override
	public void onLoadFinished(Loader<RESTResponse> loader, RESTResponse data) {

	}

	@Override
	public void onLoaderReset(Loader<RESTResponse> loader) {

	}
}
