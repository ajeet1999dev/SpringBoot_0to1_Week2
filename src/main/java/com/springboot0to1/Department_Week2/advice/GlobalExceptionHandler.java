package com.springboot0to1.Department_Week2.advice;

import com.springboot0to1.Department_Week2.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFoundException(ResourceNotFoundException exception){

//        return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
        ApiError apiError = ApiError
                .builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build();
        return handleApiResponse(apiError);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleAllInternalServerErrors(Exception exception){

        ApiError apiError = ApiError
                .builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();
        return handleApiResponse(apiError);

    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleAllInputValidationErrors(MethodArgumentNotValidException exception){

        List<String> internalServerErrors = exception.
                getBindingResult()
                .getAllErrors()
                .stream()
                .map(errors -> errors.getDefaultMessage())
                .collect(Collectors.toList());

        ApiError apiError = ApiError
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Intrnal Server Errors")
                .suberrors(internalServerErrors)
                .build();
        return handleApiResponse(apiError);
    }

    private ResponseEntity<ApiResponse<?>> handleApiResponse(ApiError apiError) {
        return new ResponseEntity<>(new ApiResponse<>(apiError), apiError.getStatus());
    }
}
