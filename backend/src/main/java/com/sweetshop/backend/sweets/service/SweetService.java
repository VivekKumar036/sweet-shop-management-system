package com.sweetshop.backend.sweets.service;

import com.sweetshop.backend.sweets.entity.Sweet;
import com.sweetshop.backend.sweets.repository.SweetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SweetService {

    private final SweetRepository sweetRepository;

    public SweetService(SweetRepository sweetRepository) {
        this.sweetRepository = sweetRepository;
    }

    // ---------------- BASIC ----------------

    public Sweet addSweet(Sweet sweet) {
        return sweetRepository.save(sweet);
    }

    public List<Sweet> getAllSweets() {
        return sweetRepository.findAll();
    }

    public Sweet getSweetById(Long id) {
        return sweetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sweet not found"));
    }

    // ---------------- PURCHASE ----------------

    public Sweet purchaseSweet(Long sweetId, int quantity) {
        Sweet sweet = getSweetById(sweetId);

        if (sweet.getQuantityInStock() < quantity) {
            throw new IllegalArgumentException("Insufficient stock available");
        }

        // ✅ MODIFY EXISTING ENTITY
        sweet.setQuantityInStock(
                sweet.getQuantityInStock() - quantity
        );

        return sweetRepository.save(sweet);
    }

    // ---------------- ADMIN ----------------

    public void deleteSweet(Long sweetId) {
        if (!sweetRepository.existsById(sweetId)) {
            throw new IllegalArgumentException("Sweet not found");
        }
        sweetRepository.deleteById(sweetId);
    }

    public Sweet restockSweet(Long sweetId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Restock quantity must be positive");
        }

        Sweet sweet = getSweetById(sweetId);

        // ✅ MODIFY EXISTING ENTITY
        sweet.setQuantityInStock(
                sweet.getQuantityInStock() + quantity
        );

        return sweetRepository.save(sweet);
    }

    // ---------------- SEARCH ----------------

    public List<Sweet> searchByName(String name) {
        return sweetRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Sweet> searchByCategory(String category) {
        return sweetRepository.findByCategoryIgnoreCase(category);
    }

    public List<Sweet> searchByPriceRange(double minPrice, double maxPrice) {
        return sweetRepository.findByPriceBetween(minPrice, maxPrice);
    }
}
