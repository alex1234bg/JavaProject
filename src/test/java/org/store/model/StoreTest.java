package org.store.model;

import org.alexanderpetrovF113655.store.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class StoreTest {

    private Store store;

    @BeforeEach
    void setUp() {
        store = new Store("Test Store", 30.0, 20.0, 5, 10.0);

        Product p1 = new FoodProduct(1, "Хляб", 1.0, LocalDate.now().plusDays(5), 10); // 10 лв доставки
        Product p2 = new NonFoodProduct(2, "Сапун", 2.0, LocalDate.now().plusDays(10), 5); // 10 лв доставки

        store.addProduct(p1);
        store.addProduct(p2);

        Cashier c1 = new Cashier(100, "Анна", 1200.0);
        Cashier c2 = new Cashier(101, "Борис", 800.0);

        store.addCashier(c1);
        store.addCashier(c2);
    }

    @Test
    void testCalculateExpenses() {
        double expectedExpenses = 10 * 1.0 + 5 * 2.0 + 1200.0 + 800.0;
        assertEquals(expectedExpenses, store.calculateExpenses(), 0.01);
    }

    @Test
    void testMarkupPercent() {
        assertEquals(30.0, store.getMarkupPercent(Category.FOOD));
        assertEquals(20.0, store.getMarkupPercent(Category.NON_FOOD));
    }

    @Test
    void testAddReceiptAndRevenue() {
        Receipt dummyReceipt = new Receipt(Store.getNextReceiptNumber(), new Cashier(999, "Dummy", 0));
        dummyReceipt.addProduct(new FoodProduct(3, "Продукт", 2.0, LocalDate.now().plusDays(3), 5), 2, 3.0);
        store.addReceipt(dummyReceipt);

        assertEquals(6.0, store.getTotalRevenue(), 0.01);
    }

    @Test
    void testCalculateProfit() {
        // Добавяме бележка с приходи 100 лв
        Receipt r = new Receipt(Store.getNextReceiptNumber(), new Cashier(999, "Dummy", 0));
        r.addProduct(new FoodProduct(4, "Сирене", 2.0, LocalDate.now().plusDays(3), 10), 5, 20.0); // 5 * 20 = 100
        store.addReceipt(r);

        double profit = store.calculateProfit();
        double expected = 100.0 - store.calculateExpenses();

        assertEquals(expected, profit, 0.01);
    }
}