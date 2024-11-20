package com.ai.serviceLayer;

import com.ai.dataLayer.core.StudentsDataService;
import com.ai.dataLayer.models.Student;
import com.ai.serviceLayer.core.StudentManagerService;
import com.ai.serviceLayer.mappers.StudentEntityMapper;
import com.ai.serviceLayer.models.StudentCreateItem;
import com.ai.serviceLayer.models.StudentItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StudentManagerServiceImpl implements StudentManagerService {
    private static final Logger logger = LoggerFactory.getLogger(StudentManagerServiceImpl.class);

    private final StudentsDataService studentsDataService;

    public StudentManagerServiceImpl(StudentsDataService studentsDataService) {
        this.studentsDataService = studentsDataService;
    }

    @Override
    public StudentItem addStudent(StudentCreateItem studentCreateItem) {
        Student student = StudentEntityMapper.mapToStudent(studentCreateItem);
        Student savedStudent = studentsDataService.saveStudent(student);
        return StudentEntityMapper.mapToStudentItem(savedStudent);

    }
}
