package com.linkedin.studentservices.repositories;

import com.linkedin.studentservices.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student getStudentByName(String name);

    @Query("select avg (grade) from Student where active=true")
    Double getAvgGradeForActiveStudents();

}
