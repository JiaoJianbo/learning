package com.bravo.interview.juc;

/**
 * @author: Bobby
 *
 * 线程安全的单例模式 -- DCL + volatile
 *
 * DCL（双端检锁）机制不一定线程安全，原因是有指令重排序的存在，加入volatile可以禁止指令重排。
 * 当一条线程访问instance不为null时，由于instance实例未必已初始化完成，也就造成了线程安全问题。
 * 所以要解决指令重排，还需要再加 volatile 关键字。
 *
 * 可以不要再使用Double-Checked Locking了: http://www.importnew.com/23980.html
 *
 * 因为 synchronization 的规则能保证所有在 monitorexit 之前的动作都能够生效,
 * 而并不能保证在 monitorexit 之后的动作在 monitorexit 之前不生效。
 * 也就是我们能够保证在退出内部同步块之前 SingletonDemo 能够被实例化，不能保证 instance 被赋值一定发生在退出同步块之后，
 * 因此同样会出现没有被构造完的 SingletonDemo 实例被其他线程引用并访问。
 *
 * 原文：
 * The rules for synchronization don't work that way. The rule for a monitorexit (i.e., releasing synchronization)
 * is that actions before the monitorexit must be performed before the monitor is released. However,
 * there is no rule which says that actions after the monitorexit may not be done before the monitor is released.
 */
public class SingletonDemo {
    private static volatile SingletonDemo instance = null;

    private SingletonDemo (){
        System.out.println(Thread.currentThread().getName() + " construct SingletonDemo");
    }

    // 使用了双端检锁 DCL（Double-Checked  Locking），不推荐直接使用 DCL。
    public static SingletonDemo getInstance(){
        if(instance == null){
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    // 不加 synchronized 时，运行程序，看构造方法被执行了几次。
    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                SingletonDemo.getInstance();
            }).start();

        }
    }
}
