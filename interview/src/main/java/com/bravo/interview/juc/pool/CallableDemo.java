package com.bravo.interview.juc.pool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.function.LongFunction;
import java.util.stream.Collectors;

/**
 * @author Bobby
 *
 * Future.get() 方法会阻塞线程，直到计算出结果。一般建议放在最后。
 * 另外还有个 Future.get(long timeout, TimeUnit unit) 设置超时时间。
 */
public class CallableDemo {
    public static void main(String[] args) {
        Worker worker = new Worker(1000000000L);
        FutureTask<Long> task = new FutureTask<>(worker);

        new Thread(task, "AAA").start();

        try {
            long sum = task.get(); // 会阻塞线程，直到计算结束
            System.out.println("sum = " + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("end ................!");
    }

}

class Worker implements Callable <Long>{
    private long end;

    public Worker(long end) {
        this.end = end;
    }

    @Override
    public Long call() throws Exception {
        long sum = 0;
        for (long i = 0; i <= end; i++) {
            sum += i;
        };
        return sum;
    }
}