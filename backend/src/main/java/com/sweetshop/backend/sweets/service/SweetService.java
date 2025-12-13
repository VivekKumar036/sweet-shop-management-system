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

    public Sweet purchaseSweet(Long sweetId, int quantity) {
        Sweet sweet = getSweetById(sweetId);

        if (sweet.getQuantityInStock() < quantity) {
            throw new IllegalArgumentException("Insufficient stock available");
        }

        Sweet updatedSweet = new Sweet(
                sweet.getName(),
                sweet.getCategory(),
                sweet.getPrice(),
                sweet.getQuantityInStock() - quantity
        );

        return sweetRepository.save(updatedSweet);
    }
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
