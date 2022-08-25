package Africa.semicolon.schoolProject.exception;

import lombok.Data;

@Data
public class StudentExistsException extends RuntimeException {
    public StudentExistsException(String message) {

    }
}
