package com.internet.shop.dao.implementations;

import com.internet.shop.dao.interfaces.ProductDao;
import com.internet.shop.db.Storage;
import com.internet.shop.model.Product;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class ProductDaoImpl implements ProductDao {
    @Override
    public Product create(Product product) {
        Storage.addProduct(product);
        return product;
    }

    @Override
    public Optional<Product> get(Long id) {
        return Storage.products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Product> getAll() {
        return Storage.products;
    }

    @Override
    public Product update(Product product) {
        IntStream.range(0, Storage.products.size())
                .filter(index -> Storage.products.get(index)
                        .getId().equals(product.getId()))
                .forEach(index -> Storage.products.set(index, product));
        return product;
    }

    @Override
    public boolean deleteById(Long id) {
        return Storage.products
                .removeIf(product -> product.getId().equals(id));
    }

    @Override
    public boolean delete(Product product) {
        return deleteById(product.getId());
    }
}
