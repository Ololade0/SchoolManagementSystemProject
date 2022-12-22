package Africa.semicolon.schoolProject.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRest {
    private String password;
    private String email;
}
