package com.pig.client;

import com.pig.api.User;
import com.pig.api.UserService;
import com.pig.client.utils.ProxyUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author Mr.Pan
 * @Date 2025/2/19
 **/
public class Main {
    public static void main(String[] args) {
        UserService userService = ProxyUtils.getProxy(UserService.class);

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                User user = userService.getUser(1L);
                System.out.println(user);
            });
        }

    }
}
