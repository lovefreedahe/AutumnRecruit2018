package com.wangrupeng.pattern.appearance;

import javafx.geometry.Pos;

public class FinalPrice {
    ProductPrice productPrice;
    Postage postage;
    Discount discount;
    public FinalPrice() {
        productPrice = new ProductPrice();
        postage = new Postage();
        discount = new Discount();
    }
    int getFinalPrice(String product, String address, String discountCode) {
        return productPrice.getPrice(product) + postage.getPostage(address) - discount.getDiscount(discountCode);
    }
}
