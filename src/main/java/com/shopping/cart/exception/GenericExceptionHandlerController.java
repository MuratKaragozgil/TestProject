package com.shopping.cart.exception;

import com.shopping.cart.controller.BaseController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GenericExceptionHandlerController extends BaseController {

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ErrorResponse> genericExceptionHandler(HttpServletRequest request, GenericException ex) {
        logErrorMessage(request, ex.getMessage());
        ErrorResponse response = new ErrorResponse();
        response.setMessage(ex.getMessage());
        response.setErrorCode(ex.getErrorCode());
        return new ResponseEntity<>(response, ex.getStatusCode());
    }
}