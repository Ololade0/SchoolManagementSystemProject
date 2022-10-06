package Africa.semicolon.schoolProject.data.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("student")
public class Student {
    @Id
    private String id;
    private String studentFirstName;
    private String studentLastName;
    private String gender;
    private String studentAge;
    private String email;


}
