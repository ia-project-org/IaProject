package com.ai.dataLayer.repositories;

import com.ai.dataLayer.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
