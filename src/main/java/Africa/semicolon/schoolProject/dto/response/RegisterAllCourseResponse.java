package Africa.semicolon.schoolProject.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RegisterAllCourseResponse {
    private String message;
    private String courseId;
    private String schoolId;
    private String studentId;

}
