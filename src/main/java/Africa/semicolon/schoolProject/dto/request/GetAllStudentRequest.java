package Africa.semicolon.schoolProject.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetAllStudentRequest {
    private String schoolId;
    private String studentId;
}
