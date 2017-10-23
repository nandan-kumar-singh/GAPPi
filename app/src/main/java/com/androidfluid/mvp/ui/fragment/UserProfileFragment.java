package com.androidfluid.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidfluid.mvp.R;
import com.androidfluid.mvp.base.BaseFragment;
import com.androidfluid.mvp.mvp.profile.IProfileInteracter;
import com.androidfluid.mvp.mvp.profile.ProfilePresenter;

/**
 * Created by singh on 23/10/17.
 */

public class UserProfileFragment extends BaseFragment<ProfilePresenter> implements IProfileInteracter.View {

    public static UserProfileFragment getInstance() {
        return new UserProfileFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_video_call, container, false);
        return view;
    }

    @Override
    protected ProfilePresenter onCreatePresenter() {
        return new ProfilePresenter(this);
    }

}
