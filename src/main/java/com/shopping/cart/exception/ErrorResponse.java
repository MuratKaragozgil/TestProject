package com.shopping.cart.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Murat Karagözgil
 */
@Getter
@Setter
@ToString
public class ErrorResponse {
    private String message;
    private int errorCode;
}
