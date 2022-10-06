package Africa.semicolon.schoolProject.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UpdateStudentProfileResponse {
    private String message;
    private String id;
}
