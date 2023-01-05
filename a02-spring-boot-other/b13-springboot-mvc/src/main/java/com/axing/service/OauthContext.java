package com.axing.service;

/**
 * @author 公众号：码猿技术专栏
 * @description 用户上下文信息
 */
public class OauthContext {
    private static  final  ThreadLocal<String> loginValThreadLocal= ThreadLocal.withInitial(() -> "tom");

    public static  String get(){
        return loginValThreadLocal.get();
    }
    public static void set(String loginVal){
        loginValThreadLocal.set(loginVal);
    }
    public static void clear(){
        loginValThreadLocal.remove();
    }
}
