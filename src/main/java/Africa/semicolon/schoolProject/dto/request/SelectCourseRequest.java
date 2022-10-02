package Africa.semicolon.schoolProject.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class SelectCourseRequest {
    private String courseName;
    private String schoolName;
}
