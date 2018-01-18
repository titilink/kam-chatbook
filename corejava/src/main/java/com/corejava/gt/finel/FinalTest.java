package com.corejava.gt.finel;

/**
 * 描述：final如果修饰局部变量，标识该变量在本次调用中不可变。
 *       final如果修饰实例变量，标识该变量在实例内调用不可变
 *       final如果修饰静态变量，标识该变量在所有实例内调用不可变
 *
 * @author ganting
 * @date 2018-01-18
 * @since v1.0
 */
public class FinalTest {

    private Final aFinal = new Final();

    private static Final bFinal = new Final();

    private void printFinal() {
        final Final finel = new Final();
        System.out.println(finel.toString());
        System.out.println(aFinal.toString());
    }

    private void printStaticFinal() {
        System.out.println(bFinal.toString());
    }

    public static void main(String[] args) {
        FinalTest finalTest = new FinalTest();
        finalTest.printFinal();
        finalTest.printFinal();

        FinalTest staticFinalTest1 = new FinalTest();
        FinalTest staticFinalTest2 = new FinalTest();
        staticFinalTest1.printStaticFinal();
        staticFinalTest2.printStaticFinal();
    }

    private static class Final {

    }

}
