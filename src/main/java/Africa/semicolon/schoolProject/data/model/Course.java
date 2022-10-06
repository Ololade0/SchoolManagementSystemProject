package Africa.semicolon.schoolProject.data.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("course")
public class Course {
    @Id
    private Long id;
    private Long courseName;
    private String courseCode;
    private String courseStatus;



}
