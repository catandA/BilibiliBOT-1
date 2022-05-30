package com.catand.bilibilibot1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.lang.management.ManagementFactory;

@SpringBootApplication
public class BilibiliBot1Application {

    public static void main(String[] args) {
        SpringApplication.run(BilibiliBot1Application.class, args);
    }
    public static void exitApplication() {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        String pid = name.split("@")[0];
        try {
            Runtime.getRuntime().exec("taskkill /F /PID " + pid);
        } catch (IOException ignored) {
        }
    }
}
