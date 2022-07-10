package com.ojt.mvc_student_jpa.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ojt.mvc_student_jpa.model.Student;



public interface StudentRepo extends JpaRepository<Student,Integer> {
    @Query(
        value = "SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'yoyo' AND TABLE_NAME = 'student';",
        nativeQuery = true
    )
    int getId();
    
    List<Student> findByStuId(String id);
    List<Student> findDistinctByStuIdOrStuNameContainingOrStuCourse_CourseNameContaining(String stuId, String stuName, String courseName);
}
