package com.bravo.interview.core;

/**
 * @author Bobby
 *
 * 注意包装类的 “==” 运算在没有遇到算术运算的情况下不会自动拆箱，而且它们的 equals() 方法不会处理数据转型的关系。
 */
public class AutoWrapDemo {

    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;

        System.out.println(c == d); // true Integer 有个静态内部类 IntegerCache，缓存 -128~127 的数。在这之间的都是同一个对象，除非是自己 new 出来的
        System.out.println(e == f); // false

        System.out.println(c == (a + b)); // true
        System.out.println(c.equals(a+b)); // true

        System.out.println(g == (a + b)); //true?
        System.out.println(g.equals(a+b)); // false。 参考 Long 的 equals 实现，a+b 非 instanceof Long，直接返回 false。

        System.out.println("-------------------");
        int i = 3;
        System.out.println(c == i);
        System.out.println(c.equals(i));

    }
}
