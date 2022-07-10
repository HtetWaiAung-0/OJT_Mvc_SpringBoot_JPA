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
import com.ojt.mvc_student_jpa.model.Student;
import com.ojt.mvc_student_jpa.repo.StudentRepo;

@SpringBootTest
public class TestStuRepo {
    @Mock
    StudentRepo repo;

    @Test
    public void findAllTest(){
        List<Student> list = new ArrayList<Student>();
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
        List<Course> clist2 = new ArrayList<Course>();
        clist2.add(course2);
        clist2.add(course3);
        List<Course> clist3 = new ArrayList<Course>();
        clist3.add(course1);
        clist3.add(course2);
        clist3.add(course3);

        Student stu1 = new Student() ;
        stu1.setId(1);
        stu1.setStuId("STU-001");
        stu1.setStuName("PKaR");
        stu1.setStuGender("male");
        stu1.setStuPhone("09123123");
        stu1.setStuEducation("Bachlor in IT");
        stu1.setStuCourse(clist1);

        Student stu2 = new Student() ;
        stu2.setId(2);
        stu2.setStuId("STU-002");
        stu2.setStuName("Htet Wai Aung");
        stu2.setStuGender("male");
        stu2.setStuPhone("09123123");
        stu2.setStuEducation("Bachlor in IT");
        stu2.setStuCourse(clist2);

        Student stu3 = new Student() ;
        stu3.setId(3);
        stu3.setStuId("STU-003");
        stu3.setStuName("Soe Soe");
        stu3.setStuGender("female");
        stu3.setStuPhone("09123123");
        stu3.setStuEducation("Bachlor in IT");
        stu3.setStuCourse(clist3);

        list.add(stu1);
        list.add(stu2);
        list.add(stu3);

        when(repo.findAll()).thenReturn(list);
        List<Student> stuList = repo.findAll();
        assertEquals(3, stuList.size());

        verify(repo,times(1)).findAll();
    }

    @Test
    public void findByStuIdTest(){

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
        stu1.setStuGender("male");
        stu1.setStuPhone("09123123");
        stu1.setStuEducation("Bachlor in IT");
        stu1.setStuCourse(clist1);
        List<Student> stu = new ArrayList<>();
        stu.add(stu1);
        when(repo.findByStuId(1)).thenReturn(stu);
        List<Student> getStu = repo.findByStuId(1);
        assertEquals(stu, getStu);


    }

    @Test
    public void saveTest(){
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
        stu1.setStuGender("male");
        stu1.setStuPhone("09123123");
        stu1.setStuEducation("Bachlor in IT");
        stu1.setStuCourse(clist1);
        repo.save(stu1);
        verify(repo,times(1)).save(stu1);

    }

    @Test
    public void deleteByStuIdTest(){
        repo.deleteById(1);
        verify(repo,times(1)).deleteById(1);
    }

    @Test
    // List<Student> findDistinctByStuIdOrStuNameContainingOrStuCourse_CourseNameContaining(String stuId, String stuName, String courseName);
    public void findDistinctByStuIdOrStuNameContainingOrStuCourse_CourseNameContainingTest(){
        List<Student> list = new ArrayList<Student>();
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
        // clist1.add(course2);
        List<Course> clist2 = new ArrayList<Course>();
        clist2.add(course2);
        // clist2.add(course3);
        List<Course> clist3 = new ArrayList<Course>();
        // clist3.add(course1);
        // clist3.add(course2);
        clist3.add(course3);

        Student stu1 = new Student() ;
        stu1.setId(1);
        stu1.setStuId("STU-001");
        stu1.setStuName("PKaR");
        stu1.setStuGender("male");
        stu1.setStuPhone("09123123");
        stu1.setStuEducation("Bachlor in IT");
        stu1.setStuCourse(clist1);

        Student stu2 = new Student() ;
        stu2.setId(2);
        stu2.setStuId("STU-002");
        stu2.setStuName("Htet Wai Aung");
        stu2.setStuGender("male");
        stu2.setStuPhone("09123123");
        stu2.setStuEducation("Bachlor in IT");
        stu2.setStuCourse(clist2);

        Student stu3 = new Student() ;
        stu3.setId(3);
        stu3.setStuId("STU-003");
        stu3.setStuName("Soe Soe");
        stu3.setStuGender("female");
        stu3.setStuPhone("09123123");
        stu3.setStuEducation("Bachlor in IT");
        stu3.setStuCourse(clist3);

       /*   list.add(stu1); */
        list.add(stu2);
        /* list.add(stu3);  */

        when(repo.findDistinctByStuIdOrStuNameContainingOrStuCourse_CourseNameContaining("STU-002", "@#$" ,"@#$" )).thenReturn(list);
        List<Student> stuList = repo.findDistinctByStuIdOrStuNameContainingOrStuCourse_CourseNameContaining("STU-002", "@#$", "@#$");
        assertEquals(1, stuList.size());
        for(Student check : stuList){
            assertEquals("Htet Wai Aung", check.getStuName());
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
}
