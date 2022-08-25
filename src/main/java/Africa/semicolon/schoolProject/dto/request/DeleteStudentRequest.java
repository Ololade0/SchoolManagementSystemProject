package Africa.semicolon.schoolProject.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document
public class DeleteStudentRequest {
    private String firstName;
    private String lastName;
    private int studentNumber;
    private String schoolName;


}
