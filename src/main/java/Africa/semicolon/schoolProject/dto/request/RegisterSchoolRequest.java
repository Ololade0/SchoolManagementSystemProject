package Africa.semicolon.schoolProject.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RegisterSchoolRequest {
    private String id;
    private String schoolName;
    private String schoolLocation;



}
