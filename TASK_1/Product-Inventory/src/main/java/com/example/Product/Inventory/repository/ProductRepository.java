package com.example.Product.Inventory.repository;

import com.example.Product.Inventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
