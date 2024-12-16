package com.roberto.demo.domain.usecases.impl;

import com.roberto.demo.domain.mappers.PriceEntityDomainModelMapper;
import com.roberto.demo.domain.models.Price;
import com.roberto.demo.domain.usecases.interfaces.GetPrice;
import com.roberto.demo.infra.db.models.PriceEntity;
import com.roberto.demo.infra.db.repositories.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class GetPriceImpl implements GetPrice {

    private final PriceRepository priceRepository;

    public GetPriceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    /**
     * Get price for  given query date , product id and brand id
     * @param brandId brand id to filter
     * @param productId product id to filter
     * @param queryDate query date to filter
     * @return the price
     */
    @Override
    public Price getPrice(Long brandId, Long productId, LocalDateTime queryDate) {
        List<PriceEntity> prices = priceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfter(brandId, productId, queryDate, queryDate);
        PriceEntity price = prices.stream().max(Comparator.comparing(PriceEntity::getPriority)).orElse(null);
        return PriceEntityDomainModelMapper.entityToDomainModel(price);
    }
}
