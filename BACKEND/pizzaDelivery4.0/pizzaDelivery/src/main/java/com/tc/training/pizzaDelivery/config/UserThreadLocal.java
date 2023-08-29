package com.tc.training.pizzaDelivery.config;
public class UserThreadLocal {
    private static ThreadLocal<Long> userThreadLocal = new ThreadLocal<>();

    public static Long getUserId() {
        return userThreadLocal.get();
    }

    public static void setUserId(Long userId) {
        userThreadLocal.set(userId);
    }

    public static void removeUserId() {
        userThreadLocal.remove();
    }
}
