package com.lawencon.linov.outsource.util;

public class CommonUtil {

    public static boolean isEmpty(String s){

        if (s.isEmpty()) {
            return true;
        }

        if (s.equals("")) {
            return true;
        }

        if (s.equalsIgnoreCase("")) {
            return true;
        }

        if (s.length() < 1) {
            return true;
        }

        return true;
    }
}
