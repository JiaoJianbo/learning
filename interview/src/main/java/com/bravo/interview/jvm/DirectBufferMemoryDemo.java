package com.bravo.interview.jvm;

import java.nio.ByteBuffer;

/**
 * @author: Bobby
 *
 * <pre>
 * 运行前先配置参数：
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 *
 * 故障现象：
 * Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
 *
 * 导致原因：
 * 写 NIO 程序时经常用到 ByteBuffer 来读取或写入数据，它可以使用 Native 函数库直接分配堆外内存(使用 Unsafe 类)，
 * 然后通过一个存储在 Java 堆里面的 DirectByteBuffer 对象作为这块内存的引用进行操作。这样能在一些场景中显著提高性能,
 * 因为避免了在 Java 堆和 native 堆之间来回复制对象。
 *
 * ByteBuffer.allocate(capacity) 是分配 JVM 堆内存，属于 GC 管辖范围，由于需要拷贝，所以速度相对较慢。
 *
 * ByteBuffer.allocateDirect(capacity) 是分配 OS 本地内存，不属于 GC 管辖范围，由于不需要拷贝，速度相对较快。
 *
 * 但如果不断地分配本地内存，堆内存很少使用，那么 JVM 就不需要执行 GC，DirectByteBuffer 对象就不会被回收，
 * 这是如果对内存充足，但本地内存有可能已经用完，再次尝试分配本地内存就会出现 OutOfMemoryError，程序直接崩溃。
 * </pre>
 */
public class DirectBufferMemoryDemo {

    static int _10MB = 10 * 1024 * 1024;

    public static void main(String[] args) {
        // 如不指定，默认跟Java堆的最大值（-Xmx）一样。
        System.out.println(sun.misc.VM.maxDirectMemory() / 1024.0 / 1024.0 + "MB");
        ByteBuffer.allocateDirect(_10MB);
    }
}
