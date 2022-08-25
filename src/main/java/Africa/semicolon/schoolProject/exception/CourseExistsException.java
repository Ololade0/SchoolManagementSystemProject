package Africa.semicolon.schoolProject.exception;

import lombok.Data;

@Data
public class CourseExistsException extends RuntimeException {
    public CourseExistsException(String message){

    }
}
