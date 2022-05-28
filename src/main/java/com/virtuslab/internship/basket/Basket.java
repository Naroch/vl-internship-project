package com.virtuslab.internship.basket;

import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.product.ProductDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
@Scope("prototype")
public class Basket {

    @Autowired
    private ProductDb productDb;

    private final List<Product> products;

    public Basket() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void generateBasket(List<Product> listProduct) throws NoSuchElementException {

        var optional = listProduct.stream().filter(a -> !productDb.productDbContains(a)).findFirst();
        if(optional.isPresent()) {
            throw new NoSuchElementException(optional.get().name());
        } else {
            products.addAll(listProduct);
        }
    }
}
