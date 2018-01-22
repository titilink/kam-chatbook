package com.java8.gt.stream;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;

/**
 * 描述：实现自己的Collector<T, A, R>
 *     主要包括5个方法：
 *         Supplier<T> supplier();
 *         BiConsumer<T, A> accumulator();
 *         BinaryOperator<A> combiner();
 *         Function<A, R> finisher();
 *         Set<Characteristics> characteristics();
 *
 *     本实例通过计算质数，来演示自定义收集器的用处
 *
 * @author sandy
 * @date 2018/1/22
 * @since v1.0
 */
public class CustomCollector {

    public static void main(String[] args) {
        CustomCollector customCollector = new CustomCollector();

        customCollector.testInnerCollector();
        customCollector.testCustomeCollector();
    }

    private void testInnerCollector() {
        long fastest = Long.MAX_VALUE;

        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            primeWithInnerCollector(1_000_000);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest) {
                fastest = duration;
            }
        }

        System.out.println("Fastest by inner collector in: " + fastest + " msecs");
    }

    private void testCustomeCollector() {
        long fastest = Long.MAX_VALUE;

        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            primeWithCustomCollector(1_000_000);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest) {
                fastest = duration;
            }
        }

        System.out.println("Fastest by custom collector in: " + fastest + " msecs");
    }

    private void primeWithInnerCollector(int n) {
        IntStream.rangeClosed(2, n).boxed().collect(partitioningBy(candidate -> isPrime(candidate)));
    }

    private boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);

        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
    }

    private void primeWithCustomCollector(int n) {
        IntStream.rangeClosed(2, n).boxed().collect(new PrimeNumbersCollector());
    }

    private static class PrimeNumbersCollector implements
        Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

        @Override
        public Supplier<Map<Boolean, List<Integer>>> supplier() {
            return () -> new HashMap<Boolean, List<Integer>>(){{
                put(true, new ArrayList<>());
                put(false, new ArrayList<>());
            }};
        }

        @Override
        public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
            return (Map<Boolean, List<Integer>> acc, Integer candidate) -> acc.get(isPrime(acc.get(true), candidate)).add(candidate);
        }

        @Override
        public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
            return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2) -> {
                map1.get(true).addAll(map2.get(true));
                map1.get(false).addAll(map2.get(false));
                return map1;
            };
        }

        @Override
        public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
            return Function.identity();
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
        }

        private static boolean isPrime(List<Integer> primes, Integer candidate) {
            int candidateRoot = (int) Math.sqrt((double) candidate);

            return takeWhile(primes, i -> i <= candidateRoot).stream().noneMatch(p -> candidate % p == 0);


        }

        private static <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
            int i = 0;
            for (A item : list) {
                if (!p.test(item)) {
                    return list.subList(0, i);
                }
                i++;
            }
            return list;
        }
    }

}
