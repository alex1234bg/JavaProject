package org.alexanderpetrovF113655.store.model;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class Receipt implements  Serializable{
    private int receiptNumber;
    private  Cashier cashier;
    private LocalDateTime timeStamp;
    private Map<Product, PurchaseInfo> purchasedProducts;
    private double total;

    public Receipt(int receiptNumber, Cashier cashier) {
        this.receiptNumber = receiptNumber;
        this.cashier = cashier;
        this.timeStamp = LocalDateTime.now();
        this.purchasedProducts = new LinkedHashMap<>();
        this.total = 0.0;
    }
    public void addProduct(Product product, int quantity, double unitPrice) {
        purchasedProducts.put(product, new PurchaseInfo(quantity, unitPrice));
        total += unitPrice * quantity;
    }


    public int getReceiptNumber() {
        return receiptNumber;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public Map<Product, PurchaseInfo> getPurchasedProducts() {
        return purchasedProducts;
    }

    public double getTotal() {
        return Math.round(total*100.0)/ 100.0;
    }

    private static class PurchaseInfo implements Serializable {
        int quantity;
        double unitPrice;

        PurchaseInfo(int quantity, double unitPrice) {
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Receipt #").append(receiptNumber).append('\n');
        sb.append("Cashier: ").append(cashier.getName()).append('\n');
        sb.append("Date: ").append(timeStamp).append('\n');
        sb.append("Items:\n");

        for (Map.Entry<Product, PurchaseInfo> entry : purchasedProducts.entrySet()) {
            Product product = entry.getKey();
            PurchaseInfo info = entry.getValue();
            double lineTotal = info.unitPrice * info.quantity;

            sb.append(" - ")
                    .append(product.getName())
                    .append(" x ").append(info.quantity)
                    .append(" @ ").append(String.format("%.2f", info.unitPrice)).append(" BGN")
                    .append(" = ").append(String.format("%.2f", lineTotal)).append(" BGN\n");
        }

        sb.append("Total: ").append(String.format("%.2f", total)).append(" BGN\n");
        return sb.toString();
    }

}
