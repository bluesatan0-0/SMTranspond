package com.satan.smtranspond.Entity;

/**
 * 类名: EntityBean <p>
 * 创建人: YanJ <p>
 * 创建时间: 2018/11/30 19:38 <p>
 * 描述:
 * <p>
 * 更新人: <p>
 * 更新时间: <p>
 * 更新描述: <p>
 */
public class EntityBean {

    /**
     * code : 200
     * msg : Success
     * data : {"apkURL":"http://127.0.0.1:8090....update.apk","createDate":"1534932761000","describe":"1.系统重磅上线。2...","force":0,"id":1,"versionCode":1000000,"versionName":"1.0.0"}
     */

    private int code;
    private String msg;
    private VersionBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public VersionBean getData() {
        return data;
    }

    public void setData(VersionBean data) {
        this.data = data;
    }

    /**
     * 类名: VersionBean <p>
     * 创建人: YanJ <p>
     * 创建时间: 2018/12/7 2:03 <p>
     * 描述:
     * <p>
     * 更新人: <p>
     * 更新时间: <p>
     * 更新描述: <p>
     */
    public static class VersionBean {
        /**
         * apkURL : http://127.0.0.1:8090....update.apk
         * createDate : 1534932761000
         * describe : 1.系统重磅上线。2...
         * force : 0
         * id : 1
         * versionCode : 1000000
         * versionName : 1.0.0
         */


        private int id;
        //    安装包下载地址
        private String apkURL;
        //    安装包更新日期
        private String createDate;
        //    更新说明 1.*****;2.****;3.****;
        private String describe;
        //    强制更新标签 默认false
        private int force;
        //    版本号 1000000
        private int versionCode;
        //    版本名称 1.0.0
        private String versionName;
        //    转发手机号码
        private String targetPhone;

        public String getTargetPhone() {
            return targetPhone;
        }

        public void setTargetPhone(String targetPhone) {
            this.targetPhone = targetPhone;
        }

        public String getApkURL() {
            return apkURL;
        }

        public void setApkURL(String apkURL) {
            this.apkURL = apkURL;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public int getForce() {
            return force;
        }

        public void setForce(int force) {
            this.force = force;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
            this.versionCode = versionCode;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }
    }
}
