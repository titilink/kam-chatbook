package com.java8.gt.stream;

import java.util.*;

import static java.util.stream.Collectors.*;

/**
 * 描述：流分组  groupingBy
 *
 *      groupingBy(Function<? super T, ? extends K> classifier)
 *                  分组函数
 *
 *      分组也可以嵌套，返回类似Map<T, Map<U, List<P>>>
 *
 *      传给分组的不一定是另外一个分组，也可以是counting等收集器
 *
 * @author sandy
 * @date 2018/1/20
 * @since v1.0
 */
public class GroupStream {

    public static void main(String[] args) {
        GroupStream groupStream = new GroupStream();

        List<Transaction> transactions = Arrays.asList(
            new Transaction(new Trader("GanTing", "HZ"), 2011, 10),
            new Transaction(new Trader("GanTing", "HZ"), 2011, 300),
            new Transaction(new Trader("GanTing", "HZ"), 2012, 1000)
        );

        Map<ValueLevel, List<Transaction>> groups1 = groupStream.groupTransactionByValue(transactions);
        groups1.forEach((key, value) -> {
            System.out.println(key);
            System.out.println(value);
        });

        Map<Trader, Map<ValueLevel, List<Transaction>>> groups2 = groupStream.groupTransactionByCityAndValue(transactions);
        groups2.forEach((key, value) -> {
            System.out.println(key);
            System.out.println(value);
        });

        Map<Integer, Long> group3 = groupStream.groupTransactionByYear(transactions);
        group3.forEach((key, value) -> {
            System.out.println(key);
            System.out.println(value);
        });

        Map<Integer, Optional<Transaction>> group4 = groupStream.groupTransactionMaxValueByYear(transactions);
        group4.forEach((key, value) -> {
            System.out.println(key);
            System.out.println(value.orElseGet(Transaction::new).getValue());
        });

        Map<Integer, IntSummaryStatistics> group5 = groupStream.groupTransactionValueByYear(transactions);
        group5.forEach((key, value) -> {
            System.out.println(key);
            System.out.println("count:" + value.getCount() + ", sum:" + value.getSum());
        });
    }

    /**
     * 按交易额分组
     *
     * @param transactions
     * @return
     */
    private Map<ValueLevel, List<Transaction>> groupTransactionByValue(List<Transaction> transactions) {
        return transactions.stream().collect(groupingBy(transaction -> {
            if (transaction.getValue() < 100) {
                return ValueLevel.LOWER;
            } else if (transaction.getValue() < 500) {
                return ValueLevel.MIDDLE;
            } else {
                return ValueLevel.HIGHER;
            }
        }));
    }

    /**
     * 先按交易员分组，然后按交易额分组
     *
     * @param transactions
     * @return
     */
    private Map<Trader, Map<ValueLevel, List<Transaction>>> groupTransactionByCityAndValue(List<Transaction> transactions) {
          return transactions.stream().collect(groupingBy(Transaction::getTrader, groupingBy(transaction -> {
              if (transaction.getValue() < 100) {
                  return ValueLevel.LOWER;
              } else if (transaction.getValue() < 500) {
                  return ValueLevel.MIDDLE;
              } else {
                  return ValueLevel.HIGHER;
              }
          })));
    }

    /**
     * 统计每一年的交易数量
     *
     * @param transactions
     * @return
     */
    private Map<Integer, Long> groupTransactionByYear(List<Transaction> transactions) {
        return transactions.stream().collect(groupingBy(Transaction::getYear, counting()));
    }

    /**
     * 统计每一年的最大交易额
     *
     * @param transactions
     * @return
     */
    private Map<Integer, Optional<Transaction>> groupTransactionMaxValueByYear(List<Transaction> transactions) {
        return transactions.stream().collect(groupingBy(Transaction::getYear, maxBy(Comparator.comparingInt(Transaction::getValue))));
    }

    /**
     * 统计每一年的所有交易额
     *
     * @param transactions
     * @return
     */
    private Map<Integer, IntSummaryStatistics> groupTransactionValueByYear(List<Transaction> transactions) {
        return transactions.stream().collect(groupingBy(Transaction::getYear, summarizingInt(Transaction::getValue)));
    }

    /**
     * 按年分组，并统计每年的交易额最大的交易
     *
     * @param transactions
     * @return
     */
    private Map<Integer, Transaction> fetchAndGroupTransactionMaxValueByYear(List<Transaction> transactions) {
        return transactions.stream().collect(groupingBy(Transaction::getYear, collectingAndThen(maxBy(Comparator.comparingInt(Transaction::getValue)), Optional::get)));
    }

    /**
     * 按年分组，并统计每年的交易额类型
     *
     * @param transactions
     * @return
     */
    private Map<Integer, Set<ValueLevel>> groupTransactionValueLevelByYear(List<Transaction> transactions) {
//        return transactions.stream().collect(groupingBy(Transaction::getYear, mapping(transaction -> {
//            if (transaction.getValue() < 100) {
//                return ValueLevel.LOWER;
//            } else if (transaction.getValue() < 300) {
//                return ValueLevel.MIDDLE;
//            } else {
//                return ValueLevel.HIGHER;
//            }
//        }, toSet())));

        return transactions.stream().collect(groupingBy(Transaction::getYear, mapping(transaction -> {
            if (transaction.getValue() < 100) {
                return ValueLevel.LOWER;
            } else if (transaction.getValue() < 300) {
                return ValueLevel.MIDDLE;
            } else {
                return ValueLevel.HIGHER;
            }
        }, toCollection(HashSet::new))));
    }

    private enum ValueLevel {
        LOWER, MIDDLE, HIGHER
    }

}
