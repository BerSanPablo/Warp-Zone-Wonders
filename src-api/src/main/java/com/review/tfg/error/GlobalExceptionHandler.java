package com.review.tfg.error;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.review.tfg.dto.auth.response.ErrorDetailsResponse;
import com.review.tfg.dto.auth.response.ErrorListResponse;
import com.review.tfg.error.exception.BadTokenException;
import com.review.tfg.error.exception.CantCreateUserException;
import com.review.tfg.error.exception.UserNotFoundException;
import com.review.tfg.error.exception.VideojuegoNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	/**
     * ###################################################
     * #       Conflictos al crear usuarios - 409       ##
     * ###################################################
     * @param ex
     * @param request
     * @return ErrorDetailsResponse
     */
    @ExceptionHandler(CantCreateUserException.class)
    public ResponseEntity<ErrorDetailsResponse> handleCantCreateUserException(CantCreateUserException ex, WebRequest request) {
    	ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(
            new Date(),
            ex.getMessage(),
            request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

	/**
     * ###################################################
     * #        No se encuentra el usuario - 404        ##
     * ###################################################
     * @param ex
     * @param request
     * @return ErrorDetailsResponse
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetailsResponse> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
    	ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(
            new Date(),
            ex.getMessage(),
            request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

	/**
     * ###################################################
     * #       No se encuentra el videojuego - 404      ##
     * ###################################################
     * @param ex
     * @param request
     * @return ErrorDetailsResponse
     */
    @ExceptionHandler(VideojuegoNotFoundException.class)
    public ResponseEntity<ErrorDetailsResponse> handleVideojuegoNotFoundException(VideojuegoNotFoundException ex, WebRequest request) {
    	ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(
            new Date(),
            ex.getMessage(),
            request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

	/**
     * ###################################################
     * #        No se encuentra el usuario - 404        ##
     * ###################################################
     * @param ex
     * @param request
     * @return ErrorDetailsResponse
     */
    @ExceptionHandler(BadTokenException.class)
    public ResponseEntity<ErrorDetailsResponse> handleBadTokenException(BadTokenException ex, WebRequest request) {
    	ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(
            new Date(),
            ex.getMessage(),
            request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

	/**
     * ###################################################
     * # No se ha podido validar una entidad con @Valid ##
     * ###################################################
     * @param ex
     * @param request
     * @return ErrorDetailsResponse
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorListResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
    	List<String> errors = ex.getBindingResult()
    							.getFieldErrors()
                				.stream()
                				.map(FieldError::getDefaultMessage)
                				.collect(Collectors.toList());
    	
    	ErrorListResponse errorDetails = new ErrorListResponse(
            new Date(),
            errors);

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

	/**
     * ###################################################
     * #        Se ha violado una clave primaria        ##
     * ###################################################
     * @param ex
     * @param request
     * @return ErrorDetailsResponse
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorDetailsResponse> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex, WebRequest request) {
    	ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(
            new Date(),
            ex.getMessage(),
            "Se ha violado una clave primaria al intentar guardar una entidad");

        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }
    
}
