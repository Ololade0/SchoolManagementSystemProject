package Africa.semicolon.schoolProject.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetStudentRequest {
    private String studentId;
    private String schoolId;
}
