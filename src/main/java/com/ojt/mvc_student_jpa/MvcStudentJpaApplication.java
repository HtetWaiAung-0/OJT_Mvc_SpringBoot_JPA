package com.ojt.mvc_student_jpa;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ojt.mvc_student_jpa.model.Student;
import com.ojt.mvc_student_jpa.repo.StudentRepo;
import com.ojt.mvc_student_jpa.service.ReportService;

import net.sf.jasperreports.engine.JRException;

@SpringBootApplication
@RestController
public class MvcStudentJpaApplication {
	@Autowired
	private StudentRepo studentRepo;

	@Autowired
	private ReportService reportService;

	@GetMapping("/getStudent")
	public List<Student> getStudent(){
		return studentRepo.findAll();
	}

	@GetMapping("/report/{format}")
	public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException{
		return reportService.exportReport(format);
	}

	public static void main(String[] args) {
		SpringApplication.run(MvcStudentJpaApplication.class, args);
	}

}
