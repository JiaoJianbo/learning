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
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 *
 * 这和 {@link GCOverheadLimitDemo} 有什么不同？
 */
public class HeapOutOfMemoryDemo {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        int i = 0;

        try {
            while (true){
                list.add(new Integer(i++));
            }
        } catch (Throwable e) {
            System.out.println("i=====================" + i);
            e.printStackTrace();
        }
    }
}
