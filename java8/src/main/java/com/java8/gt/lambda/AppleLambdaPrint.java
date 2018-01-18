package com.java8.gt.lambda;

import com.java8.gt.Apple;
import com.java8.gt.AppleFormatter;
import com.java8.gt.ApplePrinter;

import java.util.Arrays;
import java.util.List;

/**
 * 描述：[描述]
 *
 * @author ganting
 * @date 2018-01-17
 * @since v1.0
 */
public class AppleLambdaPrint {

    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple(151, "red"), new Apple(100, "green"));

        AppleFormatter<Apple> appleAppleFormatter = (Apple apple) -> apple.getColor();

        new ApplePrinter().printApple(apples, appleAppleFormatter);
    }

}
