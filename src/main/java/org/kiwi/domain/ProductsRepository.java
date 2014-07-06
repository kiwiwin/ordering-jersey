package org.kiwi.domain;

import java.util.List;

public interface ProductsRepository {
    Product getProductById(int productId);

    List<Product> getProducts();
}
