package com.ax.common.util.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    /**
     * 将时间日期字符串转换成cron表达式
     *
     * @param dateStr
     * @return
     */
    public static String dateStrConvertCronExpression(String dateStr, Integer minute) {
        DateTimeFormatter localDateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime date = LocalDateTime.parse(dateStr, localDateTimeFormat);

        if (minute != null) {
            date = date.plusMinutes(minute);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(date.getSecond()).append(" ").append(date.getMinute()).append(" ").append(date.getHour()).append(" ")
                .append(date.getDayOfMonth()).append(" ").append(date.getMonthValue()).append(" ").append("?");

        return sb.toString();
    }
}
