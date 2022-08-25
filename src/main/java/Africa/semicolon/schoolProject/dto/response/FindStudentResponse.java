package Africa.semicolon.schoolProject.dto.response;

import Africa.semicolon.schoolProject.data.model.Course;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class FindStudentResponse {
    private String firstName;
    private String lastName;
    private int studentNumber;
    private String gender;
    private String age;
    private String email;
    @DBRef
    private List<Course> coursesOfferedByStudent = new ArrayList<>();
}
