package org.tasks.taskmanager.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({
            IllegalArgumentException.class, IllegalStateException.class
    })
    public ResponseEntity<ErrorMessage> ExceptionHandler(RuntimeException ex, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(
                request.getDescription(false),
                HttpStatus.BAD_REQUEST.value(), ex.getMessage()
        );
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }
}
