package com.hardtask.testmobarkiya.sharedPreference;

import android.content.Context;
import android.content.res.Configuration;

import java.net.ConnectException;
import java.util.Locale;

/**
 * Created by it_ah on 24/03/2019.
 */

public class ChangeLanguage {
    public Context context;

    public ChangeLanguage(Context context) {
        this.context = context;
    }

    public static void Translate(String country, String Language, Context context1, String Saved_Lang) {
        Locale locale = new Locale(Language, country);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context1.getResources().updateConfiguration(config,
                context1.getResources().getDisplayMetrics());

        //save Locale ..,
        SharedPrefManager.getInstance(context1).saveLanguage(Saved_Lang);
    }
}
