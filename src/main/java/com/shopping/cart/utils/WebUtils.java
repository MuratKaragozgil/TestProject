package com.shopping.cart.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Murat Karagözgil
 */
public class WebUtils {

    public static String getClientIP(HttpServletRequest request) {
        String xForwardedForHeader = request.getHeader("X-Real-IP");
        if (xForwardedForHeader == null) {
            return request.getRemoteAddr();
        } else {
            return StringUtils.split(xForwardedForHeader, ',')[0];
        }
    }
}
