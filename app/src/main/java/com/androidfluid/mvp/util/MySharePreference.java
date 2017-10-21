/*
 * Copyright (c) 2017. Nandan.
 */

package com.androidfluid.mvp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class MySharePreference {

    private static final String KEY = "session";
    private static final String USER_NAME = "user_name";

    public static String getUserName(Context context) {
        SharedPreferences savedSession = context.getSharedPreferences(KEY, Context.MODE_PRIVATE);
        return savedSession.getString(USER_NAME, "");
    }

    public static boolean setUserName(Context context, String apiKey) {
        Editor editor = context.getSharedPreferences(KEY, Context.MODE_PRIVATE).edit();
        editor.putString(USER_NAME, apiKey);
        return editor.commit();
    }


    public static void clear(Context context) {
        Editor editor = context.getSharedPreferences(KEY, Context.MODE_PRIVATE)
                .edit();
        editor.clear();
        editor.apply();
    }

}
