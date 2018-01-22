package com.java8.gt.stream;

/**
 * 描述：交易信息
 *
 * @author sandy
 * @date 2018/1/19
 * @since v1.0
 */
public class Transaction {

    private Trader trader;

    private int year;

    private int value;

    public Transaction() {

    }

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
