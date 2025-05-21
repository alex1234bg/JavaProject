package org.alexanderpetrovF113655.store.model;
import java.time.LocalDate;

public interface DiscountableInterface {
    double calculateSellingPrice(LocalDate currentDate, double markupPercent, int daysThreshold, double discountPercent);
}
