package Africa.semicolon.schoolProject.dto.request;

import lombok.Data;

@Data
public class UpdateCourseRequest {
    private String id;
    private String schoolId;
    private String schoolName;
    private String CourseName;
    private String CourseId;
    private String courseCode;


}
