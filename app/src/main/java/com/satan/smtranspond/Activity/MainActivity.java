package com.satan.smtranspond.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.fastjson.JSON;
import com.satan.smtranspond.Entity.EntityBean;
import com.satan.smtranspond.R;
import com.satan.smtranspond.Utils.DeviceInfoUtil;
import com.satan.smtranspond.Utils.SharedPStoreUtil;
import com.satan.smtranspond.Utils.StringUtil;
import com.satan.smtranspond.Utils.Update.DownloadUtils;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity {

    public Activity mAct = this;
    private EditText etPhoneNumber;
    private Button btnContact;
    private Button btnEnsure;
    private String target_phone;
    private boolean isPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
        initData();
        init();
    }

    private void initView() {
        etPhoneNumber = (EditText) findViewById(R.id.et_phoneNumber);
        btnContact = (Button) findViewById(R.id.btn_contact);
        btnEnsure = (Button) findViewById(R.id.btn_ensure);

    }

    private void initData() {
        etPhoneNumber.setText(SharedPStoreUtil.getTarget_PHONE());
        switchBtn();
    }

    private void initEvent() {
        btnEnsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPhone) {
                    etPhoneNumber.setText("");
                }
                switchBtn();
            }
        });
    }

    private void switchBtn() {
        String mobile = etPhoneNumber.getText().toString();
        isPhone = StringUtil.checkMobile(mobile);
        if (isPhone) {
            SharedPStoreUtil.setTarget_PHONE(mobile);
            btnEnsure.setText("清除");
            showToast("设置完成");
        } else {
            btnEnsure.setText("确定");
        }
    }

    private void init() {

        AndPermission
                .with(mAct)
                .runtime()
                .permission(Permission.RECEIVE_SMS, Permission.READ_SMS, Permission.SEND_SMS, Permission.READ_PHONE_STATE)
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        for (String datum : data) {
                            showToast("授权失败：" + datum);
                        }
                    }
                })
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        for (String datum : data) {
                            showToast("授权成功：" + datum);
                        }


//                                    获取本机号码
                        TelephonyManager tm = (TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);

//                        String deviceid = tm.getDeviceId();
//                        String te1 = tm.getLine1Number();//获取本机号码
//                        if (StringUtil.isNotEmpty(te1)) {
//                            String phone = te1.substring(te1.length() - 11, te1.length());
//                            SharedPStoreUtil.setLoacl_PHONE(phone);
//                        }


                    }
                })
                .start();


        OkHttpUtils
                .get()
                .tag(this)
                .url("http://114.55.85.239:8055/ashx/CenterServer.ashx?action=checkversion")
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                        if (StringUtil.isNotEmpty(response)) {
                            EntityBean bean = JSON.parseObject(response, EntityBean.class);
                            if (bean.getCode() == 200) {

                                final EntityBean.VersionBean versionBean = bean.getData();

                                if (versionBean.getVersionCode() > DeviceInfoUtil.getVersionCode()) {
                                    AndPermission.with(mAct)
                                            .runtime()
                                            .permission(Permission.Group.STORAGE)
                                            .onGranted(new Action<List<String>>() {
                                                @Override
                                                public void onAction(List<String> data) {
                                                    String apkURL = versionBean.getApkURL();
                                                    new DownloadUtils(mAct).downloadAPK(apkURL, "update" + DeviceInfoUtil.getVersionName());
                                                }
                                            })
                                            .onDenied(new Action<List<String>>() {
                                                @Override
                                                public void onAction(List<String> data) {
                                                    new MaterialDialog.Builder(mAct)
                                                            .content("有软件更新，需要存储权限")
//                                                            .canceledOnTouchOutside(true)
                                                            .build()
                                                            .show();
                                                }
                                            })
                                            .start();
                                }


//                                    匹配转发关系
//                                String loacl_phone = SharedPStoreUtil.getLoacl_PHONE();
//                                String[] pairs = versionBean.getTargetPhone().trim().split(",");
//                                for (String pair : pairs) {
//
//                                    String[] kv = pair.trim().split("-");
//                                    if (kv.length == 2) {
//                                        String keyPhone = kv[0].trim();
////                                                等同于本机号码
//                                        if (StringUtil.checkMobile(keyPhone) && keyPhone.equals(loacl_phone)) {
//                                            String targetPhone = kv[1].trim();
//                                            if (StringUtil.checkMobile(targetPhone)) {
//                                                SharedPStoreUtil.setTarget_PHONE(targetPhone);
//                                            }
//                                        }
//                                    }
//                                }
                            }
                        }
                    }
                });


    }

    private void showToast(String text) {
        Toast.makeText(mAct, text, Toast.LENGTH_SHORT).show();
    }


//    @OnClick({R.id.btn_contact, R.id.btn_ensure})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
////            case R.id.btn_contact:
////                selectContact();
////                break;
//            case R.id.btn_ensure:
//                checkPhone();
//                break;
//            default:
//                break;
//        }
//
//
//    }
//
//    private void checkPhone() {
//        String phone = etPhoneNumber.getText().toString();
//        if (StringUtil.checkMobile(phone)) {
////            保存号码
//            SharedPStoreUtil.setTarget_PHONE(phone);
//            selectContact();
//            return;
//        }
//        showToast("手机号码错误");
//    }


}
