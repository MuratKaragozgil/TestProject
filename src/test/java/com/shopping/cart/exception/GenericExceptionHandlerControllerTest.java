package com.shopping.cart.exception;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Murat Karagözgil
 */
@RunWith(MockitoJUnitRunner.class)
public class GenericExceptionHandlerControllerTest {

    @InjectMocks
    private GenericExceptionHandlerController exceptionHandlerController;

    @Mock
    private MockHttpServletRequest request;

    @Test
    public void shouldReturnCorrectErrorMessage() {
        ResponseEntity<ErrorResponse> errorResponseResponseEntity = exceptionHandlerController.genericExceptionHandler(request, new GenericException(CommonError.SHOPPING_CART_NOT_FOUND));
        assertThat(errorResponseResponseEntity.getBody().getErrorCode(), equalTo(CommonError.SHOPPING_CART_NOT_FOUND.getErrorCode()));

    }
}