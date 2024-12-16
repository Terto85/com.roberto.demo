package com.roberto.demo.infra.db.repositories;

import com.roberto.demo.infra.db.models.PriceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends CrudRepository<PriceEntity, Long> {

    /**
      * Get price for  given query date , product id and brand id
     */
    List<PriceEntity> findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfter(Long brandId, Long productId, LocalDateTime startQueryDate, LocalDateTime endQueryDate);
}
