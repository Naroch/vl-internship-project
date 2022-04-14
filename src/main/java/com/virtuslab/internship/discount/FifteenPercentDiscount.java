package com.virtuslab.internship.discount;

import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;

import java.math.BigDecimal;
import java.util.ListIterator;

public class FifteenPercentDiscount {

    public static String NAME = "FifteenPercentDiscount";

    public Receipt apply(Receipt receipt) {
        if (shouldApply(receipt)) {
            var totalPrice = receipt.totalPrice().multiply(BigDecimal.valueOf(0.85));
            var discounts = receipt.discounts();
            discounts.add(NAME);
            return new Receipt(receipt.entries(), discounts, totalPrice);
        }
        return receipt;
    }

    private boolean shouldApply(Receipt receipt) {
        int i = 0;
        var listToCheck = receipt.entries();
        ListIterator<ReceiptEntry> iterator = listToCheck.listIterator();
        while(iterator.hasNext()) {
            var temp = iterator.next();
            if(temp.product().type().equals(Product.Type.GRAINS))
                i = i + temp.quantity();
        }
        return i >= 3;
    }
}
