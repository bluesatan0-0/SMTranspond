package com.satan.smtranspond.Utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;
import com.satan.smtranspond.Profile.AppProfile;

/**
 * 类名: DeviceInfoUtil <p>
 * 创建人: YanJ <p>
 * 创建时间: 2018/8/17 9:47 <p>
 * 描述: 获取设备信息
 * <p>
 * 更新人: <p>
 * 更新时间: <p>
 * 更新描述: <p>
 */
public class DeviceInfoUtil {

    private static String TAG = "DeviceInfoUtil";

    /*手机号码**/
//    private static String phoneNum;
//
//    public static String getPhoneNum() {
//        //获取手机号码
//        TelephonyManager tm = (TelephonyManager)getContext().getSystemService(Context.TELEPHONY_SERVICE);
//        String deviceid = tm.getDeviceId();//获取智能设备唯一编号
//        String te1 = tm.getLine1Number();//获取本机号码
//        String imei = tm.getSimSerialNumber();//获得SIM卡的序号
//        String imsi = tm.getSubscriberId();//得到用户Id
//        return phoneNum;
//    }

    /*手机信息**/
    private static String brand;//品牌
    private static String model;//型号
    private static String platform = "Android";//平台
    private static String osVersion;//系统版本
    private static String os ;//系统名
    private static String imei;//imei

    /*app信息**/
    private static String appVersion;
    private static int versionCode;//版本号(101)
    private static String versionName;//版本名 1.0.0
    private static String packageName;//包名
    private static String channel;//渠道号

    private static int appVersionCode;

    public static int getAppVersionCode() {

        return appVersionCode;
    }


    /**
     * 初始化、获取包信息
     *
     * @return
     */
    public static PackageInfo getPi() {
        return getPi(getContext());
    }

    @Nullable
    private static PackageInfo getPi(Context context) {
        try {
            packageName = context.getPackageName();
            PackageInfo packageInfo = context
                    .getPackageManager()
                    .getPackageInfo(packageName, PackageManager.GET_CONFIGURATIONS);

            versionCode = packageInfo.versionCode;
            versionName = packageInfo.versionName;
            appVersion = versionName + "_" + versionCode;

            return packageInfo;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Logger.e(e, e.getMessage(), TAG);
            return null;
        }
    }

    private static Context getContext() {
        return AppProfile.getContext();
    }


//    ————————————————————————————————— 获取信息 ↓ ———————————————————————————————————————————————————————


    public static String getAppVersion() {
        if (appVersion == null) {
            getPi();
        }
        return appVersion;
    }

    public static int getVersionCode() {
        if (versionCode == 0) {
            getPi();
        }
        return versionCode;
    }

    public static String getVersionName() {
        if (versionName == null) {
            getPi();
        }
        return versionName;
    }

    public static String getPackageName() {
        if (packageName == null) {
            getPi();
        }
        return packageName;
    }
}
