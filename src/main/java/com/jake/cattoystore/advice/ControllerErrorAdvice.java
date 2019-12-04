package com.jake.cattoystore.advice;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllerErrorAdvice {

    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleNotFounc() {
        return ResponseEntity.notFound().build();
    }
}
