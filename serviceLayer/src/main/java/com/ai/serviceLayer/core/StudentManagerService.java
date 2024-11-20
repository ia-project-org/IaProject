package com.ai.serviceLayer.core;


import com.ai.serviceLayer.models.StudentCreateItem;
import com.ai.serviceLayer.models.StudentItem;

public interface StudentManagerService {
    StudentItem addStudent(StudentCreateItem studentCreateItem);
}
