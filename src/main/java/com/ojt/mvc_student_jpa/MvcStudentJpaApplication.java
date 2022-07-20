package com.ojt.mvc_student_jpa;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.View;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ojt.mvc_student_jpa.model.Student;
import com.ojt.mvc_student_jpa.repo.StudentRepo;
import com.ojt.mvc_student_jpa.service.ReportService;

import net.sf.jasperreports.engine.JRException;


@SpringBootApplication
@Controller
public class MvcStudentJpaApplication {
	@Autowired
	private StudentRepo studentRepo;

	@Autowired 
	private ServletContext context;

	@Autowired
	private ReportService reportService;

	@GetMapping("/getStudent")
	public List<Student> getStudent(){
		return studentRepo.findAll();
	}

	@GetMapping("/report/{format}")
	public void generateReport(HttpServletResponse response,HttpServletRequest request,@PathVariable String format) throws JRException, IOException{
		 reportService.exportReport(format,response);
		
		
	}

	// private void fileDownload(String fullPath,HttpServletResponse response,String filename) throws IOException{
	// 	File  file = new File(fullPath);
	// 	final int BUFFER_SIZE = 4096;
	// 	if(file.exists()){
	// 		try {
	// 			FileInputStream inputStream = new FileInputStream(file);
	// 			String mineType = context.getMimeType(fullPath);
	// 			response.setContentType(mineType);
	// 			response.setHeader("content-disposition", "attachment; filename = " + filename);
	// 			OutputStream outputStream = response.getOutputStream();
	// 			byte[] buffer = new byte[BUFFER_SIZE];
	// 			int byteRead = -1;
	// 			while((byteRead = inputStream.read(buffer))!= -1){
	// 				outputStream.write(buffer,0,byteRead);
	// 			}
	// 			inputStream.close();
	// 			outputStream.close();
	// 			file.delete();

	// 		} catch (FileNotFoundException e) {
	// 			// TODO Auto-generated catch block
	// 			e.printStackTrace();
	// 		}
			
	// 	}
	// }

	public static void main(String[] args) {
		SpringApplication.run(MvcStudentJpaApplication.class, args);
	}

}
