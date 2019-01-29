package com.satan.smtranspond.Utils;

import java.util.ArrayList;

/**
 * 类名: ArrayUtil <p>
 * 创建人: YanJ <p>
 * 创建时间: 2018/8/21 9:54 <p>
 * 描述:
 * <p>
 * 更新人: <p>
 * 更新时间: <p>
 * 更新描述: <p>
 */
public class ArrayUtil {


    /**
     * 融合多个字符串数组
     *
     * @param permissions 包含各字符串数组的集合，每个元素E对应一个字符串数组
     * @return
     */
    public static String[] mergeStrArray(ArrayList<String[]> permissions) {
        // TODO Auto-generated method stub
        String[] aa0 = null;
        // tyy 每次都是两个数组合并 所以合并的次数为 collect.size() ，第一个是虚拟的数组
        for (int i = 0; i < permissions.size(); i++) {
            String[] aa1 = (String[]) permissions.get(i);
            String[] newInt = mergeStrArray(aa0, aa1);
            aa0 = newInt;
        }
        return aa0;
    }

    /**
     * 融合两个字符串数组
     * @param aa
     * @param bb
     * @return
     */
    public static String[] mergeStrArray(String[] aa, String[] bb) {
        // TODO Auto-generated method stub
        if (aa == null) {
            return bb;
        }
        String[] collectionInt = new String[aa.length + bb.length];
        for (int i = 0; i < aa.length; i++) {
            collectionInt[i] = aa[i];
        }
        for (int i = aa.length; i < aa.length + bb.length; i++) {
            collectionInt[i] = aa[i - aa.length];
        }
        return collectionInt;
    }
}
