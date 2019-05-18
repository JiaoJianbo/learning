package com.bravo.interview.jvm;

import java.util.concurrent.TimeUnit;

/**
 * @author: Bobby
 * <p>
 * Java虚拟机栈（即我们常说的栈）大小用 -Xss512K 大小设置（等同于 -XX:ThreadStackSize=512K），
 * 大小通常在 512K ~ 1024K。可以使用 jinfo -flag ThreadStackSize [Java进程ID] 查看某个Java进程的栈大小。
 * -XX:ThreadStackSize=0，代表使用系统默认值。
 * </p>
 */
public class StackOverflowDemo {

    public static void main(String[] args) {
        StackOverflowDemo demo = new StackOverflowDemo();

        try {
            demo.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length: " + demo.stackLength);
            e.printStackTrace();
        }

        System.out.println("==================================");
        demo.method1();
    }


    private int stackLength = 1;

    public void stackLeak () {
        stackLength ++;
        stackLeak();
    }

    // -------------------------------

    public void method1() {
        method1();
    }

}
