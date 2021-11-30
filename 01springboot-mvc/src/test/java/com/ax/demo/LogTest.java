package com.ax.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class LogTest {
    @Test
    void test1() {


        log.info("执行时长：{} 毫秒", 1.11);

    }

    @Test
    void test2() {

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(60);

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();

        float seconds = (endTime - startTime) / 1000F;

        System.out.println(Float.toString(seconds) + " seconds.");
    }
}
