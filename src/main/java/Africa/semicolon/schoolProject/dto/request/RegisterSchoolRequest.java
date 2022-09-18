package Africa.semicolon.schoolProject.dto.request;

import lombok.Data;

@Data
public class RegisterSchoolRequest {
    private String id;
    private String schoolName;
    private String schoolLocation;
    private String schoolAddress;


}
