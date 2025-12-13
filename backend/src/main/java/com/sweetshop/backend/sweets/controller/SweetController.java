package com.sweetshop.backend.sweets.controller;

import com.sweetshop.backend.sweets.entity.Sweet;
import com.sweetshop.backend.sweets.service.SweetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sweets")
public class SweetController {

    private final SweetService sweetService;

    public SweetController(SweetService sweetService) {
        this.sweetService = sweetService;
    }

    // Add a new sweet
    @PostMapping
    public ResponseEntity<Sweet> addSweet(@RequestBody Sweet sweet) {
        Sweet savedSweet = sweetService.addSweet(sweet);
        return ResponseEntity.ok(savedSweet);
    }

    // Get all sweets
    @GetMapping
    public ResponseEntity<List<Sweet>> getAllSweets() {
        return ResponseEntity.ok(sweetService.getAllSweets());
    }

    // Purchase a sweet (reduce quantity)
    @PostMapping("/{id}/purchase")
    public ResponseEntity<Sweet> purchaseSweet(
            @PathVariable Long id,
            @RequestParam int quantity
    ) {
        // For now, we’ll assume sweet is already fetched (we’ll improve this next)
        // This keeps things simple and readable
        Sweet sweet = sweetService.getAllSweets()
                .stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Sweet not found"));

        Sweet updatedSweet = sweetService.purchaseSweet(sweet, quantity);
        return ResponseEntity.ok(updatedSweet);
    }
}
