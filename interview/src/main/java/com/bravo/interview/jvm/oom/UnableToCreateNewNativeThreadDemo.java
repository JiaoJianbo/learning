package com.bravo.interview.jvm.oom;

import java.util.concurrent.TimeUnit;

/**
 * @author: Bobby
 *
 * 常见于高并发请求的环境。
 *
 * 错误：
 * Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
 * 准确的讲，该native thread异常与对应的平台有关。在 Windows 下可能看不出效果。
 *
 * 导致原因：
 * 1. 应用创建了太多线程，一个应用进程创建多个线程，超过系统承载极限。
 * 2. 服务器并不允许应用程序创建那么多线程，linux系统默认允许单个进程可以创建的线程数是1024个。
 *
 * 解决办法：
 * 1. 想办法降低应用程序创建线程的数量，分析应用是否真的需要创建那么多线程，如果不是，改代码将线程数降到最低。
 * 2. 对于有的应用，确实需要创建多个线程，远超过linux系统默认的1024个线程的限制，可以通过修改linux服务器配置，扩大linux默认限制。
 *
 * 引申：
 * java.net.SocketException: Too many open files. 异常。文件流未关闭等原因导致...
 */
public class UnableToCreateNewNativeThreadDemo {

    public static void main(String[] args) throws InterruptedException{
        for (int i=0;  ; i++) {
            new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "t-"+i).start();

            System.out.println("i=============" + i);
        }
    }
}
