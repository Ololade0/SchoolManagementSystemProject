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
    private String schoolId;
    private String studentFirstName;
    private String studentLastName;
    private String emailAddress;
    private String studentAge;
    private String studentGender;

}
