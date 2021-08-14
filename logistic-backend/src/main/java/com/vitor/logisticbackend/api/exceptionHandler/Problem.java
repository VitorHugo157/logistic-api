package com.vitor.logisticbackend.api.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.*;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
public class Problem {

    private Integer status;
    private OffsetDateTime dateTime;
    private String title;
    private List<Field> fields;

    @AllArgsConstructor
    @Getter
    public static class Field {

        private String field;
        private String message;
    }
}
