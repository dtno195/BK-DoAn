/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DateUtil {

//    public static String getTimeAfter15Minutes() {
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + 15);
//        cal.set(Calendar.SECOND, 0);
//        cal.set(Calendar.MILLISECOND, 0);
//        String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";
//        SimpleDateFormat dateFormat = new SimpleDateFormat(DATEFORMAT);
//        return dateFormat.format(cal.getTime());
//    }
//    
//    public static String getTimeAfter16Minutes() {
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + 16);
//        cal.set(Calendar.SECOND, 0);
//        cal.set(Calendar.MILLISECOND, 0);
//        String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";
//        SimpleDateFormat dateFormat = new SimpleDateFormat(DATEFORMAT);
//        return dateFormat.format(cal.getTime());
//    }
    public static java.sql.Date getTimeAfter15Minutes() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + 15);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return new java.sql.Date(cal.getTimeInMillis());
    }

    public static java.sql.Date getTimeAfter16Minutes() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + 16);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return new java.sql.Date(cal.getTimeInMillis());
    }
}
