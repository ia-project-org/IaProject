package com.ai.apiLayer.student.mappers;

import com.ai.serviceLayer.models.StudentCreateItem;
import com.ai.apiLayer.student.models.StudentCreateRequest;

public class StudentDtoMapper {
    public static StudentCreateItem mapToStudentCreateItem(StudentCreateRequest studentCreateRequest) {
        return new StudentCreateItem(studentCreateRequest.getFirstName(), studentCreateRequest.getLastName());
    }
}
