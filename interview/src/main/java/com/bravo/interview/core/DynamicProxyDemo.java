package com.bravo.interview.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Bobby
 *
 * 动态代理的一个简单例子。
 * 代理类在原始类方法执行前打印一句 “welcome”。
 *
 * 为了让代码结构清晰一些，可以将实现 InvocationHandler 的类和生成代理对象的 bind 方法分开。
 * 参见 proxy 包里面的类
 */
public class DynamicProxyDemo {

    interface IHello {
        void sayHello();
    }

    static class Hello implements IHello {
        @Override
        public void sayHello() {
            System.out.println("hello world");
        }
    }

    // 代理类
    static class DynamicProxy implements InvocationHandler {
        Object originalObj; //有一个成员变量，作为被代理的目标对象

        // 得到一个代理类对象，与被代理类实现了相同的接口
        Object bind(Object origObj) {
            this.originalObj = origObj;

            /* 底层调用 sun.misc.ProxyGenerator.generateProxyClass() 方法类完成生成字节码的动作，
             * 这个方法可以在运行时产生一个描述代理类的字节码 byte[] 数组。
             */
            return Proxy.newProxyInstance(origObj.getClass().getClassLoader(),
                    origObj.getClass().getInterfaces(), this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("welcome");
            return method.invoke(originalObj, args);
        }
    }

    public static void main(String[] args) {
        // 加入这句后，在磁盘中会产生一个名为"$Proxy0.class"的代理Class文件
        //System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        IHello hello = new Hello(); //被代理对象
        IHello helloProxy = (IHello) new DynamicProxy().bind(hello); //代理对象
        helloProxy.sayHello();
    }
}
