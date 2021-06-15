package com.linkedin.studentservices.services;

import com.linkedin.studentservices.domain.Student;
import com.linkedin.studentservices.exceptions.StudentNotFoundException;
import com.linkedin.studentservices.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Cacheable("students")
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(()->new StudentNotFoundException());
    }
}
