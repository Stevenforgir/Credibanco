package com.credibanco.assessment.card.exceptions;

import com.credibanco.assessment.card.dto.CardDtoResponse;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ErrorMessage {
    private String exception;
    private String message;
    private String path;

    public ErrorMessage(Exception exception, String path) {
        this.message = exception.getMessage();
        this.exception = exception.getClass().getSimpleName();
        this.path = path;
    }
    public ErrorMessage(String message, Exception exception, String path) {
        this.message = message;
        this.exception = exception.getClass().getSimpleName();
        this.path = path;
    }
}
