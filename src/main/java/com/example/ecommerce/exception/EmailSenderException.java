package com.example.ecommerce.exception;

import ch.qos.logback.core.encoder.EchoEncoder;

public class EmailSenderException extends RuntimeException {
    public EmailSenderException(String message) {
        super(message);
    }
}
