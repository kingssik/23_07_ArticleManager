package com.koreaIT.java.AM.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
  // 현재 날짜 시간
  public static String getNowDateStr() {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    Date time = new Date();

    String time1 = format.format(time);

    return time1;
  }
}
