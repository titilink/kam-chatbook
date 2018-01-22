package com.java8.gt.stream;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.reducing;

/**
 * 描述：流收集器：reducing、groupingby、counting、joining、toList、toSet、
 *
 *     reducing(U identity, Function<? super T, ? extends U> mapper, BinaryOperator<U> op)
 *               初始值                 转换函数                          累积函数
 *     reducing(U identity, BinaryOperator<U> op)   ##转换函数可以看成是t -> t的恒等函数
 *               初始值        累积函数
 *     reducing(BinaryOperator<U> op)   ##没有初始值时，返回Optional<T>
 *                    累积函数
 *
 * @author sandy
 * @date 2018/1/20
 * @since v1.0
 */
public class StreamCollect {

    public static void main(String[] args) {
        StreamCollect streamCollect = new StreamCollect();

        List<Transaction> transactions = Arrays.asList(
            new Transaction(new Trader("GanTing", "HZ"), 2011, 10),
            new Transaction(new Trader("GanTing", "HZ"), 2011, 30),
            new Transaction(new Trader("GanTing", "HZ"), 2012, 20)
        );
        streamCollect.calcTransactionValueByYear(transactions);

        List<Trader> traders = Arrays.asList(
            new Trader("GanTing", "HZ"),
            new Trader("ChenFen", "HZ"),
            new Trader("ZhangSan", "SZ")
        );
        streamCollect.fetchTraderNameStr(traders);
    }

    /**
     * 计算2011年的所有交易金额
     *
     * @param transactions
     */
    private void calcTransactionValueByYear(List<Transaction> transactions) {
        int sumValue1 = transactions.stream()
            .filter(transaction -> transaction.getYear() == 2011)
            .mapToInt(transaction -> transaction.getValue())
            .sum();
        System.out.println(sumValue1);

        int sumValue2 = transactions.stream()
            .filter(transaction -> transaction.getYear() == 2011)
            .map(Transaction::getValue)
            .reduce(Integer::sum).get();
        System.out.println(sumValue2);

        int sumValue3 = transactions.stream()
            .filter(transaction -> transaction.getYear() == 2011)
            .collect(reducing(0, Transaction::getValue, Integer::sum));
        System.out.println(sumValue3);
    }

    /**
     * 计算所有交易员的姓名，并转换为字符串
     *
     * @param traders
     */
    private void fetchTraderNameStr(List<Trader> traders) {
        String traderNameString1 = traders.stream().map(Trader::getName).collect(joining(", "));
        System.out.println(traderNameString1);

        String traderNameString2 = traders.stream().map(Trader::getName).collect(reducing((s1, s2) -> s1 + ", " + s2)).get();
        System.out.println(traderNameString2);

        String traderNameString3 = traders.stream().collect(reducing("", Trader::getName, (s1, s2) -> s1 + ", " + s2));
        System.out.println(traderNameString3);
    }

}
