package com.rude3knife.commonutils.utils;

import java.util.Locale;

/**
 * 日期时间相关的工具类
 * @author yangzhendong
 */
public class DateTimeUtil {

    /**
     * 格式化秒数时长为 时：分：秒
     * @param seconds 秒数
     * @return
     */
    public static String formatDuration(Integer seconds) {
        String standardTime;
        if (seconds <= 0){
            standardTime = "00:00";
        } else if (seconds < 60) {
            standardTime = String.format(Locale.getDefault(), "00:%02d", seconds % 60);
        } else if (seconds < 3600) {
            standardTime = String.format(Locale.getDefault(), "%02d:%02d", seconds / 60, seconds % 60);
        } else {
            standardTime = String.format(Locale.getDefault(), "%02d:%02d:%02d", seconds / 3600, seconds % 3600 / 60, seconds % 60);
        }
        return standardTime;
    }
}
