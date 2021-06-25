package com.utn.parcial.exceptions;

public class CurrencyApiException extends RuntimeException{
    public CurrencyApiException() {
        super("Currency conversion service has failed");
    }
}
