package com.cloud.shopping.common.advice;


import com.cloud.shopping.common.enums.ExceptionEnum;
import com.cloud.shopping.common.exception.CloudException;
import com.cloud.shopping.common.vo.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(CloudException.class)
    public ResponseEntity<ExceptionResult> HandlerException(CloudException le){
        ExceptionEnum em = le.getExceptionEnum();
        return ResponseEntity.status(le.getExceptionEnum().getCode()).body(new ExceptionResult(le.getExceptionEnum()));
    }
}
