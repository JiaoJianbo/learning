package com.bravo.interview.jvm.ref;

import java.util.HashMap;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author: Bobby
 *
 * WeakHashMap 基于哈希表实现的Map接口，具有弱键。WeakHashMap中的条目在其键不再正常使用时将自动删除。
 * 常做缓存使用。Mybatis, Redis 中。
 * 更准确地说，给定键的映射的存在并不会阻止被垃圾收集器回收。当一个key被丢弃时，
 * 它的entry将被有效地从map中删除，因此该类的行为与其他映射实现略有不同。
 *
 * 同样这个类也是非同步的，要得到一个同步的WeakHashMap，同样也要Collections.synchronizedMap 方法。
 */
public class WeakHashMapDemo {
    public static void main(String[] args) {
        useHashMap();
        System.out.println("=======================");
        useWeakHashMap();
    }

    private static void useHashMap(){
        HashMap<Integer, String> hashMap = new HashMap<>();
        Integer key = new Integer(1);

        hashMap.put(key, "HashMap");
        System.out.println("hashMap = " + hashMap);

        key = null;
        System.out.println("hashMap = " + hashMap);

        System.gc();
        System.out.println("hashMap = " + hashMap);
    }

    private static void useWeakHashMap(){
        WeakHashMap<Integer, String> weakHashMap = new WeakHashMap<>(); //如果map 的key用String，不能保证被gc
        Integer key = new Integer(2);

        weakHashMap.put(key, "WeakHashMap");
        System.out.println("weakHashMap = " + weakHashMap);

        key = null;
        System.out.println("weakHashMap = " + weakHashMap);

        System.gc();
        System.out.println("weakHashMap = " + weakHashMap);
    }

}
