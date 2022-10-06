package Africa.semicolon.schoolProject.services;


import Africa.semicolon.schoolProject.data.model.School;
import Africa.semicolon.schoolProject.dto.request.RegisterSchoolRequest;
import Africa.semicolon.schoolProject.dto.request.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class SchoolServiceImplTest {
    @Autowired
    private SchoolService schoolService;
    School savedSchool;

    @AfterEach
    void tearDown() {
        schoolService.deleteAllSchools();
    }

    @BeforeEach
    void setUp() {
        RegisterSchoolRequest registerSchoolRequest = RegisterSchoolRequest
                .builder()
                .schoolLocation("Sabo")
                .schoolName("Semicolon")
                .build();
       savedSchool =  schoolService.registerSchool(registerSchoolRequest);
    }
    @Test
    public void testThatCanBeCreated(){
        School newSChool = School.builder()
                .schoolLocation("Sabo")
                .schoolName("Semicolon")
                .build();
        assertEquals("Semicolon", newSChool.getSchoolName());
    }

    @Test
    public void testThatSchoolCanBeRegister(){
        assertEquals(1, schoolService.TotalNumbersOfSchool());
    }
    @Test
    public void testThatSchoolCanBeFindById(){
       School foundSchool =  schoolService.findSchoolById(savedSchool.getId());
        assertThat(foundSchool.getId()).isEqualTo(savedSchool.getId());
        assertThat(foundSchool.getId()).isGreaterThan("50");
        assertThat(foundSchool).isNotNull();
    }
    @Test
    public void findAllSchools(){
        schoolService.findAllSchools();
        assertEquals("Semicolon", schoolService.findAllSchools().get(0).getSchoolName());
    }
    @Test
    public void deleteAllSchools(){
        schoolService.deleteAllSchools();
        assertEquals(0, schoolService.TotalNumbersOfSchool());

    }
    @Test
    public void schoolCanBeDeletedById(){
        RegisterSchoolRequest registerSchoolRequest = RegisterSchoolRequest
                .builder()
                .schoolLocation("Sabo")
                .schoolName("Semicolon")
                .build();
        savedSchool =  schoolService.registerSchool(registerSchoolRequest);
        schoolService.deleteById(savedSchool.getId());
        assertEquals(1, schoolService.TotalNumbersOfSchool());
    }

    @Test
    public void schoolProfileCanBeUpdated(){
        UpdateSchoolProfileRequest updateSchoolProfileRequest = UpdateSchoolProfileRequest.builder()
                .schoolLocation("Lekki")
                .schoolName("Unilag")
                .build();
        updateSchoolProfileRequest.setSchoolId(savedSchool.getId());
        schoolService.updateSchoolProfile(updateSchoolProfileRequest);
        assertEquals("Unilag", schoolService.findAllSchools().get(0).getSchoolName());

    }




}


