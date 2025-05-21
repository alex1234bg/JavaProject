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
    private Map<Product, Integer> purchasedProducts;
    private double total;

    public Receipt(int receiptNumber, Cashier cashier) {
        this.receiptNumber = receiptNumber;
        this.cashier = cashier;
        this.timeStamp = LocalDateTime.now();
        this.purchasedProducts = new LinkedHashMap<>();
        this.total = 0.0;
    }
    public void addProduct(Product product,int quantity, double unitPrice){
        purchasedProducts.put(product, quantity);
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

    public Map<Product, Integer> getPurchasedProducts() {
        return purchasedProducts;
    }

    public double getTotal() {
        return Math.round(total*100.0)/ 100.0;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Receipt #").append(receiptNumber).append("\n");
        sb.append("Cashier: ").append(cashier.getName()).append("\n");
        sb.append("Date: ").append(timeStamp).append("\n");
        sb.append("Items:\n" );
        for(Map.Entry<Product, Integer> entry: purchasedProducts.entrySet()){
            sb.append(" - ")
                    .append(entry.getKey().name)
                    .append(" x ")
                    .append(entry.getValue())
                    .append("\n");
        };
        sb.append("Total: ").append(getTotal()).append(" BGN\n");
        return sb.toString();
    }
}
