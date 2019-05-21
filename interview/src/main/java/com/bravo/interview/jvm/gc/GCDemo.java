package com.bravo.interview.jvm.gc;

import java.util.Random;

/**
 * @author: Bobby
 *
 * 配置使用不同的垃圾收集器，查看各自的执行情况。一些配置是相互激活的，如果配置了新生代的，那么老年代的相关收集器会被激活，
 * 如果配置了老年代的，那么新生代也会使用相关收集器。通常，没有必要同时配置两个。
 *
 * 1.  Serial + Serial Old
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC  (DefNew + Tenured)
 *
 * 2.  ParNew + Serial Old （即将废弃）
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC  (ParNew + Tenured)
 *
 * Java HotSpot(TM) 64-Bit Server VM warning:
 * Using the ParNew young collector with the Serial old collector is deprecated
 * and will likely be removed in a future release
 *
 * 3. Parallel Scavenge + Parallel Old (JDK1.8 默认，不配置默认使用这种)
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC  (PSYoungGen + ParOldGen)
 *
 * 4. 跟上配置 3 一样，新生代默认会使用 Parallel Scavenge，老年代使用 Parallel Old
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelOldGC  (PSYoungGen + ParOldGen)
 *
 * 5. ParNew + CMS +  Serial Old (保底)
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC  (par new generation + concurrent mark-sweep generation)
 *
 * 6. （已经废弃）
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialOldGC
 * Server模式下，在 JDK1.5 及以前，配合 Parallel Scavenge + Serial Old。之后用来配合 CMS 一起使用。
 *
 * 7. G1
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseG1GC   (garbage-first)
 *
 */
public class GCDemo {
    public static void main(String[] args) {
        String s = "sssssssssssssssss";

        try {
            while (true){
                s += s + new Random(100000000L);
                s.intern();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
