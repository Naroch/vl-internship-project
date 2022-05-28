package com.virtuslab.internship.basket;

import com.virtuslab.internship.product.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BasketTest {
    @Autowired
    Basket basket1;

    @Autowired
    Basket basket2;

    @Test
    void shouldGenerateBasket() {


        var cereal = new Product("Cereals", Product.Type.GRAINS,new BigDecimal(8));
        var orange = new Product("Orange", Product.Type.FRUITS, new BigDecimal(5));
        var banana = new Product("Banana", Product.Type.FRUITS, new BigDecimal("4.4"));
        List<Product> list = new ArrayList<>();
        list.add(cereal);
        list.add(orange);
        list.add(banana);

        basket1.addProduct(cereal);
        basket1.addProduct(orange);
        basket1.addProduct(banana);

        basket2.generateBasket(list);

        assertEquals(basket1.getProducts(), basket2.getProducts());
    }

    @Test
    void generateBasketShouldThrowError() {

        var cereal = new Product("Cereals2", Product.Type.GRAINS,new BigDecimal(8));
        var orange = new Product("Orange", Product.Type.FRUITS, new BigDecimal(5));
        var banana = new Product("Banana", Product.Type.FRUITS, new BigDecimal("4.4"));
        List<Product> list = new ArrayList<>();
        list.add(cereal);
        list.add(orange);
        list.add(banana);

        basket1.addProduct(cereal);
        basket1.addProduct(orange);
        basket1.addProduct(banana);

        basket2.generateBasket(list);

        assertEquals(basket1.getProducts(), basket2.getProducts());
    }
}
