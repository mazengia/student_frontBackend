package com.bezkoder.student.StudentEnroll;

import org.springframework.hateoas.CollectionModel;

public interface StudentService {
    CollectionModel<StudentDTO> findAll(int page, int size );

    StudentDTO addStudent(StudentEnrolment studentEnrolment);

    StudentDTO findStudentById(Long id);
}