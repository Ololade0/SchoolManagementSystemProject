package Africa.semicolon.schoolProject.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document
public class DeleteStudentRequest {
    private String id;
    private String firstName;
    private String lastName;
    private String studentId;
    private String schoolName;


}
