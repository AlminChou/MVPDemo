package com.almin.mvpdemo.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.almin.mvpdemo.R;
import com.almin.mvpdemo.model.User;
import com.almin.mvpdemo.presenter.Presenter;
import com.almin.mvpdemo.presenter.VendorCategoryListPresenter;
import com.almin.mvpdemo.presenter.impl.AllUsersPresenterImpl;
import com.almin.mvpdemo.presenter.impl.CategoryListPresenterImpl;
import com.almin.mvpdemo.ui.BaseFragment;
import com.almin.mvpdemo.ui.view.AllUsersView;
import com.almin.mvpdemo.ui.view.VendorCategoriesView;

import java.util.List;

public class VendorCategoryListFragment extends BaseFragment implements VendorCategoriesView{
    private VendorCategoryListPresenter mPresenter;
    private TextView mTvName;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_all_users,container,false);

        mTvName = (TextView) rootView.findViewById(R.id.tv_name);
        Button btnUpdate = (Button) rootView.findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mPresenter.updateUserName(mTvName.getText().toString());
                FragmentActivity activity = getActivity();
                FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container_main, new AllUsersFragment(), null);
                fragmentTransaction.addToBackStack("1111");
                fragmentTransaction.commitAllowingStateLoss();
                activity.getSupportFragmentManager().executePendingTransactions();

            }
        });

        Button btnRefresh = (Button) rootView.findViewById(R.id.btn_refresh);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.refreshCategoryList();
            }
        });


//        mPresenter.onCreate();

//        mPresenter.loadUsers("admin","123");
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("","onActivityCreated------------------");
        if(mPresenter==null) {
            mPresenter = new CategoryListPresenterImpl(this, this);
            mPresenter.loadCategoryList();
        }else{
            Log.e("","loader does not call, it will be started automatically");
        }
    }

    @Override
    public Presenter getCurrentPresenter() {
        return mPresenter;
    }

    @Override
    public void onStart() {
        Log.e("","onStart------------------");
        //revert data and revert UI.
        super.onStart();
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    @Override
    public void showCategoryList(List<String> list) {
        Log.e("","update listview------------------"+list.toString());
    }
}
