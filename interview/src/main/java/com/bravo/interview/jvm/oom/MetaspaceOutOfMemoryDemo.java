package com.bravo.interview.jvm.oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author: Bobby
 *
 * 运行前先配置参数：
 * -XX:MetaspaceSize=5m -XX:MaxMetaspaceSize=10m -XX:+PrintGCDetails
 *
 * 错误：
 * java.lang.OutOfMemoryError: Metaspace
 * JDK1.8之前，可能会报 OutOfMemoryError: PermGen space，属于方法区溢出
 *
 *
 * Java8之后的版本使用Metaspace来替代永久代，Metaspace是方法区在HotSpot中的实现，它与永久代最大的区别在于：
 * Metaspace并不在虚拟机内存中而是使用本地内存，也即在java8中，class metaspace（the virtual machines
 * internal presentation of java class），被存储在叫做Metaspace的native memory。
 *
 * 永久代（Java8 后叫作 Metaspace）存放以下信息：
 * 虚拟机加载的类信息
 * 常量池
 * 静态变量
 * 即时编译后的代码
 *
 * 本例借助CGLib直接操作字节码，生成大量的动态类。
 */
public class MetaspaceOutOfMemoryDemo {
    static class OOMObject{
    }

    public static void main(String[] args) {
        int i=0;//模拟多少次后发生异常

        try{
            while (true){
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMObject.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o, args);
                    }
                });

                enhancer.create();
            } // end while
        }catch (Throwable e){
            System.out.println(i+ " 次后发生了异常");
            e.printStackTrace();
        }
    }
}
