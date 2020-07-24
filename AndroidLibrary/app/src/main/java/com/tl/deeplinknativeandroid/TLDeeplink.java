package com.tl.deeplinknativeandroid;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import com.unity3d.player.UnityPlayerActivity;

public class TLDeeplink extends UnityPlayerActivity {

    private String DEEP_LINK_URL_KEY = "deeplink";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent =
                com.unity3d.player.UnityPlayer.currentActivity.getIntent();
        if (intent != null && intent.getAction() == Intent.ACTION_VIEW) {
            SaveUrl(intent);
        }
    }

    private void SaveUrl(Intent intent) {
        Uri uri = intent.getData();
        if (uri != null)
            SaveStringToPlayerPrefs(uri.toString(), DEEP_LINK_URL_KEY);
    }

    public static void SaveStringToPlayerPrefs(String value, String key) {
        Context appContext =
                com.unity3d.player.UnityPlayer.currentActivity.getApplicationContext();
        SharedPreferences prefs =
                appContext.getSharedPreferences(appContext.getPackageName() +
                        ".v2.playerprefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefs.edit();
        prefEditor.putString(key, value);
        prefEditor.commit();
    }

}