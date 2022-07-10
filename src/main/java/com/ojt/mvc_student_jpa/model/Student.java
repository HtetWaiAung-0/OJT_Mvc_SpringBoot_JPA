package com.ojt.mvc_student_jpa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

@Entity
public class Student {
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
    @Column(name = "stu_id",nullable = false)
    private String stuId;
    @Column(name = "stu_name",nullable = false)
	private String stuName;
	@Column(name = "stu_dob",nullable = false)
	private String stuDob;
	@Column(name = "stu_gender",nullable = false)
	private String stuGender;
	@Column(name = "stu_phone",nullable = false)
	private String stuPhone;
	@Column(name = "stu_education",nullable = false)
	private String stuEducation;

	@ManyToMany(cascade=CascadeType.PERSIST,fetch = FetchType.EAGER)
	@JoinTable(
			 name="student_course",
			 joinColumns = @JoinColumn(name="student_id"),
			 inverseJoinColumns = @JoinColumn(name="course_id")
			)
	private List<Course> stuCourse=new ArrayList<Course>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuDob() {
        return stuDob;
    }

    public void setStuDob(String stuDob) {
        this.stuDob = stuDob;
    }

    public String getStuGender() {
        return stuGender;
    }

    public void setStuGender(String stuGender) {
        this.stuGender = stuGender;
    }

    public String getStuPhone() {
        return stuPhone;
    }

    public void setStuPhone(String stuPhone) {
        this.stuPhone = stuPhone;
    }

    public String getStuEducation() {
        return stuEducation;
    }

    public void setStuEducation(String stuEducation) {
        this.stuEducation = stuEducation;
    }

    public List<Course> getStuCourse() {
        return stuCourse;
    }

    public void setStuCourse(List<Course> stuCourse) {
        this.stuCourse = stuCourse;
    }
    public Student(){

    }

	@Override
    public String toString() {
        return "Student [id=" + id + ", stuCourse=" + stuCourse + ", stuDob=" + stuDob + ", stuEducation="
                + stuEducation + ", stuGender=" + stuGender + ", stuId=" + stuId + ", stuName=" + stuName
                + ", stuPhone=" + stuPhone + "]";
    }
    
    

    
}
