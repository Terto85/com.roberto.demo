package com.roberto.demo.ports.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.roberto.demo.domain.usecases.impl.GetPriceImpl;
import com.roberto.demo.ports.rest.exceptions.NotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.time.format.DateTimeParseException;

import static com.roberto.demo.factories.PriceFactory.getPriceDomainModelForId;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class PriceControllerTest {

    private static final String VALID_DATE_TIME = "20241231235959";

    @Mock
    GetPriceImpl getPriceImpl;

    private PriceController sut;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        sut = new PriceController(getPriceImpl);
    }

    @AfterEach
    void afterEach() throws Exception{
        closeable.close();
    }

    @Test
    void givenWrongDateTime_thenExceptionIsThrown() {
        assertThrows(DateTimeParseException.class, () -> {
            sut.getPrice(1L, 1L, "wrongDateTime");
        });
    }

    @Test
    void givenNullResultForQuery_thenReturnsNotFoundException(){
        Mockito.when(getPriceImpl.getPrice(any(), any(), any())).thenReturn(null);
        assertThrows(NotFoundException.class, () -> {
            sut.getPrice(1L, 1L, VALID_DATE_TIME);
        });
    }

    @Test
    void givenFilledResultForQuery_thenReturnsCorrectPrice() throws NotFoundException, JsonProcessingException {
        Mockito.when(getPriceImpl.getPrice(any(), any(), any())).thenReturn(getPriceDomainModelForId(1L));
        String priceJson = sut.getPrice(1L, 1L, VALID_DATE_TIME);
        Assertions.assertEquals("{\"productId\":1,\"brandId\":1,\"priceList\":1,\"priceValue\":1.1,\"startDate\":\"0001-01-01 01:01:00\",\"endDate\":\"0001-01-01 01:01:00\"}",
                priceJson);
    }

}