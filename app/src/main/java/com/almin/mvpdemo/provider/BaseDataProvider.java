package com.almin.mvpdemo.provider;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.util.Log;

import com.almin.mvpdemo.loaders.XOAbstractRESTLoader;

/**
 * Created by dgao on 2/17/2016.
 */
public class BaseDataProvider {

    LoaderManagerHelper mLoaderManagerHelper;

    public LoaderManagerHelper getLoaderManagerHelper() {
        return mLoaderManagerHelper;
    }

    public BaseDataProvider(LoaderManagerHelper loaderManagerHelper){
        mLoaderManagerHelper=loaderManagerHelper;
    }

    public interface LoaderManagerHelper{
        LoaderManager getCurrentLoaderManager();
        Context getContext();
    }

    protected void initLoader(XOAbstractRESTLoader xoAbstractRESTLoader){
        Log.e("","xoAbstractRESTLoader.getId()---------"+xoAbstractRESTLoader.getId());
        mLoaderManagerHelper.getCurrentLoaderManager().initLoader(xoAbstractRESTLoader.getId(),xoAbstractRESTLoader.getLoaderArguments(),xoAbstractRESTLoader);
    }



    protected void restartLoader(XOAbstractRESTLoader xoAbstractRESTLoader){
        //loader.restart may not work if we do not change the arguments.
        if(mLoaderManagerHelper.getCurrentLoaderManager().getLoader(xoAbstractRESTLoader.getId())!=null) {
            xoAbstractRESTLoader.cancelLoadInBackground();
            xoAbstractRESTLoader.reset();
            xoAbstractRESTLoader.startLoading();
        }
    }
}
