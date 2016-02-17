package com.almin.mvpdemo.provider;

import com.almin.mvpdemo.loaders.VendorApiLoader;
import com.almin.mvpdemo.loaders.XOAbstractRESTLoader;
import com.almin.mvpdemo.spi.RESTfulApiClient;

import java.util.List;

/**
 * Created by dgao on 2/17/2016.
 */
public class VendorCategoryProvider extends BaseDataProvider{
    XOAbstractRESTLoader mVendorCategoryListLoader;

    public VendorCategoryProvider(LoaderManagerHelper loaderManagerHelper) {
        super(loaderManagerHelper);
    }

    public void getVendorCategoryList(RESTfulApiClient.OnApiCallbackListener<List<String>> onApiCallbackListener){
        mVendorCategoryListLoader=VendorApiLoader.getVendorCategoryListLoader(getLoaderManagerHelper().getContext(), onApiCallbackListener);
        initLoader(mVendorCategoryListLoader);
    }


    public void refreshVendorCategoryList(){
        restartLoader(mVendorCategoryListLoader);
    }


}
