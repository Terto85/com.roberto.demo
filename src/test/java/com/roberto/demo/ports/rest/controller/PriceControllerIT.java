package com.roberto.demo.ports.rest.controller;

import com.roberto.demo.application.DemoApplication;
import com.roberto.demo.domain.usecases.impl.GetPriceImpl;
import com.roberto.demo.factories.PriceFactory;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
class PriceControllerIT {

    private static final String URL = "/price/1/1/";

    private static final String VALID_DATE_TIME = "20200614210000";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    GetPriceImpl getPriceImpl;

    @Test
    void whenGetPriceItsCalledWithCorrectValuesAndFilledResult_thenReturnsOkWithFilledBody() throws Exception {
        Mockito.when(getPriceImpl.getPrice(any(),any(),any())).thenReturn(PriceFactory.getPriceDomainModelForId(1L));
        mockMvc.perform(MockMvcRequestBuilders.get(URL + VALID_DATE_TIME)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{\"productId\":1,\"brandId\":1,\"priceList\":1,\"priceValue\":1.1,\"startDate\":\"0001-01-01 01:01:00\",\"endDate\":\"0001-01-01 01:01:00\"}"))
                .andReturn();
    }


    @Test
    void givenWrongDateTime_thenReturnsBadRequest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get(URL + "WRONG_DATE_TIME")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }

    @Test
    void givenNullResultForQuery_thenReturnsOk()throws Exception{
        Mockito.when(getPriceImpl.getPrice(any(),any(),any())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get(URL + VALID_DATE_TIME)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }


}
