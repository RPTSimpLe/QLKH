package com.DoAn.f88.exeption.Error401;

import com.DoAn.f88.exeption.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthException extends ApplicationException {
    public AuthException(String message) {super(message);}
}
