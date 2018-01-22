package com.java8.gt.stream;

import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * 描述：[描述]
 *
 * @author sandy
 * @date 2018/1/19
 * @since v1.0
 */
public class TradeStream {

    public static void main(String[] args) {

    }

    /**
     * 找出2011年发生的交易，并按交易额排序（从低到高）
     *
     * @param transactions
     */
    private void findTransactionByYear(List<Transaction> transactions) {
        transactions.stream()
            .filter(transaction -> transaction.getYear() == 2011)
            .sorted(comparing(Transaction::getValue))
            .collect(toList());
    }

    /**
     * 查找交易员工作城市
     *
     * @param transactions
     */
    private void findTraderCity(List<Transaction> transactions) {
        transactions.stream()
            .map(transaction -> transaction.getTrader().getCity())
            .distinct()
            .collect(toList());

        transactions.stream()
            .map( transaction -> transaction.getTrader().getCity())
            .collect(toSet());
    }

    /**
     * 查找所有来自伦敦的交易员，并按照姓名排序
     *
     * @param transactions
     */
    private void findTraderByCityAndSort(List<Transaction> transactions) {
        transactions.stream()
            .map(transaction -> transaction.getTrader())
            .filter(trader -> "Cambridge".equals(trader.getCity()))
            .distinct()
            .sorted(comparing(Trader::getName))
            .collect(toList());
    }

    /**
     * 返回所有交易员的姓名字符串，并按照字母排序
     *
     * @param transactions
     */
    private void findTraderName(List<Transaction> transactions) {
        transactions.stream()
            .map(transaction -> transaction.getTrader().getName())
            .distinct()
            .sorted()
            .reduce("", (n1, n2) -> n1 + n2);
    }

    /**
     * 有没有交易员在米兰工作过
     *
     * @param transactions
     */
    private void findTraderByCity(List<Transaction> transactions) {
        transactions.stream()
            .anyMatch(transaction -> "Milan".equals(transaction.getTrader().getCity()));
    }

    /**
     * 打印生活在剑桥的交易员的所有交易额
     *
     * @param transactions
     */
    private void countByTraderCity(List<Transaction> transactions) {
        transactions.stream()
            .filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
            .map(Transaction::getValue)
            .forEach(System.out::println);
    }

    /**
     * 寻找最高的交易额
     *
     * @param transactions
     */
    private void findMaxValue(List<Transaction> transactions) {
        transactions.stream()
            .map(Transaction::getValue)
            .reduce(Integer::max);

        transactions.stream()
            .max(Comparator.comparing(Transaction::getValue))
            .map(Transaction::getValue);

        transactions.stream()
            .mapToInt(Transaction::getValue)
            .max();
    }

    /**
     * 寻找交易额最小的交易
     *
     * @param transactions
     */
    private void findMinTransaction(List<Transaction> transactions) {
        transactions.stream()
            .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);

        transactions.stream()
            .min(Comparator.comparing(Transaction::getValue));
    }

}