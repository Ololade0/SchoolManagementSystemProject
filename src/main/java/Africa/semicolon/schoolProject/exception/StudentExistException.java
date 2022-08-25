package Africa.semicolon.schoolProject.exception;

import lombok.Data;

@Data
public class StudentExistException extends RuntimeException {
    public  StudentExistException(String message) {

    }
}
