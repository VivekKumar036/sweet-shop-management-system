package com.sweetshop.backend.sweets.service;

import com.sweetshop.backend.sweets.entity.Sweet;
import com.sweetshop.backend.sweets.repository.SweetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SweetServiceTest {

    private SweetRepository sweetRepository;
    private SweetService sweetService;

    @BeforeEach
    void setUp() {
        sweetRepository = mock(SweetRepository.class);
        sweetService = new SweetService(sweetRepository);
    }

    @Test
    void shouldAddNewSweetSuccessfully() {
        Sweet sweet = new Sweet("Rasgulla", "Indian", 20.0, 50);

        when(sweetRepository.save(any(Sweet.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Sweet savedSweet = sweetService.addSweet(sweet);

        assertEquals("Rasgulla", savedSweet.getName());
        assertEquals(50, savedSweet.getQuantityInStock());
    }

    @Test
    void shouldReturnAllAvailableSweets() {
        when(sweetRepository.findAll()).thenReturn(
                List.of(
                        new Sweet("Ladoo", "Indian", 10.0, 30),
                        new Sweet("Barfi", "Indian", 15.0, 20)
                )
        );

        List<Sweet> sweets = sweetService.getAllSweets();

        assertEquals(2, sweets.size());
    }

    @Test
    void shouldReduceQuantityWhenSweetIsPurchased() {
        Sweet sweet = new Sweet("Jalebi", "Indian", 12.0, 5);

        when(sweetRepository.findById(1L))
                .thenReturn(java.util.Optional.of(sweet));
        when(sweetRepository.save(any(Sweet.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Sweet updatedSweet = sweetService.purchaseSweet(1L, 2);

        assertEquals(3, updatedSweet.getQuantityInStock());
    }

    @Test
    void shouldThrowExceptionWhenStockIsInsufficient() {
        Sweet sweet = new Sweet("Peda", "Indian", 25.0, 1);

        when(sweetRepository.findById(1L))
                .thenReturn(java.util.Optional.of(sweet));

        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> sweetService.purchaseSweet(1L, 2)
        );

        assertEquals("Insufficient stock available", exception.getMessage());
    }

    @Test
    void shouldRestockSweetSuccessfully() {
        Sweet sweet = new Sweet("Kaju Katli", "Indian", 40.0, 10);

        when(sweetRepository.findById(1L))
                .thenReturn(java.util.Optional.of(sweet));
        when(sweetRepository.save(any(Sweet.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Sweet updatedSweet = sweetService.restockSweet(1L, 5);

        assertEquals(15, updatedSweet.getQuantityInStock());
    }

    @Test
    void shouldDeleteSweetSuccessfully() {
        when(sweetRepository.existsById(1L)).thenReturn(true);
        doNothing().when(sweetRepository).deleteById(1L);

        assertDoesNotThrow(() -> sweetService.deleteSweet(1L));
    }
}
