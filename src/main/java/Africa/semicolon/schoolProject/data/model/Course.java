package Africa.semicolon.schoolProject.data.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
@NoArgsConstructor
public class Course {
    @Id
    private String courseId;
    private String courseName;
    private String courseCode;
    private boolean isAvailable;
    @DBRef
    private List<Student> studentsOfferingCourse = new ArrayList<>();
}
