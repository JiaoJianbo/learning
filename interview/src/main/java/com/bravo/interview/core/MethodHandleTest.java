package com.bravo.interview.core;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * @author Bobby
 *
 * java.lang.invoke 包在 JDK1.7 新加入的，在 1.8 又做了一些补充。包含一些由 Java 核心库和 JVM 提供的对动态语言的支持。
 *
 */
public class MethodHandleTest {
    static class ClassA {
        public void println(String s){
            System.out.println("[ClassA::println]" + s);
        }
    }

    private static MethodHandle getPrintlnMethodHandle(Object receiver) throws Exception {
        //MethodType: “方法类型”。包含了方法的返回值类型（methodType()的第一个参数）和具体参数类型（methodType()的第二个及以后的参数）
        MethodType mt = MethodType.methodType(void.class, String.class);

        /*
         * lookup()方法来自于 MethodHandles.lookup()，这句的作用是在指定类中查找符合给定的方法名称、方法类型，并且符合调用权限的方法句柄
         *
         * 因为这里调用的是一个虚方法，按照Java语言的规定，方法的第一个参数是隐式的，代表该方法的接收者，也即是this指向的对象，这个参数以前是放在
         * 参数列表中进行传递的，而现在提供了bindTo()方法来完成这件事情
         */
        return lookup().findVirtual(receiver.getClass(), "println", mt).bindTo(receiver);
    }

    public static void main(String[] args) throws Throwable {
        Object obj = System.currentTimeMillis() % 2 == 0 ? System.out : new ClassA(); // obj 可能会是 System.out 对象或者是 ClassA 的对象
        getPrintlnMethodHandle(obj).invokeExact("MethodHandleTest"); // 调用 obj 的 println 方法
    }
}
