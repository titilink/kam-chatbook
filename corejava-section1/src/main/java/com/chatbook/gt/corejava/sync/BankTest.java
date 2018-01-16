package com.chatbook.gt.corejava.sync;

/**
 * 描述：[描述]
 *
 * @author sandy
 * @date 2018/1/16
 * @since v1.0
 */
public class BankTest {

    private static final int NACCOUNTS = 100;
    private static final double INITIAL_BALANCE = 1000;
    private static final double MAX_AMOUNT = 1000;
    private static final int DELAY = 10;

    public static void main(String[] args) {
        Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);
        for ( int i = 0; i < NACCOUNTS; i++ ) {
            int fromAccount = i;
            Runnable r = () -> {
              try {
                  while ( true ) {
                      int toAccount = (int) (bank.size() * Math.random());
                      double amount = MAX_AMOUNT * Math.random();
                      bank.transfer(fromAccount, toAccount, amount);
                      Thread.sleep((long) (DELAY * Math.random()));
                  }
              } catch (InterruptedException ie) {

              }
            };
            Thread thread = new Thread(r);
            thread.start();
        }
    }

}
