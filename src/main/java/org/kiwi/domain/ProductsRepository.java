package org.kiwi.domain;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductsRepository {
    Product getProductById(@Param("productId") int productId);

    List<Product> getProducts();

    void createProduct(@Param("product") Product product);
}
