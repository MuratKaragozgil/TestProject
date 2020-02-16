package com.shopping.cart.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

/**
 * @author Murat Karagözgil
 */

@Getter
public class GenericException extends RuntimeException implements Supplier<GenericException> {

    private Integer errorCode;
    private HttpStatus statusCode;

    public GenericException(CommonError error) {
        super(error.getErrorMsg());
        this.errorCode = error.getErrorCode();
        this.statusCode = error.getHttpStatusCode();
    }

    @Override
    public GenericException get() {
        return this;
    }
}
