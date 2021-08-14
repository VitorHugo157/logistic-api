package com.vitor.logisticbackend.domain.exception;

public class ResourceNotFoundException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
