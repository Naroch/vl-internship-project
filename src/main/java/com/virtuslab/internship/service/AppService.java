package com.virtuslab.internship.service;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.discount.FifteenPercentDiscount;
import com.virtuslab.internship.discount.TenPercentDiscount;
import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class AppService {

    private ProductDb productDb;
    private Basket basket;

    public AppService(ProductDb productDb, Basket basket) {
        this.productDb = productDb;
        this.basket = basket;
    }

    public Set<Product> getProducts() { return productDb.getProducts(); }

    public List<Product> getBasket() { return basket.getProducts(); }

    public Product addToBasket(String productName) {
        try {
            Product product = productDb.getProduct(productName);
            basket.addProduct(product);
            return product;
        } catch (NoSuchElementException a) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Product Not Found", a);
        }
    }

    public Receipt generateReceipt() {
        var receiptGenerator = new ReceiptGenerator();
        var discount15Percent = new FifteenPercentDiscount();
        var discount10Percent = new TenPercentDiscount();

        var receipt = receiptGenerator.generate(basket);
        var receiptAfterDiscount15 = discount15Percent.apply(receipt);
        var receiptAfterDiscount10 = discount10Percent.apply(receiptAfterDiscount15);
        return receiptAfterDiscount10;
    }
}
