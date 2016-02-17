package com.almin.mvpdemo.ui.fragment;

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
import com.almin.mvpdemo.presenter.impl.AllUsersPresenterImpl;
import com.almin.mvpdemo.ui.BaseFragment;
import com.almin.mvpdemo.ui.view.AllUsersView;

import java.util.List;

/**
 * Created by Almin on 2016/1/24.
 */
public class AllUsersFragment extends BaseFragment implements AllUsersView{
    private AllUsersPresenterImpl mPresenter;
    private TextView mTvName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_all_users,container,false);
        mPresenter = new AllUsersPresenterImpl(this);
        mTvName = (TextView) rootView.findViewById(R.id.tv_name);
        Button btnUpdate = (Button) rootView.findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mPresenter.updateUserName(mTvName.getText().toString());
                FragmentActivity activity=getActivity();
                FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container_main, new AllUsersFragment1(), null);
                fragmentTransaction.addToBackStack("1111");
                fragmentTransaction.commitAllowingStateLoss();
                activity.getSupportFragmentManager().executePendingTransactions();

            }
        });


        mPresenter.onCreate();

//        mPresenter.loadUsers("admin","123");
        return rootView;
    }

    @Override
    public void showAllUsers(List<User> users) {
        mTvName.setText(users.get(0).getUsername());
    }


    @Override
    public Presenter getCurrentPresenter() {
        return mPresenter;
    }

    @Override
    public void onDestroyView() {
        Log.e("", "AllUsersFragment--------onDestroyView");
        super.onDestroyView();
    }


    @Override
    public void onDestroy() {
        Log.e("", "AllUsersFragment--------onDestroy");
        super.onDestroy();
    }
}
