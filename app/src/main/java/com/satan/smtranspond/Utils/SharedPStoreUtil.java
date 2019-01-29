
package com.satan.smtranspond.Utils;

import android.content.Context;

import com.satan.smtranspond.Profile.AppProfile;

/**
 * 公用的SharedPStore存储信息
 */
public class SharedPStoreUtil {

    /*目标手机 **/
    private static final String Target_PHONE = "CACHE23";//手机

    /*用户手机 **/
    private static final String Loacl_PHONE = "CACHE24";//手机

    private static SharedPStoreUtil instance = null;
    private SharedPStore sharedPStore;

    private SharedPStoreUtil(Context context) {
        if (sharedPStore == null) {
            context = context.getApplicationContext();
            sharedPStore = new SharedPStore(context, null);
        }
    }

    public static SharedPStoreUtil getInstance() {
        if (instance == null) {
            instance = new SharedPStoreUtil(AppProfile.getContext());
        }
        return instance;
    }


    public static String getTarget_PHONE() {
        return getInstance().sharedPStore.getString(Target_PHONE, "");
    }

    public static void setTarget_PHONE(String target_PHONE) {
        getInstance().sharedPStore.putString(Target_PHONE, target_PHONE);
    }

    public static String getLoacl_PHONE() {
        return getInstance().sharedPStore.getString(Loacl_PHONE, "");
    }

    public static void setLoacl_PHONE(String target_PHONE) {
        getInstance().sharedPStore.putString(Loacl_PHONE, target_PHONE);
    }


}
