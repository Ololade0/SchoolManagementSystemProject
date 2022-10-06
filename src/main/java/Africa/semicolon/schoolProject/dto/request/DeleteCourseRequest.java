package Africa.semicolon.schoolProject.dto.request;

import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DeleteCourseRequest {
    private String schoolId;
    private String courseId;
    private String courseName;
    private String courseStatus;

}
