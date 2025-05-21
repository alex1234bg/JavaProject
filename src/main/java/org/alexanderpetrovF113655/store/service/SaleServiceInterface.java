package org.alexanderpetrovF113655.store.service;
import org.alexanderpetrovF113655.store.exceptions.InsufficientQuantityException;
import org.alexanderpetrovF113655.store.model.*;
import java.util.Map;

public interface SaleServiceInterface {
    Receipt processSale(Cashier cashier, Map<Product, Integer>itemsToBuy)
        throws InsufficientQuantityException;
}
