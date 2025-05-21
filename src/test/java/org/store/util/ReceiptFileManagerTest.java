package org.store.util;

import org.alexanderpetrovF113655.store.model.*;
import org.alexanderpetrovF113655.store.util.ReceiptFileManager;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ReceiptFileManagerTest {

    @Test
    void testSaveAndLoadSerializedReceipt() throws Exception {
        Receipt receipt = new Receipt(999, new Cashier(1, "Иван", 1000));
        receipt.addProduct(new FoodProduct(1, "Хляб", 1.0, LocalDate.now().plusDays(3), 10), 2, 1.5);

        ReceiptFileManager.saveReceiptSerialized(receipt);

        Receipt loaded = ReceiptFileManager.loadReceiptSerialized(999);

        assertNotNull(loaded);
        assertEquals(receipt.getReceiptNumber(), loaded.getReceiptNumber());
        assertEquals(receipt.getCashier().getName(), loaded.getCashier().getName());
        assertEquals(receipt.getTotal(), loaded.getTotal(), 0.01);
    }

    @Test
    void testSaveReceiptAsTextFile() throws Exception {
        Receipt receipt = new Receipt(1000, new Cashier(2, "Maria", 1100));
        receipt.addProduct(new NonFoodProduct(2, "Soap", 2.0, LocalDate.now().plusDays(10), 5), 1, 2.5);

        ReceiptFileManager.saveReceiptAsText(receipt);

        File file = new File("receipts/receipt_1000.txt");
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    @Test
    void testReadReceiptTextFile() throws Exception {
        Receipt receipt = new Receipt(1001, new Cashier(3, "Test", 0));
        receipt.addProduct(new FoodProduct(3, "Yoghurt", 0.9, LocalDate.now().plusDays(2), 5), 1, 1.2);

        ReceiptFileManager.saveReceiptAsText(receipt);

        String content = ReceiptFileManager.readReceiptText(1001);

        assertNotNull(content);
        assertTrue(content.contains("Receipt #1001"));
        assertTrue(content.contains("Yoghurt"));
    }
}