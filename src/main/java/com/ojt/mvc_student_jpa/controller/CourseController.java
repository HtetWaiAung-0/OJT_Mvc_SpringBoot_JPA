package com.ojt.mvc_student_jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import com.ojt.mvc_student_jpa.model.Course;
import com.ojt.mvc_student_jpa.repo.CourseRepo;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CourseController {
    @Autowired
    private CourseRepo courseRepo;

    @RequestMapping(value="/courseAddPage", method=RequestMethod.GET)
    public ModelAndView courseAddPage() {
        Course cBean = new Course();
        int i = courseRepo.getId();
        String format = "COU" + String.format("%03d", i);
        cBean.setCourseId(format);
        return new ModelAndView("BUD003", "cBean", cBean);
    }

    @RequestMapping(value="/courseAddPageNext", method=RequestMethod.GET)
    public ModelAndView courseAddPageNext(ModelMap model) {
        Course cBean = new Course();
        int i = courseRepo.getId();
        String format = "COU" + String.format("%03d", i);
        cBean.setCourseId(format);
        model.addAttribute("errorFill", "Success Add");
        return new ModelAndView("BUD003", "cBean", cBean);
    }
    
    @RequestMapping(value = "/courseAdd", method = RequestMethod.POST)
    public String courseAdd(@ModelAttribute("cBean") Course cBean, ModelMap model) {
        if (cBean.getCourseName().isBlank()) {

            model.addAttribute("errorFill", "Fill the Blank!!!");
            return "BUD003";
        } else { 
            courseRepo.save(cBean);
            return "redirect:/courseAddPageNext";
        }

    }
}
