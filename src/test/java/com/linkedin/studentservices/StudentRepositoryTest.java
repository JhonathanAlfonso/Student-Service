package com.linkedin.studentservices;

import com.linkedin.studentservices.domain.Student;
import com.linkedin.studentservices.repositories.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;

import static org.assertj.core.api.BDDAssertions.then;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
     void testGetStudentByName_returnStudentDetails()
     {
         //given
         Student savedStudent = testEntityManager.persistAndFlush(new Student(null, "Jhonny"));

         // when
         Student student = studentRepository.getStudentByName("Jhonny");

         // then
         then(student.getId()).isNotNull();
         then(student.getName()).isEqualTo(savedStudent.getName());
     }

     @Test
     void getAvgGradeForActiveStudents_calculatesAvg() {

        //given
         Student mark = Student.builder().name("Mark").active(true).grade(80).build();
         Student jhonny = Student.builder().name("Jhonny").active(true).grade(100).build();
         Student peter = Student.builder().name("Peter").active(false).grade(100).build();
         Arrays.asList(mark, jhonny, peter).forEach(testEntityManager::persistFlushFind);

         //when
         Double avgGrade = studentRepository.getAvgGradeForActiveStudents();

         //then
         then(avgGrade).isEqualTo(90);
     }
}
