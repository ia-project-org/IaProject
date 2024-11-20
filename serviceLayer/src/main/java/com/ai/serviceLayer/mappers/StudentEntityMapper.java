package com.ai.serviceLayer.mappers;

import com.ai.dataLayer.models.Student;
import com.ai.serviceLayer.models.StudentCreateItem;
import com.ai.serviceLayer.models.StudentItem;

public class StudentEntityMapper {
    public static StudentItem mapToStudentItem(Student student) {
        return new StudentItem(student.getFirstName() + " " + student.getLastName());
    }

    public static Student mapToStudent(StudentCreateItem studentCreateItem) {
        Student student = new Student();
        student.setFirstName(studentCreateItem.getFirstName());
        student.setLastName(studentCreateItem.getLastName());
        return student;
    }

}
