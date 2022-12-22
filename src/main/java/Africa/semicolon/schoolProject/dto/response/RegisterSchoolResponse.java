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
    private String schoolName;
    private String schoolLocation;
    private String courseId;
    private String email;

    public String password;


}


