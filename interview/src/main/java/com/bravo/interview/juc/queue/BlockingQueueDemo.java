package com.bravo.interview.juc.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Bobby
 *
 * BlockingQueue 接口，继承自 Collection 接口。
 *
 * 为什么需要 BlockingQueue ？
 * 好处是我们不需要关心什么时候需要阻塞线程，什么时候需要唤醒线程，因为这一切 BlockingQueue 都给你包办了。
 *
 * ArrayBlockingQueue 是一个基于数组结构的有界阻塞队列，此队列按FIFO原则对元素进行排序。
 * LinkedBlockingQueue 是一个基于链表结构的阻塞队列，此队列按FIFO排序元素，吞吐量高于ArrayBlockingQueue。
 * SynchronousQueue 一个不存储元素的阻塞队列，每个插入操作必须等到另一个线程调用移出操作，否则插入操作一直处于阻塞状态。
 */
public class BlockingQueueDemo {

    public static void main(String[] args) {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);

//        for (int i = 0; i < 100; i++) {
//
//            final int temp  = i;
//            new Thread(() -> {
//                blockingQueue.add(temp + "");
//            }).start();
//
//            new Thread(() -> {
//                System.out.println(blockingQueue.remove());
//            }).start();
//
//        }

        blockingQueue.add("A");
        blockingQueue.add("B");
        blockingQueue.add("C");

        System.out.println(blockingQueue.element()); //Retrieves, but does not remove, the head of this queue.

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());

        System.out.println("=====================");

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {

                //blockingQueue.add(i + ""); // 空间不够时直接抛异常
                //blockingQueue.offer(i + ""); // 空间不够返回 false，结束
                /*try {
                    blockingQueue.put(i + ""); // 空间不够，阻塞，直到有可用空间
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

                try {
                    // 空间不够，阻塞给定时长，如果仍没有可以空间，返回 false，结束。
                    blockingQueue.offer(i+"", 100, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {

                //System.out.println(blockingQueue.remove());
                //System.out.println(blockingQueue.poll());
                /*try {
                    System.out.println(blockingQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

                try {
                    System.out.println(blockingQueue.poll(100, TimeUnit.MILLISECONDS));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
