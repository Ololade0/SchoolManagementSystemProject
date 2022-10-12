package Africa.semicolon.schoolProject.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CreateCourseRequest {
    private String schoolId;
   private String studentId;
    public String courseCode;
    private String courseName;
    private String courseStatus;
}
