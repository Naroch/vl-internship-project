package com.virtuslab.internship.productdb;

import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.product.ProductDb;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class ProductDbTest {

    @Test
    void productShouldBeInDB() {
        var dB = new ProductDb();
        var product = new Product("Cereals", Product.Type.GRAINS,new BigDecimal(8));

        assertTrue(dB.productDbContains(product));
    }
}
