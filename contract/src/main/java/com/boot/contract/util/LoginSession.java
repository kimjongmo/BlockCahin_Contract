package com.boot.contract.util;


import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import javax.servlet.http.HttpSession;

public class LoginSession {
    public static Long getId(HttpSession session){ return (Long)session.getAttribute("id");} //세션 부분

    public static String getName(HttpSession session){
        return (String)session.getAttribute("userID");
    }

    public static boolean isLogin(HttpSession session){
        return session.getAttribute("userID")==null?false:true;
    }
}
