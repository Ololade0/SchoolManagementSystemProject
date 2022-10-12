package Africa.semicolon.schoolProject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SchoolAlreadyExistException extends RuntimeException {
    private String message;
}
