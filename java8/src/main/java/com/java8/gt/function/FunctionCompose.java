package com.java8.gt.function;

import java.util.function.Function;

/**
 * 描述：类似数学上的y = f(g(x))，其中函数g的输出作为函数f的输入，类似流式计算
 *
 *    数学表达：f(x) = x + 10
 *    lambda表达：x -> x + 10
 *
 * @author ganting
 * @date 2018-01-18
 * @since v1.0
 */
public class FunctionCompose {

    public static void main(String[] args) {
        Function<Integer, Integer> addOne = x -> x + 1;
        Function<Integer, Integer> multiplyTwo = x -> x * 2;
        Function<Integer, Integer> andThenFunc = addOne.andThen(multiplyTwo);
        Function<Integer, Integer > composeFunc = addOne.compose(multiplyTwo);

        int ret1 = andThenFunc.apply(1);
        int ret2 = composeFunc.apply(1);
        System.out.println(ret1);
        System.out.println(ret2);
    }

}
