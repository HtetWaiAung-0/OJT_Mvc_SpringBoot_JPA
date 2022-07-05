package com.ojt.mvc_student_jpa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ojt.mvc_student_jpa.model.Course;
import com.ojt.mvc_student_jpa.model.Student;
import com.ojt.mvc_student_jpa.repo.CourseRepo;
import com.ojt.mvc_student_jpa.repo.StudentRepo;

@Controller
public class StudentController {
    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private StudentRepo studentRepo;

    @RequestMapping(value = "/stuAddPage", method = RequestMethod.GET)
	public ModelAndView stuAddPage(ModelMap model) {
		Student stuBean = new Student();
		int i = studentRepo.getId();
		String finalStuString = "STU" + String.format("%03d", i);
		stuBean.setStuId(finalStuString);
		List<Course> s = courseRepo.findAll();
		model.addAttribute("courseList", s);
		return new ModelAndView("STU001", "stuBean", stuBean);
	}

    @RequestMapping(value = "/stuAddNextPage", method = RequestMethod.GET)
	public ModelAndView stuAddNextPage(ModelMap model) {
		Student stuBean = new Student();
		int i = studentRepo.getId();
		String finalStuString = "STU" + String.format("%03d", i);
		stuBean.setStuId(finalStuString);
        List<Course> s = courseRepo.findAll();
		model.addAttribute("courseList", s);
		model.addAttribute("errorFill", "Success Add");
		return new ModelAndView("STU001", "stuBean", stuBean);
	}

    @RequestMapping(value = "/addStu", method = RequestMethod.POST)
	public String addStu(@ModelAttribute("stuBean") Student stuBean, ModelMap model) {

		if (stuBean.getStuName().isBlank() || stuBean.getStuDob().isBlank() || stuBean.getStuGender().isBlank()
				|| stuBean.getStuPhone().isBlank() || stuBean.getStuEducation().isBlank()) {
			model.addAttribute("errorFill", "Fill the Blank!!!");
			model.addAttribute("courseList", courseRepo.findAll());
			return "STU001";
		} else {

			studentRepo.save(stuBean);
			return "redirect:/stuAddNextPage";

		}
	}

    @RequestMapping(value = "/updateStu", method = RequestMethod.POST)
	public String updateStu(@ModelAttribute("stuBean") Student stuBean, ModelMap model) {

		if (stuBean.getStuName().isBlank()) {
			model.addAttribute("errorFill", "Fill the Blank!!!");
			model.addAttribute("courseList", courseRepo.findAll());
			return "USR003";
		} else {
            studentRepo.deleteById(stuBean.getId());
			studentRepo.save(stuBean);
			return "redirect:/stuSearchPage";
		}
	}


	@RequestMapping(value = "/updateStuPage", method = RequestMethod.GET)
	public ModelAndView updateStuPage(@RequestParam("id") String stuId, ModelMap model) {

		Student res = studentRepo.findByStuId(stuId);
		model.addAttribute("courseList", courseRepo.findAll());

		return new ModelAndView("STU002", "stuBean", res);
	}

	@RequestMapping(value = "/deleteStu", method = RequestMethod.GET)
	public String deleteStu(@RequestParam("id") String stuId, ModelMap model) {

        Student res = studentRepo.findByStuId(stuId);
		studentRepo.deleteById(res.getId());
		model.addAttribute("errorFill", "Success delete");
		return "redirect:/stuSearchPage";
	}

	@RequestMapping(value = "/stuSearchPage", method = RequestMethod.GET)
	public String stuSearchPage(ModelMap model) {

		List<Student> list = studentRepo.findAll();
		model.addAttribute("stuList", list);
		return "STU003";
	}

	@RequestMapping(value = "/searchStu", method = RequestMethod.POST)
	public String searchStu(@ModelAttribute("stuBean") Student stuBean, @RequestParam("searchId") String searchId,
			@RequestParam("searchName") String searchName,
			@RequestParam("searchCourse") String searchCourse, ModelMap model) {

		List<Student> showList = new ArrayList<>();
		if (searchId.isBlank() && searchName.isBlank() && searchCourse.isBlank()) {

			showList = studentRepo.findAll();
			model.addAttribute("stuList", showList);
			return "STU003";
		} else {
			searchId = searchId.isBlank() ? "#$*@" : searchId;
			searchName = searchName.isBlank() ? "#$*@" : searchName;
			searchCourse = searchCourse.isBlank() ? "#$*@" : searchCourse;
			showList = studentRepo.findDistinctByStuIdOrStuNameContainingOrStuCourse_CourseNameContaining(searchId, searchName, searchCourse);

			model.addAttribute("stuList", showList);
			return "STU003";
		}
	}
}
