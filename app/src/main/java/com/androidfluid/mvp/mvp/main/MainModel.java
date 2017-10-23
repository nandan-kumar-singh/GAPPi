/*
 * Copyright (c) 2017. Nandan.
 */

package com.androidfluid.mvp.mvp.main;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.androidfluid.mvp.R;
import com.androidfluid.mvp.api.ApiEngine;
import com.androidfluid.mvp.model.Contact;
import com.androidfluid.mvp.model.Gank;
import com.androidfluid.mvp.rx.RxSchedulers;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Nandan on 2016/10/30.
 */

public class MainModel implements IMainInteracter.Model {

    @Override
    public Observable<Gank> getGank() {

        return ApiEngine.getInstance().getApiService()
                .getGank("1")
                .compose(RxSchedulers.<Gank>switchThread());
    }

    @Override
    public Observable<Boolean> shouldShowAppDescription(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.app_description), Context.MODE_PRIVATE);

        return Observable.just(preferences.getBoolean(context.getString(R.string.app_description), true));
    }

    @Override
    public Observable<List<Contact>> getAllContact(Context ctx) {
        return Observable.just(getContact(ctx)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    List<Contact> getContact(Context context) {
        List<com.androidfluid.mvp.model.Contact> list = new ArrayList<>();
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                if (cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor cursorInfo = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id}, null);
                    //InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(context.getContentResolver(),
                    ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, new Long(id));

                    Uri person = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.valueOf(id));
                    Uri pURI = Uri.withAppendedPath(person, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);

                    //Bitmap photo = null;
                    // if (inputStream != null) {
                    //    photo = BitmapFactory.decodeStream(inputStream);
                    // }
                    while (cursorInfo.moveToNext()) {
                        com.androidfluid.mvp.model.Contact info = new com.androidfluid.mvp.model.Contact();
                        info.id = id;
                        info.name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        info.mobileNumber = cursorInfo.getString(cursorInfo.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        //info.photo = photo;
                        info.photoURI = pURI;
                        list.add(info);
                    }

                    cursorInfo.close();
                }
            }
            cursor.close();
        }
        return list;
    }
}
