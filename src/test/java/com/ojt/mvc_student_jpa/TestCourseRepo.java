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

import com.ojt.mvc_student_jpa.model.Course;
import com.ojt.mvc_student_jpa.repo.CourseRepo;

@SpringBootTest
public class TestCourseRepo {
    
    @Mock
    CourseRepo repo;

    @Test
    public void findAllTest(){
        
        Course course1 = new Course();
        course1.setId(1);
        course1.setCourseId("CUD-001");
        course1.setCourseName("Java");
        Course course2 = new Course();
        course2.setId(2);
        course2.setCourseId("CUD-002");
        course2.setCourseName("C");
        Course course3 = new Course();
        course3.setId(1);
        course3.setCourseId("CUD-003");
        course3.setCourseName("PHP");

        List<Course> clist1 = new ArrayList<Course>();
        clist1.add(course1);
        clist1.add(course2);
        clist1.add(course3);

        when(repo.findAll()).thenReturn(clist1);
        List<Course> cList = repo.findAll();
        assertEquals(3, cList.size());

        verify(repo,times(1)).findAll();

    }

    @Test
    public void saveTest(){
        Course course1 = new Course();
        course1.setId(1);
        course1.setCourseId("CUD-001");
        course1.setCourseName("Java");
        repo.save(course1);
        verify(repo,times(1)).save(course1);
    }
}
