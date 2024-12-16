package com.roberto.demo.factories;

import com.roberto.demo.domain.models.Price;
import com.roberto.demo.infra.db.models.PriceEntity;

import java.time.LocalDateTime;

public class PriceFactory {

    private PriceFactory(){}

    public static PriceEntity getPriceEntityForId(Long id ){
        LocalDateTime localDateTime = LocalDateTime.of(1, 1, 1, 1, 1);

        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setPriceList(id);
        priceEntity.setBrandId(id);
        priceEntity.setStartDate(localDateTime);
        priceEntity.setEndDate(localDateTime);
        priceEntity.setProductId(id);
        priceEntity.setPriority(id);
        priceEntity.setPriceValue(id + 0.1F);
        priceEntity.setCurrency("EUR");
        return priceEntity;
    }
    public static Price getPriceDomainModelForId(Long id ){
        LocalDateTime localDateTime = LocalDateTime.of(1, 1, 1, 1, 1);

        Price priceDomainModel = new Price();
        priceDomainModel.setPriceList(id);
        priceDomainModel.setBrandId(id);
        priceDomainModel.setStartDate(localDateTime);
        priceDomainModel.setEndDate(localDateTime);
        priceDomainModel.setProductId(id);
        priceDomainModel.setPriority(id);
        priceDomainModel.setPriceValue(id + 0.1F);
        priceDomainModel.setCurrency("EUR");
        return priceDomainModel;
    }
}
