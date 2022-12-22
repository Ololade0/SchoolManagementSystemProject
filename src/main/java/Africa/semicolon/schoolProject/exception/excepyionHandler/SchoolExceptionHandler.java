package Africa.semicolon.schoolProject.exception.excepyionHandler;

import Africa.semicolon.schoolProject.exception.SchoolAlreadyExistException;
import Africa.semicolon.schoolProject.exception.SchoolDoesExistException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class SchoolExceptionHandler {
    @ExceptionHandler(value = {SchoolDoesExistException.class})
    public ResponseEntity<Object> handleSchoolException(SchoolDoesExistException exception, WebRequest webRequest){
        ErrorMessage errorMessage = new ErrorMessage(new Date(), exception.getMessage());
        return new ResponseEntity<>(errorMessage.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(value = {SchoolAlreadyExistException.class})
    public ResponseEntity<Object> SchoolEXistException(SchoolAlreadyExistException exception, WebRequest webRequest){
        ErrorMessage errorMessage = new ErrorMessage(new Date(), exception.getMessage());
        return new ResponseEntity<>(errorMessage.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
