package org.alexanderpetrovF113655.store.model;
import java.util.ArrayList;
import java.util.List;
public class Store {
    private String name;
    private List<Product> products;
    private List<Cashier> cashiers;
    private List<Receipt> receipts;

    private double markupFoodPercent;
    private double markupNonFoodPercent;
    private int discountDaysThreshold;
    private double discountPercent;
    private static int receiptCounter = 0;
    private double totalRevenue = 0.0;

    public Store(String name,  double markupFoodPercent, double markupNonFoodPercent, int discountDaysThreshold, double discountPercent) {
        this.name = name;
        this.products = new ArrayList<>();
        this.cashiers =new ArrayList<>();
        this.receipts = new ArrayList<>();
        this.markupFoodPercent = markupFoodPercent;
        this.markupNonFoodPercent = markupNonFoodPercent;
        this.discountDaysThreshold = discountDaysThreshold;
        this.discountPercent = discountPercent;

    }
    public void addCashier (Cashier cashier ){cashiers.add(cashier);}
    public void addProduct (Product product ){products.add(product);}
    public List<Product> getProducts(){return products;}
    public List<Cashier> getCashiers(){return cashiers;}
    public List<Receipt> getReceipts(){return receipts;}

    public double getMarkupPercent(Category category){
        return category == Category.FOOD ? markupFoodPercent : markupNonFoodPercent;
    }
    public int getDiscountDaysThreshold(){return discountDaysThreshold;}
    public double getDiscountPercent(){return discountPercent;}
    public static int getNextReceiptNumber(){return ++receiptCounter;}
    public void addReceipt(Receipt receipt){
        receipts.add(receipt);
        totalRevenue += receipt.getTotal();
    }
    public double calculateExpenses(){
        double salaryExpenses  = cashiers.stream()
                .mapToDouble(Cashier::getMonthlySalary)
                .sum();
        double deliveryExpenses = products.stream()
                .mapToDouble(p -> p.deliveryPrice * p.quantity)
                .sum();

        return salaryExpenses + deliveryExpenses;
    }
    public double calculateProfit(){return totalRevenue - calculateExpenses();}

    public double getTotalRevenue() {
        return totalRevenue;
    }
}
