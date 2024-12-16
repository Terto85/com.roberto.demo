package com.roberto.demo.ports.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.roberto.demo.domain.models.Price;
import com.roberto.demo.domain.usecases.impl.GetPriceImpl;
import com.roberto.demo.ports.rest.exceptions.NotFoundException;
import com.roberto.demo.ports.rest.mappers.PriceDomainModelDtoMapper;
import com.roberto.demo.ports.rest.model.ErrorDto;
import com.roberto.demo.ports.rest.model.GetPriceDto;
import com.roberto.demo.ports.rest.utils.DateUtils;
import com.roberto.demo.ports.rest.utils.DemoJsonMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping(value = "/price", produces = MediaType.APPLICATION_JSON_VALUE)
public class PriceController {

    private final GetPriceImpl getPriceImpl;

    private final DemoJsonMapper demoJsonMapper;

    public PriceController(GetPriceImpl getPriceImpl) {
        this.getPriceImpl = getPriceImpl;
        this.demoJsonMapper = new DemoJsonMapper();
    }

    @GetMapping("/{brandId}/{productId}/{dateTime}")
    @Operation(summary = "Retrieves the corresponding price for a given brand, product and moment.")
    @ResponseBody
    public String getPrice(@PathVariable Long brandId, @PathVariable Long productId, @PathVariable String dateTime)
            throws JsonProcessingException, DateTimeParseException, NotFoundException {
        LocalDateTime parsedDateTime = DateUtils.parseUrlDateTime(dateTime);
        Price price = getPriceImpl.getPrice(brandId, productId,parsedDateTime );
        if(price == null) {
            throw new NotFoundException();
        }else{
            GetPriceDto priceDto = PriceDomainModelDtoMapper.domainModelToDto(price);
            return demoJsonMapper.mapAsGetResponse(priceDto);
        }
    }

    @ExceptionHandler(DateTimeParseException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    private String onWrongDateFormat(DateTimeParseException e) throws JsonProcessingException {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setErrorDescription("Wrong date format");
        errorDto.setField(e.getParsedString());
        errorDto.setAditionalInformation(MessageFormat.format("Fail at index: {0}", e.getErrorIndex()));
        return demoJsonMapper.mapAsGetResponse(errorDto);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ApiResponse(description = "No information found")
    private void onNotFound()  { // Empty method for api documentation purposes
    }
}
