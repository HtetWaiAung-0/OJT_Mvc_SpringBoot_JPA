package com.ojt.mvc_student_jpa;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.ojt.mvc_student_jpa.model.User;
import com.ojt.mvc_student_jpa.repo.UserRepo;

@SpringBootTest
@AutoConfigureMockMvc
public class TestUserController {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserRepo userRepo;

    // @Test
    // public void loginPassTest() throws Exception{
    //     this.mockMvc.perform(get("/login").param("loginMail", "admin@gmail.com").param("loginPassword", "123"))
    //     .andExpect(status().isOk())
    //     .andExpect(view().name("MNU001"));
        
    // }

    @Test
    public void welcomePageTest() throws Exception{
        this.mockMvc.perform(get("/welcomePage"))
        .andExpect(status().isOk())
        .andExpect(view().name("MNU001"));
    }

    @Test
    public void test() throws Exception{
        this.mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("userBean"))
        .andExpect(view().name("LGN001"));
    }

    @Test
    public void addUserPageTest() throws Exception{
        this.mockMvc.perform(get("/addUserPage"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("userBean"))
        .andExpect(view().name("USR001"));
    }


    @Test
    public void addUserNextPageTest() throws Exception {
        this.mockMvc.perform(get("/addUserNextPage"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("userBean"))
        .andExpect(view().name("USR001"));
    }

    @Test
    public void addUserPassTest() throws Exception{
        User user = new User();
        user.setId(1);
        user.setUserId("123");
        user.setUserMail("qwe");
        user.setUserPassword("123");
        user.setUserRole("123");
        this.mockMvc.perform(post("/addUser").param("conPassword", "123").flashAttr("userBean", user))
        .andExpect(status().is(302))
        .andExpect(redirectedUrl("/addUserNextPage"));
    }

    @Test
    public void addUserFailTest() throws Exception{
        User user = new User();
        user.setId(1);
        user.setUserId("");
        user.setUserMail("");
        user.setUserPassword("123");
        user.setUserRole("123");
        this.mockMvc.perform(post("/addUser").param("conPassword", "123").flashAttr("userBean", user))
        .andExpect(status().is(200))
        .andExpect(model().attributeExists("userBean"))
        .andExpect(model().attributeExists("errorFill"))
        .andExpect(view().name("USR001"));
    }

    @Test
    public void updateUserPageTest() throws Exception{
        this.mockMvc.perform(get("/updateUserPage").param("id", "123"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("userBean"))
        .andExpect(view().name("USR002"));
    }

    @Test
    public void searchUserPageTest() throws Exception{
        this.mockMvc.perform(get("/searchUserPage"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("userList"))
        .andExpect(view().name("USR003"));
    }

    @Test
    public void searchUserPassTest() throws Exception{
        User user = new User();
        user.setId(1);
        user.setUserId("1123");
        user.setUserMail("123");
        user.setUserPassword("123");
        user.setUserRole("123");
        this.mockMvc.perform(post("/searchUser").param("searchId", "123").param("searchMail", "123").flashAttr("userBean", user))
        .andExpect(status().is(200))
        .andExpect(view().name("USR003"));
    }

    @Test
    public void searchUserFailTest() throws Exception{
        User user = new User();
        user.setId(1);
        user.setUserId("1123");
        user.setUserMail("");
        user.setUserPassword("123");
        user.setUserRole("123");
        this.mockMvc.perform(post("/searchUser").param("searchId", "123").param("searchMail", "123").flashAttr("userBean", user))
        .andExpect(status().is(200))
        .andExpect(view().name("USR003"));
    }
    
    @Test
    public void updateUserPassTest() throws Exception{
        User user = new User();
        user.setId(1);
        user.setUserId("1123");
        user.setUserMail("123");
        user.setUserPassword("123");
        user.setUserRole("123");

        this.mockMvc.perform(post("/updateUser").param("conPassword", "123").flashAttr("userBean",user))
        .andExpect(status().is(302))
        .andExpect(redirectedUrl("/searchUserPage"));
    }


    @Test
    public void deleteUserTest() throws Exception{
        this.mockMvc.perform(get("/deleteUser").param("id", "1"))
        .andExpect(status().is(302))
        .andExpect(redirectedUrl("/searchUserPage"));
    }
   /*  @Test
    public void addUserPassTest() throws Exception{
        User user = new User();
        user.setId(1);
        user.setUserId("123");
        user.setUserMail("qwe");
        user.setUserPassword("123");
        user.setUserRole("123");
        this.mockMvc.perform(post("/addUser").param("conPassword", "123").flashAttr("userBean", user))
        .andExpect(status().is(302))
        .andExpect(redirectedUrl("/addUserNextPage"));
    }
 */


    /* @Test
    public void updateStuPageTest() throws Exception{
        this.mockMvc.perform(get("/stuAddPage").param("id", "123"))
        .andExpect(status().isOk())
        .andExpect(view().name("STU001"))
        .andExpect(model().attributeExists("courseList"))
        .andExpect(model().attributeExists("stuBean"));
    } */
}
