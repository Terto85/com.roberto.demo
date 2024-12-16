package com.roberto.demo.ports.rest.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.roberto.demo.ports.rest.model.Dto;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

/**
 * Demo application json mapper, used to normalize json mapping on the application
 */
public class DemoJsonMapper {

    protected static final String JACKSON_TIME_MODULE = "jackson-LocalDateTime";

    private final ObjectMapper mapper = new ObjectMapper();



    public DemoJsonMapper() {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.registerModule(new JavaTimeModule());

            final SimpleModule module = new SimpleModule(JACKSON_TIME_MODULE, Version.unknownVersion());

            module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateUtils.jsonDateTimeFormatter()));
            module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateUtils.jsonDateTimeFormatter()));

            mapper.registerModule(module);
    }

    public String mapToJson(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }

    public String mapAsGetResponse(Dto obj) throws JsonProcessingException {
        if(obj == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Not found");
        }
        return mapToJson(obj);
    }

}
