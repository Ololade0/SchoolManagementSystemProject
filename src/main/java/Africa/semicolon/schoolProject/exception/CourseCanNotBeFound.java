package Africa.semicolon.schoolProject.exception;

import lombok.Data;

@Data
public class CourseCanNotBeFound extends RuntimeException{
    public  CourseCanNotBeFound(String message) {
        super(message);

    }
}
