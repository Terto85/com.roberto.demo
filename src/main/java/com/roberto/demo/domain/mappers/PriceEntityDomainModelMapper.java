package com.roberto.demo.domain.mappers;

import com.roberto.demo.domain.models.Price;
import com.roberto.demo.infra.db.models.PriceEntity;

public class PriceEntityDomainModelMapper {

    private PriceEntityDomainModelMapper() {}

    public static Price entityToDomainModel(PriceEntity priceEntity) {
        if(priceEntity == null) return null;
        Price domainModel = new Price();
        domainModel.setPriceValue(priceEntity.getPriceValue());
        domainModel.setCurrency(priceEntity.getCurrency());
        domainModel.setBrandId(priceEntity.getBrandId());
        domainModel.setPriceList(priceEntity.getPriceList());
        domainModel.setStartDate(priceEntity.getStartDate());
        domainModel.setEndDate(priceEntity.getEndDate());
        domainModel.setProductId(priceEntity.getProductId());
        domainModel.setPriority(priceEntity.getPriority());
        return domainModel;
    }
}
