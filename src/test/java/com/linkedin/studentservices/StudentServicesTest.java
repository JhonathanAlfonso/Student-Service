package com.linkedin.studentservices;

import com.linkedin.studentservices.domain.Student;
import com.linkedin.studentservices.exceptions.StudentNotFoundException;
import com.linkedin.studentservices.repositories.StudentRepository;
import com.linkedin.studentservices.services.StudentService;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class StudentServicesTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @DisplayName("Returning saved student from service layer")
    @Test
    void getStudentById_forSavedStudent_isReturned() {

        //given
        Student savedStudent = studentRepository.save(new Student(null, "Mark"));

        //when
        Student student = studentService.getStudentById(savedStudent.getId());

        //then
        then(student.getName()).isEqualTo("Mark");
        then(student.getId()).isNotNull();
    }

    @Test
    void getStudentById_whenMissingStudent_notFoundExceptionThrown() {

        //given
        Long id = 1234l;

        //when
        Throwable throwable = catchThrowable(() -> studentService.getStudentById(id));

        //then
        BDDAssertions.then(throwable).isInstanceOf(StudentNotFoundException.class);
    }

}
