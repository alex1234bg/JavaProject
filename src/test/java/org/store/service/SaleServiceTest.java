package org.store.service;
import org.alexanderpetrovF113655.store.service.SaleService;
import org.alexanderpetrovF113655.store.exceptions.InsufficientQuantityException;
import org.alexanderpetrovF113655.store.model.Store;
import org.alexanderpetrovF113655.store.model.Cashier;
import org.alexanderpetrovF113655.store.model.Product;
import org.alexanderpetrovF113655.store.model.FoodProduct;
import org.alexanderpetrovF113655.store.model.Category;
import org.alexanderpetrovF113655.store.model.Receipt;

import org.alexanderpetrovF113655.store.model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SaleServiceTest {

    private Store mockStore;
    private SaleService saleService;
    private Cashier testCashier;

    @BeforeEach
    void setUp() {
        mockStore = mock(Store.class);
        saleService = new SaleService(mockStore);

        testCashier = new Cashier(1, "Cashier Maria", 1000.0);


        when(mockStore.getMarkupPercent(Category.FOOD)).thenReturn(30.0);
        when(mockStore.getDiscountDaysThreshold()).thenReturn(5);
        when(mockStore.getDiscountPercent()).thenReturn(20.0);
    }

    @Test
    void testSuccessfulSale() throws Exception {
        Product product = new FoodProduct(1, "Pasta", 1.0,
                LocalDate.now().plusDays(3), 10);

        Map<Product, Integer> items = new HashMap<>();
        items.put(product, 2);

        Receipt result = saleService.processSale(testCashier, items);

        assertNotNull(result);
        assertEquals(2, result.getPurchasedProducts().get(product).quantity);
        assertEquals(testCashier, result.getCashier());


        assertEquals(8, product.getQuantity());
    }

    @Test
    void testInsufficientQuantityThrowsException() {
        Product product = new FoodProduct(2, "Milk", 1.0,
                LocalDate.now().plusDays(5), 1);

        Map<Product, Integer> items = new HashMap<>();
        items.put(product, 5);

        assertThrows(InsufficientQuantityException.class, () -> {
            saleService.processSale(testCashier, items);
        });
    }
}
