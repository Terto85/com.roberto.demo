package com.roberto.demo.ports.rest.mappers;

import com.roberto.demo.domain.models.Price;
import com.roberto.demo.ports.rest.model.GetPriceDto;

public class PriceDomainModelDtoMapper {

    private PriceDomainModelDtoMapper() {}

    public static GetPriceDto domainModelToDto (Price priceEntity) {
        if(priceEntity == null) return null;
        GetPriceDto dto = new GetPriceDto();
        dto.setPriceValue(priceEntity.getPriceValue());
        dto.setBrandId(priceEntity.getBrandId());
        dto.setPriceList(priceEntity.getPriceList());
        dto.setStartDate(priceEntity.getStartDate());
        dto.setEndDate(priceEntity.getEndDate());
        dto.setProductId(priceEntity.getProductId());
        return dto;
    }
}
