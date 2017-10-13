/*
 * Copyright (c) 2017. Nandan.
 */

package com.androidfluid.mvp.api;

import java.io.IOException;

import com.androidfluid.mvp.App;
import com.androidfluid.mvp.util.NetUtil;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;



public class NetworkInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        //No cache is used when there is no network
        if (!NetUtil.isConnected(App.getContext())) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }

        Response response = chain.proceed(request);

        if (NetUtil.isConnected(App.getContext())) {
            // When there is a network, set the timeout to 0
            int maxStale = 0;
            response.newBuilder()
                    .header("Cache-Control", "public, max-age=" + maxStale)
                    .removeHeader("Pragma")// Clear the header information, because if the server does not support, will return some interference information, do not clear the following can not take effect
                    .build();
        } else {
            // When there is no network, set the timeout to 3 weeks
            int maxStale = 60 * 60 * 24 * 21;
            response.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .removeHeader("Pragma")
                    .build();
        }

        return response;
    }

}
