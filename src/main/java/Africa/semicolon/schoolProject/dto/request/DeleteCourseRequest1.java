package Africa.semicolon.schoolProject.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data

@NoArgsConstructor
public class DeleteCourseRequest1 {
    private String schoolName;
    private String courseId;
    private String courseName;
    private String courseStatus;
}
