package org.alexanderpetrovF113655.store.model;
import java.time.LocalDate;
public class NonFoodProduct extends Product{
    public NonFoodProduct(int id, String name, double deliveryPrice, LocalDate expirationDate, int quantity){
        super(id, name, deliveryPrice, Category.NON_FOOD, expirationDate,quantity);
    }
    public double calculateSellingPrice(LocalDate currentDate, double markupPercent,int daysThreshold, double discountPercent ){
        double price = deliveryPrice * (1 + markupPercent / 100);
        if(!isExpired(currentDate) && expirationDate.minusDays(daysThreshold).isBefore(currentDate)){
            price *= (1 - discountPercent / 100);
        }
        return Math.round(price * 100.0) / 100.00;
    }
}