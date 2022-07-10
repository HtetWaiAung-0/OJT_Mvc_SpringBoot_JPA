package com.ojt.mvc_student_jpa;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.ojt.mvc_student_jpa.model.Course;
import com.ojt.mvc_student_jpa.repo.CourseRepo;

@SpringBootTest
@AutoConfigureMockMvc
public class TestCourseController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CourseRepo courseRepo;

    @Test
    public void courseAddPageTest() throws Exception{
        this.mockMvc.perform(get("/courseAddPage"))
        .andExpect(status().isOk())
        .andExpect(view().name("BUD003"))
        .andExpect(model().attributeExists("cBean"));
    }

    @Test
    public void courseAddPageNextTest() throws Exception{
        this.mockMvc.perform(get("/courseAddPageNext"))
        .andExpect(status().isOk())
        .andExpect(view().name("BUD003"))
        
        .andExpect(model().attributeExists("cBean"));
    }

    @Test
    public void courseAddTestPass() throws Exception{
        Course course = new Course();
        course.setId(1);
        course.setCourseId("CUD-001");
        course.setCourseName("Java");
        this.mockMvc.perform(post("/courseAdd").flashAttr("cBean",course))
        .andExpect(status().is(302))
        .andExpect(redirectedUrl("/courseAddPageNext"));
    }

    @Test
    public void courseAddTestFail() throws Exception{
        Course course = new Course();
        course.setId(1);
        course.setCourseId("CUD-001");
        course.setCourseName("");
        this.mockMvc.perform(post("/courseAdd").flashAttr("cBean",course))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("errorFill"))
        .andExpect(view().name("BUD003"));
    }
}
