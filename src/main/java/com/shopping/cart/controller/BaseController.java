package com.shopping.cart.controller;

import com.shopping.cart.utils.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Murat Karagözgil
 */
public class BaseController {

    private static final SimpleDateFormat LOG_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected void logUserAction(HttpServletRequest request, String action, Object... params) {
        String ip = WebUtils.getClientIP(request);
        logger.info("ACCESS_LOG|Date:{}|Action::{}|IP::{}|Params::{}", action, LOG_DATE_FORMAT.format(new Date()), ip, params);
    }

    protected void logErrorMessage(HttpServletRequest request, String action, Object... params) {
        String ip = WebUtils.getClientIP(request);
        logger.error("ACCESS_LOG|Date:{}|Action::{}|IP::{}|Params::{}", action, LOG_DATE_FORMAT.format(new Date()), ip, params);
    }
}
