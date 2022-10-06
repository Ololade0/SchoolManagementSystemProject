package Africa.semicolon.schoolProject.dto.request;

import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SelectCourseRequest {
    private String studentId;
    private String courseName;
    private String courseCode;
    private String courseId;
    private String schoolId;
}
