package com.catand.bilibilibot1;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BotApplication {

    private static final BotApplication instance = new BotApplication();
//    @Autowired
//    private ApplicationContext context;

    public static BotApplication getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        SpringApplication.run(BotApplication.class, args);
    }

    @SneakyThrows
    public void exitApplication(String message) {
//        String name = ManagementFactory.getRuntimeMXBean().getName();
//        String pid = name.split("@")[0];
//        try {
//            Runtime.getRuntime().exec("taskkill /F /PID " + pid); // Support Windows only
//        } catch (IOException ignored) {
//        }

//        SpringApplication.exit(context);

        throw new Exception(message); // 暂时使用throw, 因为主播不会用SpringApplication.exit

    }
}
