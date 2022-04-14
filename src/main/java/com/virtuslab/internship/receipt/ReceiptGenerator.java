package com.virtuslab.internship.receipt;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.product.Product;

import java.util.ArrayList;
import java.util.List;

public class ReceiptGenerator {

    public Receipt generate(Basket basket) {
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        var products = basket.getProducts();
        List<Product> copy = new ArrayList<>();
        copy.addAll(products);
        while(copy.size()>0) {
            int i = 0;
            var temp = copy.get(0);
            while(copy.remove(temp))
                i++;
            receiptEntries.add(new ReceiptEntry(temp, i));
        }
        return new Receipt(receiptEntries);
    }
}
