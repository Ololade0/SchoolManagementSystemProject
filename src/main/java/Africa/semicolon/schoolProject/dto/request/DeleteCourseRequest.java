package Africa.semicolon.schoolProject.dto.request;

import lombok.*;


@Setter
@Getter
public class DeleteCourseRequest {
    private String schoolId;
    private String courseId;
}
