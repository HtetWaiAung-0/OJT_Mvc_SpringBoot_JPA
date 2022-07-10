package com.ojt.mvc_student_jpa;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.ojt.mvc_student_jpa.model.Course;
import com.ojt.mvc_student_jpa.model.Student;
import com.ojt.mvc_student_jpa.repo.StudentRepo;

@SpringBootTest
@AutoConfigureMockMvc
public class TestStudentController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    StudentRepo studentRepo;

    @Test
    public void stuAddPageTest() throws Exception{
        this.mockMvc.perform(get("/stuAddPage"))
        .andExpect(status().isOk())
        .andExpect(view().name("STU001"))
        .andExpect(model().attributeExists("courseList"))
        .andExpect(model().attributeExists("stuBean"));
    }
    
    @Test
    public void stuAddNextPageTest() throws Exception{
        this.mockMvc.perform(get("/stuAddNextPage"))
        .andExpect(status().isOk())
        .andExpect(view().name("STU001"))
        .andExpect(model().attributeExists("courseList"))
        .andExpect(model().attributeExists("errorFill"))
        .andExpect(model().attributeExists("stuBean"));
    }
    

    @Test
    public void addStuPassTest() throws Exception{
        Course course1 = new Course();
        course1.setId(1);
        course1.setCourseId("CUD-001");
        course1.setCourseName("Java");

        Course course2 = new Course();
        course2.setId(2);
        course2.setCourseId("CUD-002");
        course2.setCourseName("C");

        List<Course> clist1 = new ArrayList<Course>();
        clist1.add(course1);
        clist1.add(course2);
        Student stu1 = new Student() ;
        stu1.setId(1);
        stu1.setStuId("STU-001");
        stu1.setStuName("PKaR");
        stu1.setStuDob("12-02-2001");
        stu1.setStuGender("male");
        stu1.setStuPhone("09123123");
        stu1.setStuEducation("Bachlor in IT");
        stu1.setStuCourse(clist1);

        this.mockMvc.perform(post("/addStu").flashAttr("stuBean",stu1))
        .andExpect(status().is(302))
        .andExpect(redirectedUrl("/stuAddNextPage"));
    }

    @Test
    public void addStuFailTest() throws Exception{
        Course course1 = new Course();
        course1.setId(1);
        course1.setCourseId("CUD-001");
        course1.setCourseName("Java");

        Course course2 = new Course();
        course2.setId(2);
        course2.setCourseId("CUD-002");
        course2.setCourseName("C");

        List<Course> clist1 = new ArrayList<Course>();
        clist1.add(course1);
        clist1.add(course2);
        Student stu1 = new Student() ;
        stu1.setId(1);
        stu1.setStuId("STU-001");
        stu1.setStuName("PKaR");
        stu1.setStuDob("12-02-2001");
        stu1.setStuGender("");
        stu1.setStuPhone("09123123");
        stu1.setStuEducation("Bachlor in IT");
        stu1.setStuCourse(clist1);

        this.mockMvc.perform(post("/addStu").flashAttr("stuBean",stu1))
        .andExpect(status().is(200))
        .andExpect(view().name("STU001"))
        .andExpect(model().attributeExists("courseList"))
        .andExpect(model().attributeExists("errorFill"));
    }

    @Test
    public void updateStuFailTest()throws Exception{
        Course course1 = new Course();
        course1.setId(1);
        course1.setCourseId("CUD-001");
        course1.setCourseName("Java");

        Course course2 = new Course();
        course2.setId(2);
        course2.setCourseId("CUD-002");
        course2.setCourseName("C");

        List<Course> clist1 = new ArrayList<Course>();
        clist1.add(course1);
        clist1.add(course2);
        Student stu1 = new Student() ;
        stu1.setId(1);
        stu1.setStuId("STU-001");
        stu1.setStuName("123");
        stu1.setStuDob("12-02-2001");
        stu1.setStuGender("");
        stu1.setStuPhone("09123123");
        stu1.setStuEducation("Bachlor in IT");
        stu1.setStuCourse(clist1);

        this.mockMvc.perform(post("/updateStu").flashAttr("stuBean",stu1))
        .andExpect(status().is(200))
        .andExpect(view().name("STU002"))
        .andExpect(model().attributeExists("courseList"))
        .andExpect(model().attributeExists("errorFill"));
    }

    @Test
    public void updateStuPassTest() throws Exception{
        Course course1 = new Course();
        course1.setId(1);
        course1.setCourseId("CUD-001");
        course1.setCourseName("Java");

        Course course2 = new Course();
        course2.setId(2);
        course2.setCourseId("CUD-002");
        course2.setCourseName("C");

        List<Course> clist1 = new ArrayList<Course>();
        clist1.add(course1);
        clist1.add(course2);
        Student stu1 = new Student() ;
        stu1.setId(1);
        stu1.setStuId("STU-001");
        stu1.setStuName("PKaR");
        stu1.setStuDob("12-02-2001");
        stu1.setStuGender("male");
        stu1.setStuPhone("09123123");
        stu1.setStuEducation("Bachlor in IT");
        stu1.setStuCourse(clist1);

        this.mockMvc.perform(post("/updateStu").flashAttr("stuBean",stu1))
        .andExpect(status().is(302))
        .andExpect(redirectedUrl("/stuSearchPage"));
    }

    @Test
    public void updateStuPageTest() throws Exception{
        this.mockMvc.perform(get("/updateStuPage").param("id", "123"))
        .andExpect(status().isOk())
        .andExpect(view().name("STU002"))
        .andExpect(model().attributeExists("stu"))
        .andExpect(model().attributeExists("courseList"))
        .andExpect(model().attributeExists("stuBean"));
    }

    @Test
    public void deleteStuTest() throws Exception{
        this.mockMvc.perform(get("/deleteStu").param("id", "1"))
        .andExpect(status().is(302))
        .andExpect(redirectedUrl("/stuSearchPage"));
    }

    @Test
    public void stuSearchPageTest() throws Exception{
        this.mockMvc.perform(get("/stuSearchPage"))
        .andExpect(status().isOk())
        .andExpect(view().name("STU003"))
        .andExpect(model().attributeExists("stuList"));
    }

    

    @Test
    public void searchStuPassTest() throws Exception{
        this.mockMvc.perform(post("/searchStu").param("searchId", "123").param("searchName", "123").param("searchCourse", "123"))
        .andExpect(status().isOk())
        .andExpect(view().name("STU003"))
        .andExpect(model().attributeExists("stuList"));
    }

    @Test
    public void searchStuFailTest() throws Exception{
        this.mockMvc.perform(post("/searchStu").param("searchId", "").param("searchName", "").param("searchCourse", ""))
        .andExpect(status().isOk())
        .andExpect(view().name("STU003"))
        .andExpect(model().attributeExists("stuList"));
    }
}
