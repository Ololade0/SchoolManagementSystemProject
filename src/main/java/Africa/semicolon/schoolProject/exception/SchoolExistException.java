package Africa.semicolon.schoolProject.exception;

import lombok.Data;

@Data
public class SchoolExistException extends RuntimeException{
    public  SchoolExistException(String message) {

    }
}
