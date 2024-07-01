package com.example.mitermexam_2130086_sec6;

public class CartItem {
    private String productName;
    private float unitPrice;
    private int quantity;
    private int vatRate;

    public CartItem(String productName, float unitPrice, int quantity, int vatRate) {
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.vatRate = vatRate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getVatRate() {
        return vatRate;
    }

    public void setVatRate(int vatRate) {
        this.vatRate = vatRate;
    }

    public float getVatAmount(){
        return unitPrice * vatRate / 100;
    }

    public float getTotalAmount(){
        return (unitPrice + getVatAmount()) * quantity;
    }

    @Override
    public String toString() {
        /*return "CartItem{" +
                "productName='" + productName + '\'' +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", vatRate=" + vatRate +
                '}';*/
        return productName + ": " + quantity + " units @ " + unitPrice + " vat rate " + vatRate + " % ";


    }
}
