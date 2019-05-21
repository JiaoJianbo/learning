package com.bravo.interview.jvm.gc;

/**
 * @author: Bobby
 *
 * Java中可以作为GC Roots的对象包括下面几种：
 * 1. 虚拟机栈（栈帧中的局部变量区，也叫做局部变量表）中引用的对象。
 * 2. 方法区中的类静态属性引用的对象。
 * 3. 方法区中的常量引用的对象。
 * 4. 本地方法栈中JNI（Native方法）引用的对象。
 */
public class GCRootsDemo {
    private byte[] byteArray = new byte[10 * 1024 * 1024];

    static void m1(){
        GCRootsDemo demo = new GCRootsDemo();
        System.gc();
        System.out.println("GC completed.");
    }

    public static void main(String[] args) {
        m1();
    }
}
