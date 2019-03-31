package com.hardtask.testmobarkiya.sharedPreference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by it_ah on 24/03/2019.
 */

public class SharedPrefManager
{
    public static final String SHARED_PREF_NAME = "my_pref";

    public static final String Arabic = "ar";

    public static final String English = "en";

    public static final String Lang = "lang";

    public static final String USER_ID = "id";

    public static final String ISLIKED = "false";

    public static final String BACK_STACK_ROOT_TAG = "root_fragment";

    private static SharedPrefManager mInstance;

    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {

        if (mInstance == null) {

            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }


    public void saveLanguage(String Language)
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Lang,Language);
        editor.apply();
    }

    public void saveUserID (String userId)
    {
        SharedPreferences sh = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sh.edit();
        editor.putString(USER_ID,userId);
        editor.apply();
    }

    public void likeStatus (Boolean isLiked)
    {
        SharedPreferences sh = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sh.edit();
        editor.putBoolean(ISLIKED,isLiked);
        editor.apply();
    }
}
