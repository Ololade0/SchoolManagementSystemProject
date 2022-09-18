package Africa.semicolon.schoolProject.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class AdmitStudentRequest {
    private String studentFirstName;
    private String studentLastName;
    private String emailAddress;
    private String schoolName;
    private String studentAge;
    private String gender;

}
