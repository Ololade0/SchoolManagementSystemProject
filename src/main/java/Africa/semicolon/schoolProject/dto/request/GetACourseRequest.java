package Africa.semicolon.schoolProject.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document
public class GetACourseRequest {
    private String courseName;
    private String courseId;
}
