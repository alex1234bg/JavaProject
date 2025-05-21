package org.alexanderpetrovF113655.store.model;

import java.time.LocalDate;
import java.io.Serializable;
public abstract  class Product implements DiscountableInterface,Serializable{
    protected int id;
    protected String name;
    protected double deliveryPrice;
protected Category category;
protected LocalDate expirationDate;
protected int quantity;
    public int getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(double deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public Product(int id, String name, double deliveryPrice, Category category, LocalDate expirationDate, int quantity) {
        this.id = id;
        this.name = name;
        this.deliveryPrice = deliveryPrice;
        this.category = category;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
    }
    public abstract double calculateSellingPrice(LocalDate currentDate, double markupPercent, int daysThreshold,double discountPercent);
    public boolean isExpired(LocalDate currentDate){return currentDate.isAfter(expirationDate);}
}
