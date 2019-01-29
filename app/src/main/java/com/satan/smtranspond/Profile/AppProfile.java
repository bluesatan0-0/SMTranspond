package com.satan.smtranspond.Profile;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.zhy.http.okhttp.OkHttpUtils;

import okhttp3.OkHttpClient;

/**
 * 类名: AppProfile <p>
 * 创建人: YanJ <p>
 * 创建时间: 2018/8/15 17:03 <p>
 * 描述: App初始化配置
 * <p>
 * 更新人: <p>
 * 更新时间: <p>
 * 更新描述: <p>
 */
public class AppProfile {

    private static final String TAG = "AX1";

    private static Context context;
    private static AppProfile instance;

    public static AppProfile getInstance() {
        if (instance == null) {
            instance = new AppProfile();
        }
        return instance;
    }

    public void onCreate(Application application) {
        this.context = application.getApplicationContext();

//        initToast();
        initLogger();
        initOKhttpUtil();
    }

    /**
     * 初始化第三方toast
     */
//    private void initToast() {
//        Toasty.Config
//                .getInstance()
//                .setInfoColor(getContext().getResources().getColor(R.color.toastBackground))
//                .setTextColor(getContext().getResources().getColor(R.color.white))
//                .apply();
//    }

    /**
     * 初始化日志工具
     */
    private void initLogger() {
        AndroidLogAdapter adapter = new AndroidLogAdapter(
                PrettyFormatStrategy
                        .newBuilder()
                        .methodCount(5)
                        .tag(TAG)
                        .build()
        ) {
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return true;
                // TODO: 2018/8/28  debug/relase状态分辨工具
            }
        };
        Logger.addLogAdapter(adapter);
    }

    /**
     * 初始化OkhttpUtil
     */
    private void initOKhttpUtil() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("AX1 okhttp:"))
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }


    public static Context getContext() {
        return context;
    }
}
