package com.roberto.demo.ports.rest.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class ErrorDto implements Serializable, Dto {

    @Serial
    private static final long serialVersionUID = -2497167112745246332L;

    private String errorDescription;
    private String field;
    private String aditionalInformation;

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getAditionalInformation() {
        return aditionalInformation;
    }

    public void setAditionalInformation(String aditionalInformation) {
        this.aditionalInformation = aditionalInformation;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ErrorDto errorDto = (ErrorDto) o;
        return Objects.equals(errorDescription, errorDto.errorDescription) && Objects.equals(field,
                errorDto.field) && Objects.equals(aditionalInformation, errorDto.aditionalInformation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorDescription, field, aditionalInformation);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ErrorDto{");
        sb.append("errorDescription='").append(errorDescription).append('\'');
        sb.append(", field='").append(field).append('\'');
        sb.append(", aditionalInformation='").append(aditionalInformation).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
