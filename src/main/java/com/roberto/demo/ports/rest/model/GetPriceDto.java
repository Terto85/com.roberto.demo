package com.roberto.demo.ports.rest.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class GetPriceDto implements Serializable, Dto {

    @Serial
    private static final long serialVersionUID = -4041752740788821142L;

    private Long productId;

    private Long brandId;

    private Long priceList;

    private Float priceValue;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getPriceList() {
        return priceList;
    }

    public void setPriceList(Long priceList) {
        this.priceList = priceList;
    }

    public Float getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(Float priceValue) {
        this.priceValue = priceValue;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        GetPriceDto that = (GetPriceDto) o;
        return Objects.equals(productId, that.productId) && Objects.equals(brandId, that.brandId) && Objects.equals(
                priceList, that.priceList) && Objects.equals(priceValue, that.priceValue) && Objects.equals(startDate,
                that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, brandId, priceList, priceValue, startDate, endDate);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GetPriceDto{");
        sb.append("productId=").append(productId);
        sb.append(", brandId=").append(brandId);
        sb.append(", priceList=").append(priceList);
        sb.append(", priceValue=").append(priceValue);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append('}');
        return sb.toString();
    }
}
