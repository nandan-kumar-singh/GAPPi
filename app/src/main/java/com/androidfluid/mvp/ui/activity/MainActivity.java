/*
 * Copyright (c) 2017. Nandan.
 */

package com.androidfluid.mvp.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.androidfluid.mvp.R;
import com.androidfluid.mvp.base.BaseActivity;
import com.androidfluid.mvp.model.Contact;
import com.androidfluid.mvp.model.Gank;
import com.androidfluid.mvp.mvp.main.IMainInteracter;
import com.androidfluid.mvp.mvp.main.MainPresenter;
import com.androidfluid.mvp.ui.adapter.DrawerListAdapter;
import com.androidfluid.mvp.ui.fragment.AboutAppFragment;
import com.androidfluid.mvp.ui.fragment.UserProfileFragment;
import com.anthonycr.grant.PermissionsManager;
import com.anthonycr.grant.PermissionsResultAction;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity<MainPresenter>
        implements IMainInteracter.View {

    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    RecyclerView rvDrawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        mPresenter.showAppDescription(this);
        initViews();
        requestAllPermission();
        String permission = Manifest.permission.READ_CONTACTS;
        if(ActivityCompat.checkSelfPermission(this, permission)
                == PackageManager.PERMISSION_GRANTED) {
            mPresenter.getAllContact(this);
        }
    }

    private void initViews() {

        /*Observable.interval(new Random().nextInt(10), TimeUnit.SECONDS)
                .timeInterval()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TimeInterval<Long>>() {
                    @Override
                    public void call(TimeInterval<Long> longTimeInterval) {
                        Toast.makeText(MainActivity.this, "Time: " + longTimeInterval, Toast.LENGTH_SHORT).show();
                    }
                });*/
        View headerLayout = navigationView.getHeaderView(0);
        rvDrawer = headerLayout.findViewById(R.id.rvDrawer);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });


    }

    @Override
    protected MainPresenter onCreatePresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void onSucceed(Gank data) {

        Toast.makeText(this, "The request was successful", Toast.LENGTH_SHORT).show();
        List<Gank.Result> results = data.getResults();

        for (Gank.Result result : results) {
            Log.e(TAG, result.toString());
        }
    }

    @Override
    public void onFail(String err) {
        Log.e(TAG, err);
        Toast.makeText(this, "Request failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideDialog() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void goToAppDescription() {
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        AboutAppFragment aboutAppFragment = AboutAppFragment.getInstance();
        aboutAppFragment.show(fragmentManager, "about-app");
        aboutAppFragment.setCancelable(false);
    }

    @Override
    public void showProfileFragment(Contact contact) {
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, UserProfileFragment.getInstance());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();    }

    @Override
    public void updateContactList(List<Contact> contactList) {
        rvDrawer.setLayoutManager(new LinearLayoutManager(this));
        rvDrawer.setNestedScrollingEnabled(false);
        rvDrawer.setHasFixedSize(true);

        rvDrawer.setAdapter(new DrawerListAdapter(this, contactList, new DrawerListAdapter.OnClickListener() {
            @Override
            public void onClick(Contact contact, int position) {
                showProfileFragment(contact);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        }));
    }

    void requestAllPermission(){
        PermissionsManager.getInstance().requestAllManifestPermissionsIfNecessary(this,
                new PermissionsResultAction() {
                    @Override
                    public void onGranted() {
                        mPresenter.getAllContact(MainActivity.this);
                    }

                    @Override
                    public void onDenied(String permission) {
                        // Notify the user that you need all of the permissions
                    }
                });
    }
}
