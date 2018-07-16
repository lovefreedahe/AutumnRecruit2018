package com.wangrupeng.pattern.appearance;

public enum ProductSalesman {
    INSTANCE;
    Stock stock = new Stock();
    FinalPrice finalPrice = new FinalPrice();
    Object buySomething(String product, String address, String discountCode) {
        if(!stock.hasStock(product)) {
            return "No stock";
        }
        int price = finalPrice.getFinalPrice(product, address, discountCode);
        return "订单信息:" + product + "-" + address + "-" + discountCode + "-" + price;
    }
}
