package Africa.semicolon.schoolProject.data.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document("Students")
@NoArgsConstructor
public class Student {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private int studentNumber;
    private String gender;
    private String age;
    private String email;
    @DBRef
    private List<Course> coursesOfferedByStudent = new ArrayList<>();
}
