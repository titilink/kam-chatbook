package com.java8.gt.stream;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/**
 * 描述：分区流  partitioningBy，分区流接收一个B返回Boolean的谓词作为分区函数
 *      partitioningBy(Predicate<? super T> predicate)
 *      partitioningBy(Predicate<? super T> predicate, Collector<? super T, A, D> downstream)
 *
 * @author sandy
 * @date 2018/1/21
 * @since v1.0
 */
public class PartitionStream {

    public static void main(String[] args) {

    }

    /**
     * 根据交易额对交易分区
     *
     * @param transactions
     */
    private Map<Boolean, List<Transaction>> partionValue(List<Transaction> transactions) {
        return transactions.stream().collect(partitioningBy(transaction -> {
            if (transaction.getValue() < 100) {
                return true;
            }
            return false;
        }));
    }

    /**
     * 根据交易额对交易分区，并获取每个分区的最高交易
     *
     * @param transactions
     */
    private Map<Boolean, Transaction> partionValueAndFetchMaxValueTransaction(List<Transaction> transactions) {
        return transactions.stream().collect(partitioningBy(transaction -> transaction.getValue() < 100,
            collectingAndThen(maxBy(comparingInt(Transaction::getValue)), Optional::get)));
    }

    /**
     * 首先根据交易额分区，然后根据交易年份分区，并统计每个子分区下的最高交易
     *
     * @param transactions
     * @return
     */
    private Map<Boolean, Map<Boolean, Transaction>> partionValueAndYear(List<Transaction> transactions) {
        return transactions.stream().collect(partitioningBy(transaction -> transaction.getValue() < 100,
            partitioningBy(transaction1 -> transaction1.getYear() < 2011,
                collectingAndThen(maxBy(comparingInt(Transaction::getValue)), Optional::get))));
    }

}
