package com.ojt.mvc_student_jpa.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ojt.mvc_student_jpa.model.User;
import com.ojt.mvc_student_jpa.repo.UserRepo;

@Controller
public class UserController {
	@Autowired
	private UserRepo userRepo;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPost(@RequestParam("loginMail") String loginMail,
			@RequestParam("loginPassword") String loginPassword, HttpSession session, ModelMap model) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd");
		session.setAttribute("date", LocalDate.now().format(formatter));


		if ( userRepo.existsByUserIdAndUserMail(loginMail, loginPassword) || (loginMail.equals("admin@gmail.com") && loginPassword.equals("123"))) {

			session.setAttribute("loginName", loginMail);
			session.setAttribute("loginPassword", loginPassword);
			return "MNU001";

		} else {
			model.addAttribute("error", " Please check your data again.");
			return "LGN001";
		}
	}

	@RequestMapping(value = "/logOut", method = RequestMethod.GET)
	public String login(HttpSession session) {
		session.removeAttribute("loginName");
		session.removeAttribute("loginPassword");
		session.removeAttribute("date");
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping(value = "/welcomePage", method = RequestMethod.GET)
	public String welcomePage() {
		return "MNU001";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView login() {
		return new ModelAndView("LGN001", "userBean", new User());
	}

	@RequestMapping(value = "/addUserPage", method = RequestMethod.GET)
	public ModelAndView addUserPage() {
		return new ModelAndView("USR001", "userBean", new User());
	}

	@RequestMapping(value = "/addUserNextPage", method = RequestMethod.GET)
	public ModelAndView addUserNextPage(ModelMap model) {
		model.addAttribute("errorFill", "Success Register!");
		return new ModelAndView("USR001", "userBean", new User());
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("userBean") User userBean, @RequestParam("conPassword") String conPassword,
			ModelMap model) {

		if (userBean.getUserMail().isBlank() || userBean.getUserPassword().isBlank()
				|| conPassword.isBlank() || userBean.getUserRole().isBlank()) {
			model.addAttribute("errorFill", "Fill the Blank!!!");
			return "USR001";
		} else {
			int i = userRepo.getId();
			String stuIdString = String.format("%03d", i);
			String finalString = "USR" + stuIdString;
			userBean.setUserId(finalString);
			userRepo.save(userBean);
			return "redirect:/addUserNextPage";
		}
	}

	@RequestMapping(value = "/updateUserPage", method = RequestMethod.GET)
	public ModelAndView updateUserPage(@RequestParam("id") int id, ModelMap model) {

		return new ModelAndView("USR002", "userBean", userRepo.findById(id));
	}

	@RequestMapping(value = "/searchUserPage", method = RequestMethod.GET)
	public String searchUserPage(ModelMap model) {
		List<User> list = userRepo.findAll();
		model.addAttribute("userList", list);
		return "USR003";
	}

	@RequestMapping(value = "/searchUser", method = RequestMethod.POST)
	public String searchUser(@ModelAttribute("userBean") User userBean, @RequestParam("searchId") String searchId,
			@RequestParam("searchMail") String searchMail, ModelMap model) {
		List<User> showList = new ArrayList<>();
		if (searchId.isBlank() && searchMail.isBlank()) {
			showList = userRepo.findAll();
			model.addAttribute("userList", showList);
			return "USR003";
		} else {
			showList = userRepo.findByUserIdOrUserMail(searchId, searchMail);
			model.addAttribute("userList", showList);
			return "USR003";
		}
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("userBean") User userBean, @RequestParam("conPassword") String conPassword,
			ModelMap model) {
		if (userBean.getUserMail().isBlank() || userBean.getUserPassword().isBlank()
				|| conPassword.isBlank() || userBean.getUserRole().isBlank()) {
			model.addAttribute("errorFill", "Fill the Blank!!!");
			return "USR002";
		} else {
			userRepo.save(userBean);
			model.addAttribute("errorFill", "Success Register!");
			return "redirect:/searchUserPage";
		}
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public String deleteUser(@RequestParam("id") int id, ModelMap model) {
		
		userRepo.deleteById(id);
		return "redirect:/searchUserPage";
	}
}
