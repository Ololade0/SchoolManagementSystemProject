package Africa.semicolon.schoolProject.exception;

import lombok.Data;

@Data
public class CourseExistException extends RuntimeException {
    public CourseExistException(String message){

    }
}
