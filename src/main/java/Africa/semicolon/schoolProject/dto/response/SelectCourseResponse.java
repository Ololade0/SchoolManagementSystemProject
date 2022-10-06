package Africa.semicolon.schoolProject.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SelectCourseResponse {
    private String message;
    private String courseId;
}
