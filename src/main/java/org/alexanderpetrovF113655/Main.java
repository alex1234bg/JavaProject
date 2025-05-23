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

     Product pasta = new FoodProduct(1,"Pasta",1.20,LocalDate.now().plusDays(6),20);
     Product soap = new NonFoodProduct(2,"Soap", 2.00,LocalDate.now().plusDays(30),10);
     Product milk = new FoodProduct(3,"Milk",1.50, LocalDate.now().plusDays(4),15);
     Product deodorant = new NonFoodProduct(4,"Deodorant", 3.80, LocalDate.now().plusDays(45),20);
     store.addProduct(pasta);
     store.addProduct(soap);
     store.addProduct(milk);
     store.addProduct(deodorant);

     Cashier cashier = new Cashier(100,"Peter",1200.00);
     Cashier cashier2 = new Cashier(101,"Maria",1000.00);
     Cashier cashier3 = new Cashier(102,"George",900.00);
     store.addCashier(cashier);
     store.addCashier(cashier2);
     store.addCashier(cashier3);

     SaleServiceInterface saleService = new SaleService(store);



     Map<Product, Integer> cart = new HashMap<>();
     cart.put(pasta,2);
     cart.put(soap,3);
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
;
      Map<Product, Integer> cart2 = new HashMap<>();
      cart2.put(milk,3);
      cart2.put(deodorant, 2);
      try{
          Receipt receipt2 = saleService.processSale(cashier2, cart2);
          System.out.println(receipt2);
          System.out.println("Cashier: " + cashier2);
          System.out.println("Cart: " + cart2);

          ReceiptFileManager.saveReceiptAsText(receipt2);
          ReceiptFileManager.saveReceiptSerialized(receipt2);
      } catch (Exception e) {
          System.err.println("Error in sale #2: ");
          e.printStackTrace();
      }

      Map<Product, Integer> cart3 = new HashMap<>();
      cart3.put(deodorant,2);
      cart3.put(pasta,1);
     try{
         Receipt receipt3 = saleService.processSale(cashier3, cart3);
         System.out.println(receipt3);
         ReceiptFileManager.saveReceiptAsText(receipt3);
         ReceiptFileManager.saveReceiptSerialized(receipt3);
     } catch (Exception e) {
         System.err.println("Error in sale #3: "+ e.getMessage());
     }
     System.out.println("Total revenue: " + store.getTotalRevenue() + "BGN");
     System.out.println("Total expenses: " + store.calculateExpenses() + "BGN");
     System.out.println("Profit: "  + store.calculateProfit() + "BGN");


     System.out.println("\n Attempting to buy more than available");

     Map<Product, Integer> invalidCart = new HashMap<>();
     invalidCart.put(pasta,30);
     try{
         saleService.processSale(cashier, invalidCart);
     } catch(InsufficientQuantityException e ){
         System.err.println("Exception caught: " + e.getMessage());
     }
 }

}