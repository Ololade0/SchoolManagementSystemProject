package Africa.semicolon.schoolProject.dto.request;

import lombok.*;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AdmitStudentRequest {
    private String studentId;
    private String studentFirstName;
    private String studentLastName;
    private String emailAddress;
    private String schoolName;
    private String studentAge;
    private String studentGender;

}
