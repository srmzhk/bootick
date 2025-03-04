package com.srmzhk.bootick.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class TimeIsOverException extends RuntimeException {
}
