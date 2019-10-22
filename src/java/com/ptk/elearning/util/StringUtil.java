/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptk.elearning.util;

import java.io.UnsupportedEncodingException;
import javax.xml.bind.DatatypeConverter;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;

public class StringUtil {

    private static final Logger LOGGER = Logger.getLogger(StringUtil.class);

    public static String encodeHex(String s) {

        byte[] b = null;
        String str = "";

        try {
            b = s.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(), e);
        }

        if (b != null) {
            char[] c = Hex.encodeHex(b);
            str = new String(c);
        }

        return str;
    }

    public static String decodeHexAndTrim(String s) {

        String ret = null;

        if (isEmpty(s)) {
            return ret;
        }

        char[] c = s.toCharArray();

        try {
            byte[] b = Hex.decodeHex(c);
            ret = new String(b, "UTF-8");
        } catch (DecoderException | UnsupportedEncodingException e) {
            ret = s;
            LOGGER.warn(e.getMessage(), e);
        }

        ret = ret.trim();

        return ret;
    }

    public static String decodeHex(String s) {

        String ret = null;

        if (isEmpty(s)) {
            return ret;
        }

        char[] c = s.toCharArray();
        try {
            byte[] b = Hex.decodeHex(c);
            ret = new String(b, "UTF-8");
        } catch (DecoderException | UnsupportedEncodingException e) {
            ret = s;
            LOGGER.warn(e.getMessage(), e);
        }

        return ret;
    }

    public static String trim(String s) {
        if (s == null) {
            return null;
        }

        return s.trim();
    }

    public static boolean isEmpty(String s) {
        return s == null || "".equals(s);
    }

    public static boolean isNull(String s) {
        return s == null || "".equals(s) || "null".equals(s);
    }

    public static boolean isBlank(String s) {
        return s == null || "".equals(s.trim());
    }

    public static boolean isEqual(String s1, String s2) {

        if (s1 == null && s2 == null) {
            return true;
        }

        return s1 != null && s1.equals(s2);
    }

    public static String escapeQracleSQL(String field) {
        String strName;
        if (field == null || "".equals(field)) {
            return null;
        }
        strName = field.replaceAll("\\\\", "\\\\\\\\");
        strName = strName.replaceAll("%", "\\\\%");
        strName = strName.replaceAll("_", "\\\\_");
        strName = strName.replaceAll("＿", "\\\\＿");
        return "%" + strName + "%";
    }

    public static String escapeHTML(String str) {
        if (!str.isEmpty()) {

            str = str.replaceAll("&", "&amp;")
                    .replaceAll("<", "&lt;");
            return str;
        } else {
            return null;
        }

    }

    /**
     * decode URL from web
     *
     * @author AnTV
     * @param url from web
     * @return total data TblDeviceFixfire
     */
    public static String decodeURL(String url) {
        if (isNull(url)) {
            return null;
        }
        try {
            url = url.replaceAll("kytudacbiet", "/");
            url = new String(DatatypeConverter.parseBase64Binary(url));
        } catch (Exception ex) {
            LOGGER.error(ex);
            return "decodeFail";
        }
        return url;
    }

    public static String unEscapeHTML(String str) {
        if (!str.isEmpty()) {
            str = str.replaceAll("amp;", "&");
            str = str.replaceAll("lt;", "<");
            str = str.replaceAll("thang", "#");
            str = str.replaceAll("phay", "'");
            return str;
        } else {
            return "";
        }
    }

    public static String formatCellPhone(String cellPhone, String nationCode) {
        if (cellPhone == null) {
            return null;
        }

        String a = cellPhone.trim();

        if (a.startsWith(nationCode)) {
            a = cellPhone.substring(nationCode.length());
        }

        while (a.startsWith("0")) {
            a = a.substring(1);
        }

        return nationCode + a;
    }
    
}
