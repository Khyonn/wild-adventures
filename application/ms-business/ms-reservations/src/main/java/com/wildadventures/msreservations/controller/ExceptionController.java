package com.wildadventures.msreservations.controller;

import com.wildadventures.msreservations.controller.dto.ErrorDto;
import com.wildadventures.msreservations.exceptions.BusinessException;
import com.wildadventures.msreservations.exceptions.TechnicalException;
import com.wildadventures.msreservations.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class ExceptionController {

    @ExceptionHandler({ BusinessException.class})
    public ResponseEntity<ErrorDto> handleBusinessException(BusinessException exception) {
        return new ResponseEntity<>(new ErrorDto("business", exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ TechnicalException.class})
    public ResponseEntity<ErrorDto> handleTechnicalException(TechnicalException exception) {
        return new ResponseEntity<>(new ErrorDto("technical", exception.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ UnauthorizedException.class})
    public ResponseEntity<ErrorDto> handleUnauthorizedException() {
        return new ResponseEntity<>(new ErrorDto("unauthorized", "Unauthorized user"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({ Exception.class})
    public ResponseEntity<ErrorDto> handleException() {
        return new ResponseEntity<>(new ErrorDto("unknown", ""), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
