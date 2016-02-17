package com.almin.mvpdemo.loaders;

import android.content.Context;

import com.almin.mvpdemo.provider.AbstractNonSyncRESTLoader;
import com.almin.mvpdemo.spi.RESTfulApiClient;
import com.almin.mvpdemo.spi.VendorCategoryApiCallback;

/**
 * Created by dgao on 2/16/2016.
 */
public abstract class VendorApiLoader extends AbstractNonSyncRESTLoader {

    private final static String URL_VENDOR_CATEGORY_LIST_NEW="";

    public VendorApiLoader(Context context, RESTfulApiClient.RESTfulApiCallback callback) {
        super(context, callback);
    }

    public static XOAbstractRESTLoader getVendorCategoryListLoader(Context context, RESTfulApiClient.OnApiCallbackListener onVendorCategoriesListener) {
//        NewVendorProvider provider=null;
//        if(!onRESTLoaderListener.isWifiAvailable()){
//            onVendorCategoriesListener.onError();
//        }else {
        XOAbstractRESTLoader loader = new VendorApiLoader(context, new VendorCategoryApiCallback(null, onVendorCategoriesListener)) {
                @Override
                protected String getURL() {
                    return String.format(URL_VENDOR_CATEGORY_LIST_NEW, "","","");
                }

                @Override
                protected RESTfulApiClient getRESTfulApiClient() {
                    return RESTfulApiClient.getRESTfulGetClient(null);
                }
            };
//        }
        return loader;
    }
}
