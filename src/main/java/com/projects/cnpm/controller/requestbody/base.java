package com.projects.cnpm.controller.requestbody;

public class base {

    public static  Boolean chuoi_toan_so(String str){
        if (str == null || str.isEmpty()) {
            return false; 
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false; 
            }
        }
        return true; 
    }
}
