package com.ojt.mvc_student_jpa.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.ojt.mvc_student_jpa.model.Student;
import com.ojt.mvc_student_jpa.repo.StudentRepo;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Service
public class ReportService {
    @Autowired
    private StudentRepo studentRepo;

    public void exportReport(String   reportFormat,HttpServletResponse response) throws JRException, IOException{
        String path = "C:\\Users\\Admin\\eclipse-workspace\\OJT_Mvc_SpringBoot_JPA\\src\\main\\resources";
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
        else if(reportFormat.equalsIgnoreCase("pdf")){
            /* JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(response.getOutputStream()); */
            JasperExportManager.exportReportToPdfStream(jasperPrint,response.getOutputStream());
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "inline; filename=student.pdf;");
            
        }
        else if(reportFormat.equalsIgnoreCase("excel")){
            JRXlsxExporter exporter = new JRXlsxExporter(); // initialize exporter 
          exporter.setExporterInput(new SimpleExporterInput(jasperPrint)); // set compiled report as input
          
          SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
          configuration.setOnePagePerSheet(true); // setup configuration
          configuration.setDetectCellType(true);
          exporter.setConfiguration(configuration); 
          exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream())); 
          response.setContentType("application/xlsx");
            response.addHeader("Content-Disposition", "inline; filename=student.xlsx;"); 
          exporter.exportReport();
        }
        
    }
}
