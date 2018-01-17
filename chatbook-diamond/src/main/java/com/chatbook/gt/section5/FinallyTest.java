package com.chatbook.gt.section5;

import org.junit.Test;

/**
 * 不要在finally代码块中处理返回值。有可能会出现以下两种“诡异”现象：
 *   1、覆盖了try子句中的返回值
 *   2、屏蔽了异常
 * <p>
 *
 * 1、按照我们程序员的惯性认知：当遇到return语句的时候，执行函数会立刻返回。
 *    但是，在Java语言中，如果存在finally就会有例外。除了return语句，
 *    try代码块中的break或continue语句也可能使控制权进入finally代码块。
 * 2、请勿在try代码块中调用return、break或continue语句。万一无法避免，一定要确保finally的存在不会改变函数的返回值。
 * 3、函数返回值有两种类型：值类型与对象引用。对于对象引用，要特别小心，
 *    如果在finally代码块中对函数返回的对象成员属性进行了修改，
 *    即使不在finally块中显式调用return语句，这个修改也会作用于返回值上。
 * <p>
 *
 * 异常的注意事项
 * 1、如无需要，勿用异常
 * 2、不要在try块返回
 * 3、不要在循环中使用try/catch
 * 4、勿将异常用于控制流
 * <p>
 *
 * @author ganting
 * @date 2018-01-16
 * @since v1.0
 */
public class FinallyTest {

    private int getNumber(String str) throws NumberFormatException{
        try {
            int number = Integer.parseInt(str);
            return number;
        } catch (NumberFormatException ex) {
            throw ex;
        } finally {
            return -1;
        }
    }

    @Test
    public void testFinally() {
        try {
            System.out.println(getNumber("abc")); // -1
            System.out.println(getNumber("123")); // -1
        } catch (Exception e) {
            System.out.println(e.getMessage()); // 不会发生，因为异常被finally吞掉了
        }

    }

}
