package Africa.semicolon.schoolProject.dto.response;

import Africa.semicolon.schoolProject.data.model.Student;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class FindCourseResponse {
    private String courseName;
    private String courseCode;
    private boolean isAvailable;
    private List<Student> studentsOfferingCourse = new ArrayList<>();
}
