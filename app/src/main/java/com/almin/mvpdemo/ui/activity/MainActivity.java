package com.almin.mvpdemo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.almin.mvpdemo.R;
import com.almin.mvpdemo.ui.fragment.AllUsersFragment;
import com.almin.mvpdemo.ui.fragment.VendorCategoryListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent();
//                intent.setClass(MainActivity.this, MainActivity111.class);
//                startActivity(intent);

            }
        });


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_main, new VendorCategoryListFragment(), null);
        fragmentTransaction.addToBackStack("1111");
        fragmentTransaction.commitAllowingStateLoss();
        getSupportFragmentManager().executePendingTransactions();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        Log.e("","MainActivity----------onStop-");
        super.onStop();
    }

    @Override
    protected void onStart() {
        Log.e("","MainActivity----------onStart-");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.e("","MainActivity----------onRestart-");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.e("","MainActivity----------onDestroy-");
        super.onDestroy();
    }
}
