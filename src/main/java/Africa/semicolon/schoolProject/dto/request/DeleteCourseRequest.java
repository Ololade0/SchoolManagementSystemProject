package Africa.semicolon.schoolProject.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
public class DeleteCourseRequest {
    private String schoolName;
    private String courseId;
    private String courseName;
}
