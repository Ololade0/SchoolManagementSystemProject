package Africa.semicolon.schoolProject.dto;

import lombok.Data;

@Data
public class RegisterSchoolRequest {
    private String id;
    private String schoolName;
    private String schoolLocation;
    private String schoolAddress;
}
