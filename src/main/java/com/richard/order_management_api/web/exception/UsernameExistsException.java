package com.richard.order_management_api.web.exception;

import org.springframework.http.HttpStatus;

public class UsernameExistsException extends BusinessException{
    public UsernameExistsException(){
        super("Username already exists", "USERNAME_EXISTS");
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
