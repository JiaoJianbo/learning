package com.bravo.interview.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Bobby
 *
 * 运行前先配置参数：
 * -Xms10m -Xmx10m -XX:+PrintGCDetails
 *
 * 错误：
 * java.lang.OutOfMemoryError: GC overhead limit exceeded
 * JDK1.8之前，可能会报 OutOfMemoryError: PermGen space，属于常量池溢出
 *
 *
 * 这和 {@link HeapOutOfMemoryDemo} 有什么不同？
 *
 * GC回收时间长时会抛出OutOfMemoryError。过长的定义是，超过98%的时间用来做GC并且回收了不到2%的堆内存，
 * 连续多次GC都只回收了不到2%的极端情况下才会抛出。
 *
 * 如果不抛出GC overhead limit错误会发生什么情况呢？
 * 那就是GC清理的这么点内存很快会再次填满，迫使GC再次执行，这样就形成恶性循环，CPU使用率一直是100%，而GC缺没有任何成果。
 *
 */
public class GCOverheadLimitDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int i = 0;

        try {
            while (true){
                list.add(String.valueOf(i++).intern());
            }
        } catch (Throwable e) {
            System.out.println("i=====================" + i);
            e.printStackTrace();
        }

    }
}
