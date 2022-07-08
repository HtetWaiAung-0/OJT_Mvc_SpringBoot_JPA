package com.ojt.mvc_student_jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.ojt.mvc_student_jpa.model.User;
import com.ojt.mvc_student_jpa.repo.UserRepo;

@SpringBootTest
public class TestUserRepo {
    @Mock
    UserRepo repo;

    @Test
    public void findAllTest(){
        List<User> list = new ArrayList<User>();
        User user1 = new User();
        user1.setId(1);
        user1.setUserId("USR-001");
        user1.setUserMail("pkar123@gmail.com");
        user1.setUserPassword("123123");
        user1.setUserRole("user");

        User user2 = new User();
        user2.setId(2);
        user2.setUserId("USR-002");
        user2.setUserMail("htetwaiaung@gmail.com");
        user2.setUserPassword("123123");
        user2.setUserRole("admin");

        list.add(user1);
        list.add(user2);

        when(repo.findAll()).thenReturn(list);
        List<User> userList = repo.findAll();
        assertEquals(2, userList.size());
        verify(repo,times(1)).findAll();
    }

    @Test
    public void findByStuIdTest(){
        User user1 = new User();
        user1.setId(1);
        user1.setUserId("USR-001");
        user1.setUserMail("pkar123@gmail.com");
        user1.setUserPassword("123123");
        user1.setUserRole("user");

        when(repo.findByUserId("USR-001")).thenReturn(user1);
        User user = repo.findByUserId("USR-001");
        assertEquals("USR-001", user.getUserId());
    }

    @Test
    public void saveTest(){
        User user1 = new User();
        user1.setId(1);
        user1.setUserId("USR-001");
        user1.setUserMail("pkar123@gmail.com");
        user1.setUserPassword("123123");
        user1.setUserRole("user");
        repo.save(user1);
        verify(repo,times(1)).save(user1);
    }

    @Test
    public void deleteByIdTest(){
        repo.deleteById(1);
        verify(repo,times(1)).deleteById(1);
    }

    @Test
    public void findByUserIdOrUserMailTest(){
        List<User> list = new ArrayList<User>();
        User user1 = new User();
        user1.setId(1);
        user1.setUserId("USR-001");
        user1.setUserMail("pkar123@gmail.com");
        user1.setUserPassword("123123");
        user1.setUserRole("user");

        User user2 = new User();
        user2.setId(2);
        user2.setUserId("USR-002");
        user2.setUserMail("htetwaiaung@gmail.com");
        user2.setUserPassword("123123");
        user2.setUserRole("admin");

        list.add(user1);
        
        when(repo.findByUserIdOrUserMail("USR-001", "pkar123@gmail.com")).thenReturn(list);
        List<User> userList = repo.findByUserIdOrUserMail("USR-001", "pkar123@gmail.com");
        assertEquals(1, userList.size());
        for(User check : userList){
            assertEquals("USR-001", check.getUserId());
        }
    }

    @Test
    public void getIdTest(){
        int check = 1;
        when(repo.getId()).thenReturn(check);
        int i = repo.getId();
        assertEquals(1, i);
        verify(repo,times(1)).getId();
    }

    @Test
    public void existsByUserIdAndUserMailTest(){
        Boolean check = true;
        when(repo.existsByUserIdAndUserMail("USR-001","pkar1236@gmail.com")).thenReturn(check);
        Boolean b = repo.existsByUserIdAndUserMail("USR-001", "pkar1236@gmail.com");
        assertEquals(true, b);
        verify(repo,times(1)).existsByUserIdAndUserMail("USR-001", "pkar1236@gmail.com");
    }
}
