package com.bravo.interview.juc.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Bobby
 *
 */
public class ProducerConsumerBlockQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(10);
        Resource res = new Resource(blockingQueue);

        new Thread(() -> {
            try {
                System.out.println("生产者启动 >>>>>>>>>>>>>>");
                res.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Producer").start();


        new Thread(() -> {
            try {
                System.out.println("消费者启动 >>>>>>>>>>>>>>");
                res.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Consumer").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("--------- 时间到 ---------");
        res.stop();

    }


    static class Resource {
        private volatile boolean flag = true;
        private AtomicInteger resNum = new AtomicInteger(0);

        private BlockingQueue<String> blockingQueue = null;

        public Resource(BlockingQueue<String> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        public void produce() throws InterruptedException {
            String data = null;
            boolean result;
            while(flag){
                data = resNum.incrementAndGet() + "";
                result = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
                if(result) {
                    System.out.println(Thread.currentThread().getName() + " +++> " + data + " 成功。");
                } else {
                    System.out.println(Thread.currentThread().getName() + " +++> " + data + " 超时失败。");
                }

                TimeUnit.MILLISECONDS.sleep(500);
            }
            System.out.println("生产者停止");
        }

        public void consume() throws InterruptedException {
            String data = null;
            while(flag){
                data = blockingQueue.poll(2L, TimeUnit.SECONDS);
                if(data == null || data.equals("")) {
                    flag = false;
                    System.out.println(Thread.currentThread().getName() + " 超时退出。");
                    System.out.println();
                    return;
                }
                System.out.println(Thread.currentThread().getName() + " ---> " + data + " 成功。");
            }
            System.out.println("消费者停止");
        }


        public void stop(){
            this.flag = false;
        }
    }

}
