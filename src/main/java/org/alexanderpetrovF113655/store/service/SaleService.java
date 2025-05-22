package org.alexanderpetrovF113655.store.service;
import org.alexanderpetrovF113655.store.exceptions.InsufficientQuantityException;
import org.alexanderpetrovF113655.store.model.Cashier;
        import org.alexanderpetrovF113655.store.model.Product;
        import org.alexanderpetrovF113655.store.model.Receipt;
        import org.alexanderpetrovF113655.store.model.Store;

import java.time.LocalDate;
import java.util.Map;
public class SaleService implements SaleServiceInterface {
    private Store store;
    public SaleService(Store store){this.store = store;}
    public Receipt processSale(Cashier cashier, Map<Product , Integer> itemsToBuy)

        throws InsufficientQuantityException{
            Receipt receipt = new Receipt(Store.getNextReceiptNumber(), cashier);
LocalDate today = LocalDate.now();
for(Map.Entry<Product, Integer> entry : itemsToBuy.entrySet()){
    Product product = entry.getKey();
    int requestedQuantity = entry.getValue();
    if(product.isExpired(today)){
        continue;

    }
    if(product.getQuantity()< requestedQuantity){
        throw new InsufficientQuantityException(
                "Not enough:" + product.getName() + "(available:"+product.getQuantity() + ", requested" + requestedQuantity + ")"
        );
    }
    double markup = store.getMarkupPercent(product.getCategory());
    double unitPrice = product.calculateSellingPrice( today,markup, store.getDiscountDaysThreshold(), store.getDiscountPercent());
    receipt.addProduct(product, requestedQuantity, unitPrice);
    product.setQuantity(product.getQuantity() - requestedQuantity);
}

store.addReceipt(receipt);
return receipt;
    }

}
