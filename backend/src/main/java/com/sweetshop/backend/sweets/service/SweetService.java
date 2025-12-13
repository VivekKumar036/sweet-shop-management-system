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

    public Sweet purchaseSweet(Sweet sweet, int quantity) {

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
}
