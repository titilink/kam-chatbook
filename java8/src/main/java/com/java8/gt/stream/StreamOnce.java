package com.java8.gt.stream;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 描述：和集合一样，流只能被消费一次，如果需要再消费，需要重新获取流。
 *
 * @author ganting
 * @date 2018-01-18
 * @since v1.0
 */
public class StreamOnce {

    public static void main(String[] args) {
        Stream<String> s = Arrays.asList("A", "B", "C").stream();

        s.forEach(System.out :: println);
        s.forEach(System.out :: println); //流已经被消费过了
    }

}
