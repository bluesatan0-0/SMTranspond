package com.satan.smtranspond.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类名: TimeUtil <p>
 * 创建人: YanJ <p>
 * 创建时间: 2018/8/23 15:05 <p>
 * 描述:
 * <p>
 * 更新人: <p>
 * 更新时间: <p>
 * 更新描述: <p>
 */
public class TimeUtil {

    public static String parseDateFormart(long date, String formart) {
        return parseDateFormart(new Date(date), formart);
    }

    public static String parseDateFormart(Date date, String formart) {
        if (date == null || StringUtil.isEmpty(formart)) {
            return "Error parseDateFormart";
        }
        SimpleDateFormat df = new SimpleDateFormat(formart);
        return df.format(date);
    }

}
