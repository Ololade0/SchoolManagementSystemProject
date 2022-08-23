package Africa.semicolon.schoolProject.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document
public class CreateCourseRequest {
    @Id
    private String courseId;
    public String schoolName;
    private String courseName;
    private String courseStatus;



}