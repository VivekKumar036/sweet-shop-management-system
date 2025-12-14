package com.sweetshop.backend.sweets.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sweets")
public class Sweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int quantityInStock;

    // 🔹 Required by JPA
    public Sweet() {
    }

    public Sweet(String name, String category, double price, int quantityInStock) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    // -------- GETTERS --------

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    // -------- SETTERS (CRITICAL) --------

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
}
