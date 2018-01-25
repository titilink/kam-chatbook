package com.java8.gt.future;

/**
 * 描述：折扣服务
 *
 * @author sandy
 * @date 2018/1/24
 * @since v1.0
 */
public class Quote {

    private final String shopName;

    private final double price;

    private final Discount.Code code;

    public Quote(String shopName, double price, Discount.Code code) {
        this.shopName = shopName;
        this.price = price;
        this.code = code;
    }

    public static Quote parse(String s) {
        String[] tokens = s.split(":");
        String shopName = tokens[0];
        double price = Double.parseDouble(tokens[1]);
        Discount.Code code = Discount.Code.valueOf(tokens[2]);

        return new Quote(shopName, price, code);
    }

    public String getShopName() {
        return shopName;
    }

    public double getPrice() {
        return price;
    }

    public Discount.Code getCode() {
        return code;
    }
}
