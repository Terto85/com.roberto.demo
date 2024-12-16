package com.roberto.demo.ports.rest.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {

    public static final String URL_PATTERN = "yyyyMMddHHmmss";
    public static final String JSON_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private DateUtils(){}

    public static LocalDateTime parseUrlDateTime(String dateTimeString){
        return LocalDateTime.parse(dateTimeString, urlDateTimeFormatter());
    }

    public static LocalDateTime parseJsonDateTime(String dateTimeString) throws DateTimeParseException {
        return LocalDateTime.parse(dateTimeString, jsonDateTimeFormatter());
    }

    public static DateTimeFormatter urlDateTimeFormatter(){
        return DateTimeFormatter.ofPattern(URL_PATTERN);
    }
    public static DateTimeFormatter jsonDateTimeFormatter(){
        return DateTimeFormatter.ofPattern(JSON_PATTERN);
    }
}
