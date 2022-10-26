package com.qa.foodordering.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No customer found with this ID")
public class CustomerNotFoundException extends Exception {

}
