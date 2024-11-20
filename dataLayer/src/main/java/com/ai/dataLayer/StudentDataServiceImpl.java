package com.ai.dataLayer;

import com.ai.dataLayer.core.StudentsDataService;
import com.ai.dataLayer.models.Student;
import com.ai.dataLayer.repositories.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentDataServiceImpl implements StudentsDataService {
    private final StudentRepository studentRepository;

    public StudentDataServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
}
