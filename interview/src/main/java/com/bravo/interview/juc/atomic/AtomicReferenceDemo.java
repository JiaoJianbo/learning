package com.bravo.interview.juc.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: Bobby
 *
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User user1 = new User("1", "zhangsan", 10);
        User user2 = new User("2", "lisi", 10);

        AtomicReference<User> atomicReference = new AtomicReference<>(user1);
        atomicReference.compareAndSet(user1, user2);
        User user3 = atomicReference.get();
        System.out.println("user3 = " + user3);
    }

}


class User {
    private String id;

    private String name;

    private int age;

    public User(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
