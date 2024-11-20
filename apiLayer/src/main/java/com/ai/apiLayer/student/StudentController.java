package com.ai.apiLayer.student;

import com.ai.apiLayer.student.mappers.StudentDtoMapper;
import com.ai.apiLayer.student.models.StudentCreateRequest;
import com.ai.serviceLayer.core.StudentManagerService;
import com.ai.serviceLayer.models.StudentCreateItem;
import com.ai.serviceLayer.models.StudentItem;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentManagerService studentManagerService;

    public StudentController(StudentManagerService studentManagerService) {
        this.studentManagerService = studentManagerService;
    }

    @PostMapping
    public StudentItem addStudent(@Valid @RequestBody StudentCreateRequest studentCreateRequest) {
        StudentCreateItem studentCreateItem = StudentDtoMapper.mapToStudentCreateItem(studentCreateRequest);
        return studentManagerService.addStudent(studentCreateItem);
    }

}
