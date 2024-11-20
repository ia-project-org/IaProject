package org.springbatch;

import org.junit.jupiter.api.Test;
import org.springbatch.client.Client;
import org.springbatch.client.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testSaveStudent() {
        Client student = new Client();
        student.setFirstname("John");
        student.setLastname("Doe");
        student.setAge(22);

        studentRepository.save(student);

        assertTrue(studentRepository.count() > 0);
    }
}

