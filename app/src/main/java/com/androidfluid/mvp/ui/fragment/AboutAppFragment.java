package com.androidfluid.mvp.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.androidfluid.mvp.R;
import com.androidfluid.mvp.logger.Logger;
import com.androidfluid.mvp.model.Banner;
import com.androidfluid.mvp.ui.adapter.AboutViewPagerAdapter;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by singh on 21/10/17.
 */

public class AboutAppFragment extends DialogFragment {
    public static final String TAG = AboutAppFragment.class.getSimpleName();

    @BindView(R.id.vpAboutApp)
    ViewPager vpAboutApp;
    @BindView(R.id.indicator)
    CirclePageIndicator pageIndicator;
    @BindView(R.id.btnNext)
    Button btnNext;
    @BindView(R.id.butSkip)
    Button btnSkip;


    public static AboutAppFragment getInstance() {
        return new AboutAppFragment();
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setStyle(STYLE_NO_TITLE, android.R.style.Theme_Light_NoTitleBar_Fullscreen);
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_app_description, container, false);
        ButterKnife.bind(this, view);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        List<Banner> bannerList = new ArrayList<>();
        String[] appHeading = getResources().getStringArray(R.array.app_heading);
        String[] appDescription = getResources().getStringArray(R.array.app_description);
        int[] imageSlidesL = {R.drawable.flying_phone, R.drawable.illustration_phone_1, R.drawable.elephant};
        int[] imageSlidesR = {R.drawable.earth, R.drawable.illustration_phone_2, R.drawable.phone_illustration_3};
        Banner banner = null;
        for (int i = 0; i < appHeading.length; i++) {
            banner = new Banner();

            banner.setHeaderText(appHeading[i]);
            banner.setDesctiptionText(appDescription[i]);
            banner.setLImageUrl(imageSlidesL[i]);
            banner.setRImageUrl(imageSlidesR[i]);

            bannerList.add(banner);
        }

        AboutViewPagerAdapter pagerAdapter = new AboutViewPagerAdapter(getContext(), bannerList, vpAboutApp);
        vpAboutApp.setAdapter(pagerAdapter);
        pageIndicator.setViewPager(vpAboutApp);
        vpAboutApp.setOffscreenPageLimit(1);


    }

    @OnClick({R.id.butSkip, R.id.btnNext})
   public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnNext: {
                //vpAboutApp.setCurrentItem(vpAboutApp.getCurrentItem()+1,true);
                Logger.log(TAG,"Onclicked");
                break;
            }
            case R.id.butSkip: {
                Logger.log(TAG,"Onclicked");

                break;
            }
        }
    }
}
