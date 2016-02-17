package com.almin.mvpdemo.spi;

import com.almin.mvpdemo.model.MemberProfile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dgao on 2/16/2016.
 */
public class VendorCategoryApiCallback extends RESTfulApiClient.RESTfulApiCallback{
//    private RESTfulApiClient.OnApiCallbackListener mOnVendorCategoriesListener;

    public VendorCategoryApiCallback(MemberProfile xoMemberProfile, RESTfulApiClient.OnApiCallbackListener onApiCallbackListener){
        super(xoMemberProfile,onApiCallbackListener);
//        mOnVendorCategoriesListener = onApiCallbackListener;
    }

//    @Override
//    public void readResponseBody(HttpURLConnection httpURLConnection) throws IOException, JSONException {
//        String responseString = readResponseBodyAsString(httpURLConnection);
//        getLogger().debug(String.format(" (PlannerDebug) Response: %s", responseString));
//        if(mOnVendorCategoriesListener != null) {
//            JSONObject jsonResponse = new JSONObject(responseString);
//            JSONArray array = jsonResponse.optJSONArray("categories");
//            int length = array.length();
//            List<VendorCategoryBase> categorylist = new ArrayList<VendorCategoryBase>();
//            for (int i = 0; i < length; i++) {
//                JSONObject object = array.optJSONObject(i);
//                VendorCategoryNew vendorCategoryNew = new VendorCategoryNew(object);
//                categorylist.add(vendorCategoryNew);
//            }
//            mOnVendorCategoriesListener.onSuccess(categorylist);
//        }
//    }

//    @Override
//    public String readResponseError(HttpURLConnection httpURLConnection) throws IOException, JSONException {
//        if(mOnVendorCategoriesListener != null) {
//            mOnVendorCategoriesListener.onError();
//        }
//        return null;
//    }


    @Override
    public void onError(String message) {
        onApiCallbackListener.onError(message);
        super.onError(message);
    }

    @Override
    public void readResponseBody(int statusCode, String jsonStr) throws IOException, JSONException {
        List<String> list=new ArrayList<>();
        list.add("1111111");
        list.add("2222222");
        list.add("3333333");
        onApiCallbackListener.onSuccess(list);
    }
}
