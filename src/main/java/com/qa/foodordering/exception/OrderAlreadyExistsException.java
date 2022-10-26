package com.qa.foodordering.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Order with this ID already exists")
public class OrderAlreadyExistsException extends Exception {

}
