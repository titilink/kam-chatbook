package com.java8.gt.finel;

/**
 * 描述：lambda表达式中的局部变量隐式的为final类型
 *       lambda表达式中的实例变量和静态变量没有这个限制，因为局部变量在线程栈中，而实例变量和静态变量在堆中。
 *
 * @author ganting
 * @date 2018-01-18
 * @since v1.0
 */
public class Finel {

    private String message = "fantastic";

    private void say() {
        String people = "gt"; // lambda表达式中的局部变量隐式的为final类型

        Runnable r1 = () -> System.out.println(message);
        Runnable r2 = () -> System.out.println(people);

        message = "fantastic1";
        Runnable r3 = () -> System.out.println(message);

        r1.run();
        r2.run();
        r3.run();
    }

    public static void main(String[] args) {
        new Finel().say();
    }

}
