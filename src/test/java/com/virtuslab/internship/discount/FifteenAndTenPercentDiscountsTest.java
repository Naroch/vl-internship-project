package com.virtuslab.internship.discount;

import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FifteenAndTenPercentDiscountsTest {

    @Test
    void shouldApply15And10PercentDiscounts() {
        // Given
        var productDb = new ProductDb();
        var bread = productDb.getProduct("Bread");
        var cereals = productDb.getProduct("Cereals");
        var steak = productDb.getProduct("Steak");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(bread, 2));
        receiptEntries.add(new ReceiptEntry(cereals, 1));
        receiptEntries.add(new ReceiptEntry(steak, 2));

        var receipt = new Receipt(receiptEntries);
        var discount15Percent = new FifteenPercentDiscount();
        var discount10Percent = new TenPercentDiscount();
        var expectedTotalPrice = steak.price().multiply(BigDecimal.valueOf(2)).add(bread.price().multiply(BigDecimal.valueOf(2)))
                .add(cereals.price()).multiply(BigDecimal.valueOf(0.85)).multiply(BigDecimal.valueOf(0.9));

        // When
        var receiptAfterDiscount15 = discount15Percent.apply(receipt);
        var receiptAfterDiscount10 = discount10Percent.apply(receiptAfterDiscount15);

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscount10.totalPrice());
        assertEquals(2, receiptAfterDiscount10.discounts().size());
    }

    @Test
    void shouldApplyOnly15PercentDiscount() {
        // Given
        var productDb = new ProductDb();
        var bread = productDb.getProduct("Bread");
        var cereals = productDb.getProduct("Cereals");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(bread, 2));
        receiptEntries.add(new ReceiptEntry(cereals, 1));


        var receipt = new Receipt(receiptEntries);
        var discount15Percent = new FifteenPercentDiscount();
        var discount10Percent = new TenPercentDiscount();
        var expectedTotalPrice = bread.price().multiply(BigDecimal.valueOf(2))
                .add(cereals.price()).multiply(BigDecimal.valueOf(0.85));

        // When
        var receiptAfterDiscount15 = discount15Percent.apply(receipt);
        var receiptAfterDiscount10 = discount10Percent.apply(receiptAfterDiscount15);

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscount10.totalPrice());
        assertEquals(1, receiptAfterDiscount10.discounts().size());
    }

    @Test
    void shouldApplyOnly10PercentDiscount() {
        // Given
        var productDb = new ProductDb();
        var bread = productDb.getProduct("Bread");
        var steak = productDb.getProduct("Steak");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(bread, 2));
        receiptEntries.add(new ReceiptEntry(steak, 2));

        var receipt = new Receipt(receiptEntries);
        var discount15Percent = new FifteenPercentDiscount();
        var discount10Percent = new TenPercentDiscount();
        var expectedTotalPrice = steak.price().multiply(BigDecimal.valueOf(2)).add(bread.price()
                .multiply(BigDecimal.valueOf(2))).multiply(BigDecimal.valueOf(0.9));

        // When
        var receiptAfterDiscount15 = discount15Percent.apply(receipt);
        var receiptAfterDiscount10 = discount10Percent.apply(receiptAfterDiscount15);

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscount10.totalPrice());
        assertEquals(1, receiptAfterDiscount10.discounts().size());
    }

    @Test
    void shouldNotApplyAnyDiscount() {
        // Given
        var productDb = new ProductDb();
        var bread = productDb.getProduct("Bread");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(bread, 2));

        var receipt = new Receipt(receiptEntries);
        var discount15Percent = new FifteenPercentDiscount();
        var discount10Percent = new TenPercentDiscount();
        var expectedTotalPrice = bread.price().multiply(BigDecimal.valueOf(2));


        // When
        var receiptAfterDiscount15 = discount15Percent.apply(receipt);
        var receiptAfterDiscount10 = discount10Percent.apply(receiptAfterDiscount15);

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscount10.totalPrice());
        assertEquals(0, receiptAfterDiscount10.discounts().size());
    }

    @Test
    void shouldApplyOnly15PercentDiscountWhenPriceStarts50AndEndsBelow50() {
        // Given
        var productDb = new ProductDb();
        var bread = productDb.getProduct("Bread");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(bread, 10));


        var receipt = new Receipt(receiptEntries);
        var discount15Percent = new FifteenPercentDiscount();
        var discount10Percent = new TenPercentDiscount();
        var expectedTotalPrice = bread.price().multiply(BigDecimal.valueOf(10))
                .multiply(BigDecimal.valueOf(0.85));

        // When
        var receiptAfterDiscount15 = discount15Percent.apply(receipt);
        var receiptAfterDiscount10 = discount10Percent.apply(receiptAfterDiscount15);

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscount10.totalPrice());
        assertEquals(1, receiptAfterDiscount10.discounts().size());
    }
}
