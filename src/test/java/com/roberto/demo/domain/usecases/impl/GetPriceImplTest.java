package com.roberto.demo.domain.usecases.impl;

import com.roberto.demo.domain.models.Price;
import com.roberto.demo.factories.PriceFactory;
import com.roberto.demo.infra.db.models.PriceEntity;
import com.roberto.demo.infra.db.repositories.PriceRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static com.roberto.demo.factories.PriceFactory.getPriceEntityForId;
import static org.mockito.ArgumentMatchers.any;

class GetPriceImplTest {

    @Mock
    private PriceRepository priceRepository;

    private GetPriceImpl getPriceImpl;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        getPriceImpl = new GetPriceImpl(priceRepository);
    }

    @AfterEach
    void tearDown() throws Exception{
        closeable.close();
    }

    @Nested
    class GetPriceMethod{
        @Test
        void givenCall_thenUseArgumentsToCallRepository(){
            LocalDateTime localDateTime = LocalDateTime.of(1, 1, 1, 1, 1);

            getPriceImpl.getPrice(1L, 1L, localDateTime);
            Mockito.verify(priceRepository, Mockito.times(1))
                    .findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfter(1L, 1L, localDateTime, localDateTime);
        }

        @Test
        void givenCallForMultipleResult_thenReturnTheOneWithMorePriority() {
            LocalDateTime localDateTime = LocalDateTime.of(1, 1, 1, 1, 1);

            Mockito.when(priceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfter(any(), any(),any(), any()))
                    .thenReturn(List.of(getPriceEntityForId(1L), getPriceEntityForId(2L), getPriceEntityForId(3L)));

            Price price = getPriceImpl.getPrice(1L, 1L, localDateTime);
            Assertions.assertEquals(3L, price.getPriceList());
        }

        @Test
        void givenCallForSingleResult_thenReturnThatOne() {
            LocalDateTime localDateTime = LocalDateTime.of(1, 1, 1, 1, 1);

            Mockito.when(priceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfter(any(), any(),any(), any()))
                    .thenReturn(List.of(getPriceEntityForId(1L)));

            Price price = getPriceImpl.getPrice(1L, 1L, localDateTime);
            Assertions.assertEquals(1L, price.getPriceList());
        }

        @Test
        void givenCallForEmptyResult_thenReturnNull() {
            LocalDateTime localDateTime = LocalDateTime.of(1, 1, 1, 1, 1);

            Mockito.when(priceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfter(any(), any(),any(), any()))
                    .thenReturn(List.of());

            Price price = getPriceImpl.getPrice(1L, 1L, localDateTime);
            Assertions.assertNull(price);
        }

        @Test
        void givenCallForAConcreteResult_thenReturnDomainModelOfTheResult() {
            LocalDateTime localDateTime = LocalDateTime.of(1, 1, 1, 1, 1);

            Mockito.when(priceRepository.findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfter(any(), any(),any(), any()))
                    .thenReturn(List.of(getPriceEntityForId(1L)));

            Price expectedPriceDomainModel = PriceFactory.getPriceDomainModelForId(1L);
            Price resultDomainModel = getPriceImpl.getPrice(1L, 1L, localDateTime);
            Assertions.assertEquals(expectedPriceDomainModel, resultDomainModel);
        }


    }

}