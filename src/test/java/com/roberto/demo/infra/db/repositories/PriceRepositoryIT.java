package com.roberto.demo.infra.db.repositories;

import com.roberto.demo.application.DemoApplicationInfrastructureTestConfig;
import com.roberto.demo.infra.db.models.PriceEntity;
import com.roberto.demo.ports.rest.utils.DateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= DemoApplicationInfrastructureTestConfig.class)
class PriceRepositoryIT {

    private static final Long PRODUCT_ID = 35455L;
    private static final Long BRAND_ID = 1L;
    private static final String CURRENCY = "EUR";

    @Autowired
    private PriceRepository priceRepository;

    @BeforeEach
    void setUp() {
        priceRepository.deleteAll();
        priceRepository.saveAll(pricesData());
    }

    private List<PriceEntity> pricesData(){
        return List.of(
                priceEntityFor(1L, LocalDateTime.of(2020, 6, 14, 0, 0, 0), LocalDateTime.of(2020,12,31,23,59,59), 0L,  35.5F),
                priceEntityFor(2L, LocalDateTime.of(2020, 6, 14, 15, 0, 0), LocalDateTime.of(2020,6,14,18,30,0), 1L,  25.45F),
                priceEntityFor(3L, LocalDateTime.of(2020, 6, 15, 0, 0, 0), LocalDateTime.of(2020,6,15,11,0,0), 1L,  30.50F),
                priceEntityFor(4L, LocalDateTime.of(2020, 6, 15, 16, 0, 0), LocalDateTime.of(2020,12,31,23,59,59), 1L,  38.95F)
        );
    }

    private PriceEntity priceEntityFor(Long priceList, LocalDateTime startDate, LocalDateTime endDate, Long priority, Float priceValue){
        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setPriceList(priceList);
        priceEntity.setBrandId(PriceRepositoryIT.BRAND_ID);
        priceEntity.setStartDate(startDate);
        priceEntity.setEndDate(endDate);
        priceEntity.setProductId(PriceRepositoryIT.PRODUCT_ID);
        priceEntity.setPriority(priority);
        priceEntity.setPriceValue(priceValue);
        priceEntity.setCurrency(PriceRepositoryIT.CURRENCY);
        return priceEntity;
    }

    @ParameterizedTest
    @MethodSource("valuesAndResults")
    void givenDate_thenReturnExpectedValues(String dateTimeStr, List<Long> results) {
        LocalDateTime queryDateTime = DateUtils.parseUrlDateTime(dateTimeStr);
        List<PriceEntity> priceEntities = priceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfter(BRAND_ID, PRODUCT_ID, queryDateTime,queryDateTime );
        List<Long> pricesLists = priceEntities.stream().map(PriceEntity::getPriceList).toList();
        Assertions.assertEquals(pricesLists, results);
    }

    private static Stream<Arguments> valuesAndResults() {
        return Stream.of(
                Arguments.of("20200613000000",List.of() ),
                Arguments.of("20200614100000",List.of(1L)),
                Arguments.of("20200614160000",List.of(1L, 2L)),
                Arguments.of("20200614210000",List.of(1L)),
                Arguments.of("20200615100000",List.of(1L, 3L)),
                Arguments.of("20200616210000",List.of(1L, 4L)),
                Arguments.of("20210101000000",List.of())
        );
    }


}