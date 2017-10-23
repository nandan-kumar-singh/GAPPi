package com.androidfluid.mvp.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidfluid.mvp.R;
import com.androidfluid.mvp.model.Banner;
import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

/**
 * Created by Nandan on 12/4/17.
 */

public class AboutViewPagerAdapter extends PagerAdapter implements View.OnClickListener {
    private static final String TAG = AboutViewPagerAdapter.class.getSimpleName();
    private Context context;
    private List<Banner> bannerList = Collections.EMPTY_LIST;
    private ViewPager viewPager;

    public AboutViewPagerAdapter(Context context, List<Banner> promotionalBanners,ViewPager viewPager) {
        this.context = context;
        if (bannerList == null || bannerList.isEmpty())
            this.bannerList = promotionalBanners;
        this.viewPager=viewPager;
    }

    @Override
    public int getCount() {
        return bannerList.size();
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        final ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.layout_about_app_template, container, false);

        ((TextView) layout.findViewById(R.id.tvHeader)).setText(bannerList.get(position).getHeaderText());
        ((TextView) layout.findViewById(R.id.tvDescription)).setText(bannerList.get(position).getDesctiptionText());

        //((TextView) layout.findViewById(R.id.tv_banner_description)).setText(bannerList.get(position).getBannerDescription());

        Glide.with(context).asBitmap().load(bannerList.get(position).getLImageUrl()).into(((ImageView) layout.findViewById(R.id.ivLeft)));
        Glide.with(context).asBitmap().load(bannerList.get(position).getRImageUrl()).into(((ImageView) layout.findViewById(R.id.ivRight)));

        container.addView(layout);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                layout.findViewById(R.id.ivBackground).setScrollX(positionOffsetPixels);
                layout.findViewById(R.id.ivLeft).setScrollX(positionOffsetPixels);
                layout.findViewById(R.id.ivRight).setScrollY(positionOffsetPixels);

                com.androidfluid.mvp.logger.Logger.log(TAG,positionOffsetPixels+"");
                if(position==1){
                    layout.findViewById(R.id.iv_lines).setVisibility(View.VISIBLE);
                }else {
                    layout.findViewById(R.id.iv_lines).setVisibility(View.INVISIBLE);

                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return layout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void onClick(View v) {
    }
}

