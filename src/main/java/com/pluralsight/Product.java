package com.pluralsight;

public class Product {
    private String sku;
    private String product;
    private double price;

    public Product(String sku, String product, double price) {
        this.sku = sku;
        this.product = product;
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return sku + "|" + product + "|" + price;
    }
}