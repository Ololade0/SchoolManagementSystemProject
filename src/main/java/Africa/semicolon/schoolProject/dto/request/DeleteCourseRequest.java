package Africa.semicolon.schoolProject.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class DeleteCourseRequest {
private String id;
    private String schoolName;
    private String courseId;
    private String courseName;
    private String courseStatus;

}
