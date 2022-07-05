package com.ojt.mvc_student_jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ojt.mvc_student_jpa.model.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course,Integer> {
    @Query(
        value = "SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'yoyo' AND TABLE_NAME = 'course';",
        nativeQuery = true
    )
    int getId();
}
