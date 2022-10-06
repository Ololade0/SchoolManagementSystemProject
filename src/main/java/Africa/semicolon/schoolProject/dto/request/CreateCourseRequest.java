package Africa.semicolon.schoolProject.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@Data
public class CreateCourseRequest {
    public String schoolName;
    private String courseName;
    private String courseStatus;
}
