package Africa.semicolon.schoolProject.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetAStudentRequest {

    private String studentLastName;
    private String studentId;
    private String schoolName;
}
