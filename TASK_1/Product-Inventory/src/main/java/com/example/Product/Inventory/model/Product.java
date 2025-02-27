package com.example.Product.Inventory.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PRODUCT")  // ✅ Ensure the table name is explicitly defined
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private double price;
    private int quantity;
}
