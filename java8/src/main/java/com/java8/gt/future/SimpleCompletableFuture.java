package com.java8.gt.future;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * 描述： CompletableFuture<V>  异步计算API
 *    本示例演示了4种方式获取商品price的写法
 *      1、同步方式
 *      2、没有lambda的异步方式，人为编码
 *      3、通过lambda的异步方式
 *      4、通过lambda的异步方式，并自定义线程池
 *
 *      按照Java并发编程的经验之谈，线程池的大小设定为：N（threads）= N（cpu）* U（cpu）* (1 + W/C)
 *
 * @author sandy
 * @date 2018/1/24
 * @since v1.0
 */
public class SimpleCompletableFuture {

    private Random random = new Random();

    private List<Shop> shops = Arrays.asList(
        new Shop("BestShop"),
        new Shop("LetsSaveBig"),
        new Shop("MyFavoriteShop"),
        new Shop("BuyItAll"),
        new Shop("BuyItAllGT"),
        new Shop("BuyItAllCF"),
        new Shop("BuyItAllGCY")
    );

    private final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(),
        Runtime.getRuntime().availableProcessors() * 100), r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
    });

    public static void main(String[] args) throws InterruptedException {
        SimpleCompletableFuture simpleFuture = new SimpleCompletableFuture();

        System.out.println("============编码方式异步获取价格==============");
        simpleFuture.findPriceAsyncNative();
        System.out.println("============同步获取价格==============");
        simpleFuture.findPriceSync();
        System.out.println("============lambda异步获取价格==============");
        simpleFuture.findPriceAsync1();
        System.out.println("============lambda异步且定制线程池获取价格==============");
        simpleFuture.findPriceAsync2();
    }

    /**
     * 通过编码形式以异步方式获取最佳价格
     */
    private void findPriceAsyncNative() {
        long start = System.nanoTime();
        Future<Double> future = findPriceAsyncNativeInternal();
        long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("invocation method elapse: " + invocationTime + " msecs");

        // do other things
        // Thread.sleep(2000);

        try {
            System.out.printf("fetch price: %.2f%n", future.get());
        } catch (ExecutionException e) {
            throw new RuntimeException(e); // 可以捕获计算线程的异常
        } catch (InterruptedException e) {
            throw new RuntimeException(e); // 可以捕获计算线程的异常
        }

        long retrievalPriceTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("retrieval price elapse: " + retrievalPriceTime + " msecs");
    }

    private Future<Double> findPriceAsyncNativeInternal() {
        CompletableFuture<Double> future = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = getPrice("ss");
                future.complete(price);
            } catch (Exception e) {
                future.completeExceptionally(e);  // 将计算线程中的异常捕获并抛出去
            }
        }).start();

        return future;
    }

    /**
     * 通过lambda形式以同步方式获取最佳价格
     */
    private void findPriceSync() {
        long start = System.nanoTime();
        System.out.println(findPricesSyncInternal());
        long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("invocation method elapse: " + invocationTime + " msecs");
    }

    private List<String> findPricesSyncInternal() {
        return shops.stream()
            .map(shop -> String.format("%s price is %.2f", shop.getName(), getPrice("ss")))
            .collect(Collectors.toList());
    }

    /**
     * 通过lambda形式以异步方式获取最佳价格
     */
    private void findPriceAsync1() {
        long start = System.nanoTime();
        System.out.println(findPricesAsync1Internal());
        long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("invocation method elapse: " + invocationTime + " msecs");
    }

    private List<String> findPricesAsync1Internal() {
        List<CompletableFuture<String>> completableFutures = shops.stream()
            .map(shop -> CompletableFuture.supplyAsync(() ->
                String.format("%s price is %.2f", shop.getName(), getPrice("ss"))))
            .collect(Collectors.toList());

        return completableFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    /**
     * 通过lambda形式并通过自定义线程池，以异步方式获取最佳价格
     */
    private void findPriceAsync2() {
        long start = System.nanoTime();
        System.out.println(findPricesAsync2Internal());
        long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("invocation method elapse: " + invocationTime + " msecs");
    }

    private List<String> findPricesAsync2Internal() {
        List<CompletableFuture<String>> completableFutures = shops.stream()
            .map(shop -> CompletableFuture.supplyAsync(() ->
                String.format("%s price is %.2f", shop.getName(), getPrice("ss")), executor))
            .collect(Collectors.toList());

        return completableFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    private double getPrice(String productName) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return random.nextDouble() * productName.charAt(0) + productName.charAt(1);
    }

}
