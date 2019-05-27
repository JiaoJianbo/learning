package com.bravo.interview.core;

import java.io.Serializable;
import java.sql.SQLOutput;

/**
 * @author: Bobby
 *
 * 重载的版本并不是唯一的，往往只能确定一个“更加合适的”版本。
 *
 * 自动类型转换顺序 char -> int -> long -> float -> double
 * 自动装箱到Character，如果装箱之后还是找不到装箱类，但是找到了装箱类实现的接口类型。
 * 那么就再自动转型为它实现的接口或者父类（由低到高）。可变长参数的重载优先级最低。
 */
public class OverloadDemo {

    // 从上到下依次注掉各个方法，体会“确定一个更加合适的版本”的过程
    public static void main(String[] args) {
        sayHello('a');
    }


    public static void sayHello(char arg) {
        System.out.println("Hello char");
    }

    public static void sayHello(int arg) {
        System.out.println("Hello int");
    }

    public static void sayHello(long arg) {
        System.out.println("Hello long");
    }

    public static void sayHello(Character arg) {
        System.out.println("Hello Character");
    }

    public static void sayHello(Serializable arg) {
        System.out.println("Hello Serializable");
    }

    public static void sayHello(Object arg) {
        System.out.println("Hello Object");
    }

    public static void sayHello(char... arg) {
        System.out.println("Hello char...");
    }

}
