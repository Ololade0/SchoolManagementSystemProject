package Africa.semicolon.schoolProject.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RegisterSchoolResponse {
    private String id;
    private String message;
    private String courseId;
}
