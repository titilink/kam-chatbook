package com.java8.gt.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述：对于数组型流Stream<String[]>，通过Arrays.stream和Stream.flagMap可以解决
 *
 * @author sandy
 * @date 2018/1/19
 * @since v1.0
 */
public class FlatMapArray {

    public static void main(String[] args) {
        FlatMapArray f = new FlatMapArray();

        f.distinctChar();
        f.squareInt();
        f.pairInt();
    }

    private void distinctChar() {
        String[] words = new String[]{"Hello", "World"};
        List<String> wordList = Arrays.asList(words).stream()
            .map(word -> word.split(""))
            .flatMap(Arrays::stream)
            .map(word -> word.toLowerCase())
            .distinct()
            .collect(Collectors.toList());

        System.out.println(wordList);
    }

    private void squareInt() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4);

        System.out.println(integers.stream().map(i -> i * i).collect(Collectors.toList()));
    }

    /**
     * should return [[1, 3], [1, 4], [2, 3], [2, 4], [3, 3], [3, 4]]
     */
    private void pairInt() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(3, 4);

        List<int[]> ret1 = list1.stream()
            .flatMap(m -> list2.stream().map(n -> new int[]{m, n}))
            .collect(Collectors.toList());

        List<int[]> ret2 = list1.stream()
            .flatMap(m -> list2.stream()
                .filter(n -> (m + n) % 3 == 0)
                .map(n -> new int[]{m, n}))
            .collect(Collectors.toList());


        ret1.stream().forEach(intArr -> System.out.print(Arrays.toString(intArr) + ", "));
        System.out.println();
        ret2.stream().forEach(intArr -> System.out.print(Arrays.toString(intArr) + ", "));
    }

}
