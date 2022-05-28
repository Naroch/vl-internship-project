package com.virtuslab.internship.receipt;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReceiptGenerator {

    public Receipt generate(Basket basket) {
//        List<ReceiptEntry> receiptEntries = new ArrayList<>();
//        var products = basket.getProducts();
//        List<Product> copy = new ArrayList<>();
//        copy.addAll(products);
//        while(copy.size()>0) {
//            int i = 0;
//            var temp = copy.get(0);
//            while(copy.remove(temp))
//                i++;
//            receiptEntries.add(new ReceiptEntry(temp, i));
//        }
//        return new Receipt(receiptEntries);

        var receiptEntries = basket.getProducts().stream()
                .collect(Collectors.groupingBy(Function.identity() , Collectors.counting()));
        return new Receipt(
                receiptEntries.entrySet().stream()
                        .map(a -> new ReceiptEntry(a.getKey(),a.getValue().intValue()))
                        .toList()
        );
    }
}
