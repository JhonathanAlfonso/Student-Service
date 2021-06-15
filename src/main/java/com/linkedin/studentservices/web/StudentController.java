package com.linkedin.studentservices.web;

import com.linkedin.studentservices.domain.Student;
import com.linkedin.studentservices.exceptions.StudentNotFoundException;
import com.linkedin.studentservices.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/students/{id}")
    Student getStudent(@PathVariable("id") Long id) {
        return studentService.getStudentById(id);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void studentNotFoundException(StudentNotFoundException e) {
    }
}
