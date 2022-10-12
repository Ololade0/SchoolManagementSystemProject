package Africa.semicolon.schoolProject.dto.response;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCourseResponse {
    private String message;
    private String courseId;

}
