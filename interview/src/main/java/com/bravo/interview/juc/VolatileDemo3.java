package com.bravo.interview.juc;

/**
 * @author: Bobby
 *
 * 多线程环境中线程交替执行，由于编译器优化重排的存在，两个线程中使用的变量能否保证一致性是无法确定的，结果无法预测。
 *
 * 在多线程环境下，一个线程执行method1，经过指令重排，可能 语句① 和 语句② 的顺序会颠倒，
 * 当有一个线程刚执行完 语句②，另一个线程执行method2，那么此时 count 的值仍为0。
 */
public class VolatileDemo3 {
    private int count = 0;
    private boolean flag = false;

    public void method1() {
        count = 1; //语句①
        flag = true; //语句②
    }

    public void method2() {
        if(flag){
            count += 5; //语句③
            System.out.println("count = " + count);
        }
    }

}
