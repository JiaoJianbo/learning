package com.bravo.interview.core;

/**
 * @author Bobby
 *
 * 1. 如果 try 语句块中出现属于 Exception 或其子类的异常，则转到 catch 语句块处理（finally 块仍然会被执行）。
 * 2. 如果 try 语句块中出现不属于 Exception 或其子类的异常，则转到 finally 语句块处理。
 * 3. 如果 catch 语句块出现任何异常，则转到 finally 语句块处理。
 */
public class ExceptionHandleDemo {
    public static void main(String[] args) {
        ExceptionHandleDemo demo = new ExceptionHandleDemo();
        
        int result = demo.inc();
        System.out.println("result = " + result);
    }

    public int inc() {
        int x;

        try {
            x = 1;

            String[] arr = {"1", "2"};
            System.out.println(arr[3]); // will throw an exception

//            return x; //没有异常时，在这里返回 1。如果有异常，这里的代码就不能被执行。
        } catch (Exception e) {
            System.out.println("catch block ...");
            x = 2;
            return x;
        } finally {
            System.out.println("finally block ...");
            x = 3;
        }

        //没有异常时，这里返回 3；有异常时，finally 块仍然被执行，但是返回 2（finally 块中没有return）。
        return x;
    }

}
