package com.boot.contract.util;


import javax.servlet.http.HttpSession;

public class LoginSession {
    public static String getName(HttpSession session){
        return (String)session.getAttribute("userID");
    }

    public static boolean isLogin(HttpSession session){
        return session.getAttribute("userID")==null?false:true;
    }
}
