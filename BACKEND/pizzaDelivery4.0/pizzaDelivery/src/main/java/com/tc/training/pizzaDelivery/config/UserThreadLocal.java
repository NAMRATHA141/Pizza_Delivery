package com.tc.training.pizzaDelivery.config;
public class UserThreadLocal {
    private static ThreadLocal<String> userThreadLocal = new ThreadLocal<>();

    public static String getUserId() {
        return userThreadLocal.get();
    }

    public static void setUserId(String userId) {
        userThreadLocal.set(userId);
    }

    public static void removeUserId() {
        userThreadLocal.remove();
    }
}
