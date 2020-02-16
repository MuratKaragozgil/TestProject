package com.shopping.cart.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Murat Karagözgil
 */
public enum CommonError {

    CATEGORY_NOT_FOUND(1001, HttpStatus.NOT_FOUND, "Category not found"),
    PRODUCT_NOT_FOUND(2001, HttpStatus.NOT_FOUND, "Product not found"),
    CAMPAIGN_NOT_FOUND(2001, HttpStatus.NOT_FOUND, "Campaign not found"),
    COUPON_NOT_FOUND(3001, HttpStatus.NOT_FOUND, "Coupon not found"),
    CART_ITEM_NOT_FOUND(4001, HttpStatus.NOT_FOUND, "Cart Item not found"),
    SHOPPING_CART_NOT_FOUND(5001, HttpStatus.NOT_FOUND, "Shopping Cart not found");

    private int errorCode;
    private HttpStatus httpStatusCode;
    private String errorMsg;

    CommonError(int errorCode, HttpStatus httpStatusCode, String errorMsg) {
        this.errorCode = errorCode;
        this.httpStatusCode = httpStatusCode;
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
