package com.bravo.interview.core;

/**
 * @author Bobby
 *
 * 类加载的时机：
 *
 * 类从加载到虚拟机内存中开始，到卸载出内存为止，整个生命周期包括：加载、验证、准备、解析、初始化、使用、卸载 七个阶段。
 * 其中，验证、准备、解析三个部分统称为连接。加载、验证、准备、初始化和卸载这五个阶段的顺序是确定的，而解析阶段则不一定，某些情况下可以在初始化之后再开始。
 *
 * “有且只有”以下四中情况必须立即对类进行初始化（而加载、验证、准备自然需要在此之前开始）
 * 1.遇到 new, getstatic, putstatic, invokestatic 这四个字节码指令时。如果类没有进行过初始化，则需先触发其初始化。
 *   生成这四个指令的常见的 Java 代码场景是：使用 new 关键字实例化对象时、读取或设置一个类的静态字段（被 final 修饰静态字段除外，
 *   已在编译期就把结果放入了常量池）的时候，以及调用一个类的静态方法的时候。
 * 2.使用 java.lang.reflect 包的方法对类进行反射调用的时候，如果类没有进行过初始化，则需先触发其初始化。
 * 3.当初始化一个类的时候，如果发现其父类还没有进行过初始化，则需先触发其父类的初始化。但是接口并不会。
 * 4.当虚拟机启动时，用户需要指定一个要执行的主类（包含 main() 方法的那个类），虚拟机会先初始化这个类。
 */
public class ClassInitialization {
    public static void main(String[] args) {
        // 对于静态字段，只有直接定义这个字段的类才会被初始化。因此通过子类引用父类的静态字段，不会导致子类初始化。
//        System.out.println(SubClass.value);

        // 子类初始化时，如果父类没有进行过初始化，则需要 先触发其父类的初始化
//        System.out.println(SubClass.value2);

        // 通过数组定义来应用类，不会触发此类的初始化。创建动作由字节码指令 newarray 触发。
//        SuperClass[] sca = new SuperClass[10];
//        System.out.println("sca = " + sca);

        // 常量在编译阶段会存入调用类的常量池，本质上没有直接引用到定义常量的类，因此不会触发定义常量的类的初始化。
        // 即，对 ConstClass.HELLOWORLD 的引用实际都被转化为 ClassInitialization 类对自身常量池的引用了。
        System.out.println(ConstClass.HELLOWORLD);
    }

    static class SuperClass {
        static {
            System.out.println("SuperClass init!");
        }

        public static int value = 123;
    }

    static class SubClass extends SuperClass {
        static {
            System.out.println("SubClass init!");
        }

        public static int value2 = 456;
    }

    static class ConstClass {
        static {
            System.out.println("ConstClass init!");
        }

        public static final String HELLOWORLD = "hello world";
    }
}
