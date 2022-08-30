package Africa.semicolon.schoolProject.data.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
public class Course {
    @Id
    private String courseId;
    private String courseName;
    private String courseCode;
    private String courseStatus;
    private String id;


}
