package com.java8.gt.future;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * 描述：组合多个异步任务
 *
 *     thenApply
 *     thenCompose
 *     thenCombine
 *     thenAccept
 *
 *     获取价格、获取折扣、获取汇率
 *
 * @author sandy
 * @date 2018/1/24
 * @since v1.0
 */
public class ComposeCompletableFuture {

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

    public static void main(String[] args) {
        ComposeCompletableFuture composeCompletableFuture = new ComposeCompletableFuture();

        System.out.println("===============同步方式获取价格和折扣=============");
        composeCompletableFuture.findPricesSync();
        System.out.println("===============异步方式获取价格和折扣=============");
        composeCompletableFuture.findPricesAsync();
        System.out.println("===============异步方式获取价格和折扣，监听Completion事件=============");
        composeCompletableFuture.findPricesAsyncWaitOrNot();
    }

    /**
     * 同步方式组合两个服务
     */
    private void findPricesSync() {
        long start = System.nanoTime();
        List<String> discountPrices = shops.stream()
            .map(shop -> getPrices(shop.getName(), "ss"))
            .map(Quote::parse)
            .map(Discount::applyDiscount)
            .collect(toList());

        System.out.println(discountPrices);
        System.out.println("done in " + (System.nanoTime() - start) / 1_000_000 + " msecs");
    }

    /**
     * 异步方式组合两个服务
     */
    private void findPricesAsync() {
        long start = System.nanoTime();
        List<CompletableFuture<String>> discountPriceFutures = shops.stream()
            .map(shop -> CompletableFuture.supplyAsync(() -> getPrices(shop.getName(), "ss"), executor))
            .map(future -> future.thenApply(Quote::parse))
            .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
            .collect(toList());

        List<String> prices = discountPriceFutures.stream()
            .map(CompletableFuture::join)
            .collect(toList());

        System.out.println(prices);
        System.out.println("done in " + (System.nanoTime() - start) / 1_000_000 + " msecs");
    }

    /**
     * 是否等待所有异步服务结果返回
     */
    private void findPricesAsyncWaitOrNot() {
        long start = System.nanoTime();
        Stream<CompletableFuture<String>> discountPriceFuturesStream = shops.stream()
            .map(shop -> CompletableFuture.supplyAsync(() -> getPrices(shop.getName(), "ss"), executor))
            .map(future -> future.thenApply(Quote::parse))
            .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)));

        CompletableFuture[] futures = discountPriceFuturesStream
            .map(f -> f.thenAccept(s -> {
                System.out.println(s + " done in " + (System.nanoTime() - start) / 1_000_000 + " msecs");
            }))
            .toArray(size -> new CompletableFuture[size]);

        CompletableFuture.allOf(futures).join();  // 等待所有future返回

        System.out.println("All shop have now responded in " + (System.nanoTime() - start) / 1_000_000 + " msecs");

        // CompletableFuture.anyOf(futures); // 任何一个future返回
    }

    private String getPrices(String shopName, String product) {
        // 计算价格
        randomDelay();

        double price = random.nextDouble() * product.charAt(0) + product.charAt(1);

        // 获取折扣
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];

        // 返回价格字符串
        return String.format("%s:%.2f:%s",shopName, price, code);
    }

    private void randomDelay() {
        long delay = 500 + random.nextInt(2000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
