package com.java8.gt.stream;

import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 描述：斐波拉契数值流
 *
 * @author sandy
 * @date 2018/1/20
 * @since v1.0
 */
public class FibonacciStream {

    public static void main(String[] args) {
        FibonacciStream fibonacciStream = new FibonacciStream();

        fibonacciStream.iterateStream();
        System.out.println();
        fibonacciStream.generateStream();
    }

    private void iterateStream() {
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
            .limit(20)
            .map(t -> t[0])
            .forEach(n -> System.out.print(n + ", "));
    }

    private void generateStream() {
        IntSupplier intSupplier = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int oldPrevious = previous;
                int newCurrent = previous + current;
                previous = current;
                current = newCurrent;

                return oldPrevious;
            }
        };

        IntStream.generate(intSupplier).limit(20).forEach(i -> System.out.print(i + ", "));
    }


}
