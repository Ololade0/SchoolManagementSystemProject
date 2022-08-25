package Africa.semicolon.schoolProject.data.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("student")
@NoArgsConstructor
public class Student {
    @Id
    private String id;
    private String studentFirstName;
    private String studentLastName;
    private String gender;
    private String studentAge;
    private String email;

}
