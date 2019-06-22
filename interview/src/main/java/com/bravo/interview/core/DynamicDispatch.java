package com.bravo.interview.core;

/**
 * @author Bobby
 *
 * 可以使用 javap 命令输出字节码。
 *
 * 把这种在运行期根据实际类型确定方法执行版本的分派过程称为动态分派。Java 中重写（Override）就是动态分派。
 *
 * Java 语言是一门静态多分派，动态单分派的语言。
 */
public class DynamicDispatch {
    public static void main(String[] args) {
        Human man = new Man();
        Human women = new Women();
        man.sayHello();
        women.sayHello();

        man = new Women();
        man.sayHello();
    }

    static abstract class Human {
        protected abstract void sayHello();
    }

    static class Man extends Human {
        @Override
        protected void sayHello() {
            System.out.println("man say hello");
        }
    }

    static class Women extends Human {
        @Override
        protected void sayHello() {
            System.out.println("women say hello");
        }
    }
}
