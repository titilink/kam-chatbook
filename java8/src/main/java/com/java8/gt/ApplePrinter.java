package com.java8.gt;

import java.util.Collection;

/**
 * 描述：苹果打印机，进一步通过泛型可以抽象为通用对象打印机
 *
 * @author ganting
 * @date 2018-01-17
 * @since v1.0
 */
public class ApplePrinter<T> {

    public <T> void printApple(Collection<T> ts, AppleFormatter<T> formatter) {
        for (T t : ts) {
            System.out.println(formatter.accept(t));
        }
    }

}
