package com.satan.smtranspond.Profile;

import android.app.Application;

/**
 * 类名: MyApplication <p>
 * 创建人: YanJ <p>
 * 创建时间: 2018/11/30 17:52 <p>
 * 描述:
 * <p>
 * 更新人: <p>
 * 更新时间: <p>
 * 更新描述: <p>
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppProfile.getInstance().onCreate(this);
    }

}
