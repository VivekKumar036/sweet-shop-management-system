package com.sweetshop.backend.sweets.controller;

import com.sweetshop.backend.sweets.entity.Sweet;
import com.sweetshop.backend.sweets.service.SweetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sweets")
@CrossOrigin(origins = "http://localhost:3000")
public class SweetController {

    private final SweetService sweetService;

    public SweetController(SweetService sweetService) {
        this.sweetService = sweetService;
    }

    @PostMapping
    public ResponseEntity<Sweet> addSweet(@RequestBody Sweet sweet) {
        return ResponseEntity.ok(sweetService.addSweet(sweet));
    }
    @PostMapping("/{id}/restock")
public ResponseEntity<Sweet> restock(
        @PathVariable Long id,
        @RequestParam int quantity
) {
    return ResponseEntity.ok(sweetService.restockSweet(id, quantity));
}


    @GetMapping
    public ResponseEntity<List<Sweet>> getAllSweets() {
        return ResponseEntity.ok(sweetService.getAllSweets());
    }
    @GetMapping("/search/name")
public ResponseEntity<List<Sweet>> searchByName(@RequestParam String name) {
    return ResponseEntity.ok(sweetService.searchByName(name));
}

@GetMapping("/search/category")
public ResponseEntity<List<Sweet>> searchByCategory(@RequestParam String category) {
    return ResponseEntity.ok(sweetService.searchByCategory(category));
}

@GetMapping("/search/price")
public ResponseEntity<List<Sweet>> searchByPriceRange(
        @RequestParam double min,
        @RequestParam double max
) {
    return ResponseEntity.ok(sweetService.searchByPriceRange(min, max));
}

    @PostMapping("/{id}/purchase")
    public ResponseEntity<Sweet> purchaseSweet(
            @PathVariable Long id,
            @RequestParam int quantity
    ) {
        return ResponseEntity.ok(
                sweetService.purchaseSweet(id, quantity)
        );
    }
}
