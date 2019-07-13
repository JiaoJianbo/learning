package com.bravo.interview.core;

import java.math.BigDecimal;

/**
 * BigDecimal 的常见操作
 *
 * @author Bobby
 * @since 2019/7/12
 */
public class BigDecimalDemo {

    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }

    /**
     * Java中进行浮点数运算的时候，会出现丢失精度的问题
     *
     */
    public static void test1() {
        System.out.println(0.05 + 0.01); // 0.060000000000000005
        System.out.println(1.05 - 0.45); // 0.6000000000000001
        System.out.println(4.015 * 100); // 401.49999999999994
        System.out.println(123.3 / 100); // 1.2329999999999999

    }

    /**
     * BigDecimal(double)存在精度损失风险，在精确计算或值比较的场景中可能会导致业务逻辑异常。
     * 优先推荐入参为 String 的构造方法，或使用 BigDecimal 的valueOf方法，此方法内部其实执行了
     * Double的toString，而Double的toString按double的实际能表达的精度对尾数进行了截断。
     *
     * 具体可参考 JDK 中 BigDecimal(double) 方法的注释和注意事项：
     * 1.The results of this constructor can be somewhat unpredictable. 这是因为有些数不能准确地表示为double。
     * 2.The String constructor, on the other hand, is perfectly predictable。
     * 3.When a double must be used as a source for a BigDecimal, use the static valueOf(double) method.
     */
    public static void test2(){
        System.out.println(new BigDecimal(0.05).add(new BigDecimal(0.01))); // 0.06000000000000000298372437868010820238851010799407958984375
        System.out.println(new BigDecimal("0.05").add(new BigDecimal("0.01"))); // 0.06
        System.out.println(new BigDecimal(0.05)); // 0.05000000000000000277555756156289135105907917022705078125
        System.out.println(BigDecimal.valueOf(0.05)); // 0.05
        System.out.println(new BigDecimal(Double.toString(0.05))); // 0.05
    }

    /**
     * BigDecimal 的舍入方式
     */
    public static void test3() {
        BigDecimal n1 = new BigDecimal(1);
        BigDecimal n2 = new BigDecimal(3);

        //精度是(this.scale() - divisor.scale())，如果是无限小数，抛出 ArithmeticException
        //System.out.println(n1.divide(n2));

        // 舍入远离零的舍入模式。始终对非零舍弃部分前面的数字加 1。
        System.out.println(n1.divide(n2,2, BigDecimal.ROUND_UP)); // 0.34

        // 接近零的舍入模式。从不对舍弃部分前面的数字加1，即截短
        System.out.println(n1.divide(n2,2, BigDecimal.ROUND_DOWN)); // 0.33

        // 接近正无穷大的舍入模式。如果 BigDecimal 为正，则舍入行为与 ROUND_UP 相同；为负，则舍入行为与 ROUND_DOWN 相同。
        System.out.println(n1.divide(n2,2, BigDecimal.ROUND_CEILING)); // 0.34

        // 接近负无穷大的舍入模式。如果 BigDecimal 为正，则舍入行为与 ROUND_DOWN 相同；为负，则舍入行为与 ROUND_UP 相同。
        System.out.println(n1.divide(n2,2, BigDecimal.ROUND_FLOOR)); // 0.33

        // “四舍五入”，如果舍弃部分 >= 0.5，则舍入行为与 ROUND_UP 相同;否则舍入行为与 ROUND_DOWN 相同。
        System.out.println(n1.divide(n2,2, BigDecimal.ROUND_HALF_UP)); // 0.34

        // “五舍六入”，如果舍弃部分 > 0.5，则舍入行为与 ROUND_UP 相同;否则舍入行为与 ROUND_DOWN 相同。
        System.out.println(n1.divide(n2,2, BigDecimal.ROUND_HALF_DOWN)); // 0.33

        /*
         * 向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则向相邻的偶数舍入。
         * 如果舍弃部分左边的数字为奇数，则舍入行为与 ROUND_HALF_UP 相同;
         * 如果为偶数，则舍入行为与 ROUND_HALF_DOWN 相同。
         * 注意，在重复进行一系列计算时，此舍入模式可以将累加错误减到最小。
         * 此舍入模式也称为“银行家舍入法”，主要在美国使用。四舍六入，五分两种情况。
         * 如果前一位为奇数，则入位，否则舍去。
         *
         * 比如保留小数点1位，那么这种舍入方式下的结果：
         * 1.15->1.2, 1.25->1.2
         */
        System.out.println(n1.divide(n2,2, BigDecimal.ROUND_HALF_EVEN)); // 0.33

        // 断言所请求的操作具有精确的结果，因此不需要舍入。如果在产生不精确结果，则会抛出 ArithmeticException。
        // Exception in thread "main" java.lang.ArithmeticException: Rounding necessary
        System.out.println(n1.divide(n2,2, BigDecimal.ROUND_UNNECESSARY));
    }

}
