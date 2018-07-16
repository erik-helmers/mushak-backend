package core.exceptions;


import logger.Log;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class CustomErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RestError.class)
    public ResponseEntity<Object> handleRestError(RestError error){
        return error.toEntity();
    }


    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Log.warn("Request: " + request.getDescription(true) + " raised " + ex);

        ModelAndView mav = new ModelAndView();
        mav.addObject("status", "error");
        mav.addObject("exception", ex);
        mav.addObject("url", "this");
        mav.setViewName("error");

        return new ResponseEntity<>(mav.getModelMap().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
