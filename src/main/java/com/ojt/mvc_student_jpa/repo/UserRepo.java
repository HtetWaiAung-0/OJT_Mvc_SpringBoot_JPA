package com.ojt.mvc_student_jpa.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ojt.mvc_student_jpa.model.User;

public interface  UserRepo extends JpaRepository<User,Integer> {
    @Query(
        value = "SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'yoyo' AND TABLE_NAME = 'user';",
        nativeQuery = true
    )
    int getId();
    
    void deleteByUserId(String id);
    User findByUserId(String id);
    List<User> findByUserIdOrUserMail(String id,String mail);
    Boolean existsByUserIdAndUserMail(String id ,String mail);
}
