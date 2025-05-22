package org.alexanderpetrovF113655;
import org.alexanderpetrovF113655.store.exceptions.InsufficientQuantityException;
import org.alexanderpetrovF113655.store.model.*;
import org.alexanderpetrovF113655.store.service.SaleService;
import org.alexanderpetrovF113655.store.service.SaleServiceInterface;
import org.alexanderpetrovF113655.store.util.ReceiptFileManager;

import java.time.LocalDate;
        import java.util.HashMap;
        import java.util.Map;
public class Main {
 public static void main (String[] args){
     Store store = new Store("My Store",
             30.0,
             20.0,
             5,
             15.0);

     Product bread = new FoodProduct(1,"Bread",1.20,LocalDate.now().plusDays(3),20);
     Product soap = new NonFoodProduct(2,"Soap", 2.00,LocalDate.now().plusDays(30),10);
     store.addProduct(bread);
     store.addProduct(soap);

     Cashier cashier = new Cashier(100,"Peter",1200.00);
     store.addCashier(cashier);

     Map<Product, Integer> cart = new HashMap<>();
     cart.put(bread,2);
     cart.put(soap,3);

     SaleServiceInterface saleService = new SaleService(store);
      try{
          Receipt receipt = saleService.processSale(cashier, cart);
          System.out.println(receipt);
          ReceiptFileManager.saveReceiptAsText(receipt);
          ReceiptFileManager.saveReceiptSerialized(receipt);
          Receipt loaded = ReceiptFileManager.loadReceiptSerialized(receipt.getReceiptNumber());
          System.out.println("Receipt loaded file:");
          System.out.println(loaded);
      } catch(InsufficientQuantityException e){
          System.err.println("Not enough quantity:" + e.getMessage());

      } catch(Exception e){
          System.err.println("General error:" + e.getMessage());
      }

     System.out.println("Total revenue: " + store.getTotalRevenue() + "BGN");
     System.out.println("Total expenses: " + store.calculateExpenses() + "BGN");
     System.out.println("Profit: "  + store.calculateProfit() + "BGN");
     System.out.println("\n Attempting to buy more that available");

     Map<Product, Integer> invalidCart = new HashMap<>();
     invalidCart.put(bread,10);
     try{
         saleService.processSale(cashier, invalidCart);
     } catch(InsufficientQuantityException e ){
         System.err.println("Exception caught: " + e.getMessage());
     }
 }

}