package Africa.semicolon.schoolProject.data.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document("Schools")
@NoArgsConstructor
public class School {
    @Id
    private String id;
    private String schoolName;
    private String schoolLocation;
    @DBRef
    private List<Student> students = new ArrayList<>();
    @DBRef
    private List<Course> courses = new ArrayList<>();
}
