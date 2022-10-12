package Africa.semicolon.schoolProject.dto.request;

import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RegisterAllCourseRequest {
    private String schoolId;
    private String studentId;
  //  private String
    public String courseCode;
    private String courseName;
    private String courseStatus;
}
