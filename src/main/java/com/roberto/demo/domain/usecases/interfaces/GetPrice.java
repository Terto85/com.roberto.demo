package com.roberto.demo.domain.usecases.interfaces;

import com.roberto.demo.domain.models.Price;

import java.time.LocalDateTime;

public interface GetPrice {
    Price getPrice(Long brandId, Long productId, LocalDateTime queryDate);
}
