package Africa.semicolon.schoolProject.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UpdateSchoolProfileRequest {
    private String schoolId;
    private String schoolName;
    private String schoolLocation;
}
