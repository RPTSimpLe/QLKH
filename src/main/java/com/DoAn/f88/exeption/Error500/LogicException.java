package com.DoAn.f88.exeption.Error500;

import com.DoAn.f88.exeption.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class LogicException extends ApplicationException {
    public LogicException(String message) {super(message);}

}
