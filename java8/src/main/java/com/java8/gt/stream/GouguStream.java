package com.java8.gt.stream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 描述：勾股流
 *
 * @author sandy
 * @date 2018/1/20
 * @since v1.0
 */
public class GouguStream {

    public static void main(String[] args) {
        Stream<double[]> gouguStream = IntStream.rangeClosed(1, 100)
            .boxed()
            .flatMap(a -> IntStream.rangeClosed(a, 100)
                .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                .filter(t -> t[2] % 1 == 0));

        gouguStream.forEach(t -> System.out.println(t[0] + "," + t[1] + "," + t[2]));
    }

}
