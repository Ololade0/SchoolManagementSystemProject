package Africa.semicolon.schoolProject.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor

public class GetACourseRequest {
    private String courseName;
    private String courseId;
    private String schoolName;

}
