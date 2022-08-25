package Africa.semicolon.schoolProject.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FindStudentRequest {
    private String schoolName;
    private String fullName;
    private int studentNumber;
}
