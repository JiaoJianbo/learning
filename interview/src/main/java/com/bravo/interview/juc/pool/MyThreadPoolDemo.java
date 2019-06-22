package com.bravo.interview.juc.pool;

import java.util.concurrent.*;

/**
 * @author Bobby
 *
 * 由于 Executors 创建的线程池的一些缺陷，推荐使用 ThreadPoolExecutor 自己创建线程池。
 * 可以参考阿里巴巴 Java 开发手册 并发部分。
 *
 * 七大参数：
 * 1.corePoolSize：线程池中的常驻核心线程数。
 * 2.maximumPoolSize：线程池能够容纳同时执行的最大线程数，此值必须大于等于1。
 * 3.keepAliveTime：多余的空闲线程的存活时间。
 * 4.unit: keepAliveTime 的时间单位。
 * 5.workQueue：任务队列，被提交但尚未被执行的任务。
 * 6.threadFactory：表示生成线程池中工作线程的线程工厂，用于创建线程一般用默认的即可。
 * 7.handler：拒绝策略，表示当队列满了并且工作线程大于等于线程池的最大线程数。
 *
 * 线程池底层工作原理：
 * 1.在创建了线程池后，等待提交过来的任务请求。
 * 2.当调用execute（）方法添加一个请求任务时，线程池会做如下判断：
 *   2.1 如果正在运行的线程数量小于corePoolSize，那么马上创建马上创建线程运行这个任务。
 *   2.2 如果正在运行的线程数量大于或等于corePoolSize，那么将这个任务放入队列。
 *   2.3 如果这个时候队列满了且正在运行的线程数量还小于maximumPoolSize，那么还是要创建非核心线程立刻运行这个任务。
 *   2.4 如果队列满了且正在运行的线程数量大于或等于maximumPoolSize，那么线程池会启动饱和拒绝策略来执行。
 * 3.当一个线程完成任务时，它会从队列中取下一个任务来执行。
 * 4.当一个线程无事可做超过一定的时间（keepAlilveTime）时，线程池会判断：
 *   如果当前运行的线程数大于corePoolSize，那么这个线程就被停掉。线程池的所有任务完成后它最终会收缩到corePoolSize的大小。
 *
 * ThreadPoolExecutor 内置的拒绝策略：
 * - AbortPolicy（默认）：直接抛出RejectedExecutionException异常阻止系统正常运行。
 * - CallerRunsPolicy：“调用者运行”一种调节机制，该策略既不会抛弃任务，也不会抛出异常，而是将某些任务回退到调用者，从而降低新任务的流量。
 * - DiscardOldestPolicy：抛弃队列中等待最久的任务，然后把当前任务加入队列中尝试再次提交当前任务。
 * - DiscardPolicy：直接丢弃任务，不予任何处理也不抛出异常。如果允许任务丢失，这是最好的一种方案。
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        
        ExecutorService threadPool = new ThreadPoolExecutor(
                3,
                10,
                60L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy()
        );

        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " is running.");
                });
            }
        } finally {
            threadPool.shutdown();
        }

    }
}
