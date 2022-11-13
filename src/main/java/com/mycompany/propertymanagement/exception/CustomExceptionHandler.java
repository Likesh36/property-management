package com.mycompany.propertymanagement.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {
    //private final Logger logger= LoggerFactory.getLogger(CustomExceptionHandler.class);
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorModel>> handleFieldValidation(MethodArgumentNotValidException notValidException){
        List<ErrorModel>errorModelList=new ArrayList<>();
        ErrorModel errorModel=null;
        List<FieldError>fieldErrorList= notValidException.getBindingResult().getFieldErrors();
        for(FieldError fieldError:fieldErrorList){
            logger.debug(" Inside the field validations : {} - {} ",fieldError.getField(),fieldError.getDefaultMessage());
            logger.info(" Inside the field validations : {} - {} ",fieldError.getField(),fieldError.getDefaultMessage());
            errorModel=new ErrorModel();
            errorModel.setCode(fieldError.getCode());
            errorModel.setMessage(fieldError.getDefaultMessage());
            errorModelList.add(errorModel);
        }
        return new ResponseEntity<List<ErrorModel>>(errorModelList, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException businessException){
        for(ErrorModel em: businessException.getErrors()){
            logger.debug(" Business exception is thrown - level as debug : {} - {} ",em.getCode(),em.getMessage());
            logger.error(" Business exception is thrown - level as error : {} - {} ",em.getCode(),em.getMessage());
            logger.info(" Business exception is thrown - level as info : {} - {} ",em.getCode(),em.getMessage());
            logger.warn(" Business exception is thrown - level as warn : {} - {} ",em.getCode(),em.getMessage());
        }
        return new ResponseEntity<List<ErrorModel>>(businessException.getErrors(), HttpStatus.BAD_REQUEST);

    }
//    @ExceptionHandler(BusinessException.class)
//    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException businessException){
//        logger.debug(" Business exception is thrown : {} ",businessException.getErrors());
//        logger.info(" Business exception is thrown : {} ",businessException.getErrors());
//
//        return new ResponseEntity<List<ErrorModel>>(businessException.getErrors(), HttpStatus.BAD_REQUEST);
//
//    }
}
