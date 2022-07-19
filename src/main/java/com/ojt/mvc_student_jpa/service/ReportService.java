package com.ojt.mvc_student_jpa.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.ojt.mvc_student_jpa.model.Student;
import com.ojt.mvc_student_jpa.repo.StudentRepo;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;

@Service
public class ReportService {
    @Autowired
    private StudentRepo studentRepo;

    public String exportReport(String   reportFormat) throws FileNotFoundException, JRException{
        String path = "C:\\Users\\DELL\\Desktop\\javaEE-workspace\\OJT_Mvc_SpringBoot_JPA\\src\\main\\resources";
        List<Student> student = studentRepo.findAll();
        File file = ResourceUtils.getFile("classpath:student.jrxml");
        JasperReport jasperReport =JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource =new JRBeanCollectionDataSource(student);
        Map<String,Object> parameter = new HashMap<>();
        parameter.put("createBy", "Java Techie");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, dataSource); 
        if(reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\student.html");
        }
        if(reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\student.pdf");
        }
        return "report generate in path : " + path;
    }
}
