package Africa.semicolon.schoolProject.exception;

import lombok.Data;

public class SchoolExistException extends RuntimeException{
    public  SchoolExistException(String message) {
        super(message);
    }
}
