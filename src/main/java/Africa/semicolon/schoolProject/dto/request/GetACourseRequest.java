package Africa.semicolon.schoolProject.dto.request;

import Africa.semicolon.schoolProject.data.model.School;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetACourseRequest {
    private String courseId;
    private String schoolId;
}
