package com.bravo.interview.core;

/**
 * 静态分派
 * @author: Bobby
 *
 * <code>Human man = new Man();</code>
 * Human 称为变量的静态类型（Static Type）或外观类型（Apparent Type），Man 是变量的实际类型（Actual Type）。
 * 静态类型在编译期是可知的，而实际类型在运行时才可以确定，编译器在编译时并不知道一个对象的实际类型是什么。
 *
 * 由于编译器在重载时是通过参数的静态类型（而不是实际类型）作为判定依据的。并且静态类型是编译器可知的，
 * 所以在编译阶段，Javac 编译器就根据参数的静态类型决定使用哪个重载版本。
 * 所以选择了 sayHello(Human) 作为调用目标，并把这个方法的符号引用写到 main() 方法里的两条 invokevirtual 指令的参数中。
 *
 * {@link OverloadDemo}
 */
public class StaticDispatch {

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();

        StaticDispatch sd = new StaticDispatch();
        sd.sayHello(man);
        sd.sayHello(woman);
    }

    static class Human {}
    static class Man extends Human {}
    static class Woman extends Human {}

    public void sayHello(Human guy) {
        System.out.println("Hello, guy!");
    }
    public void sayHello(Man guy) {
        System.out.println("Hello, gentleman!");
    }
    public void sayHello(Woman guy) {
        System.out.println("Hello, lady!");
    }
}
