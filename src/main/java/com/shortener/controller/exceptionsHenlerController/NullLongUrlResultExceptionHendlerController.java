package com.shortener.controller.exceptionsHenlerController;

import com.shortener.exceptions.NullLongUrlResultException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NullLongUrlResultExceptionHendlerController {

    @ExceptionHandler(NullLongUrlResultException.class)
    public String testError() {
        return "badpage";
    }

}
