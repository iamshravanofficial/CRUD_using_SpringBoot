package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmpService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@Autowired
	private EmpService empService;
	
	@GetMapping("/")
	public String index(Model m) {
		List<Employee> list = empService.getAllEmp();
		m.addAttribute("empList", list);
		return "index";
	}
	
	@GetMapping("/loadsave")
	public String loadsave() {
		return "save_emp";
	}
	
	@GetMapping("/loadedit/{id}")
	public String loadedit(@PathVariable int id,Model m) {
		System.out.println(id);
		Employee emp = empService.getEmpId(id);
		m.addAttribute("emp",emp);
		return "edit_emp";
	}
	
	@PostMapping("/saveemp")
	public String SaveEmployee(@ModelAttribute Employee emp,HttpSession session) {
		System.out.println(emp);
		Employee newEmp = empService.saveEmployee(emp);
		
		if(newEmp!=null) {
			System.out.println("Save Success");
			session.setAttribute("message", "Resgister Succesfully");
		}else {
			System.out.println("Went Wrong");
			session.setAttribute("message", "Something Went Wrong");
		}
		
		return "redirect:/loadsave";
	}
	
	@PostMapping("/updateEmp")
	public String updateEmp(@ModelAttribute Employee emp,HttpSession session) {
		System.out.println(emp);
		Employee updateEmp = empService.saveEmployee(emp);
		
		if(updateEmp!=null) {
			System.out.println("Save Success");
			session.setAttribute("message", "Update Succesfully");
		}else {
			System.out.println("Went Wrong");
			session.setAttribute("message", "Something Went Wrong");
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/deleteemp/{id}")
	public String deleteEmp(@PathVariable int id,HttpSession session) {
		boolean f = empService.deleteEmp(id);
		if(f) {
			session.setAttribute("message", "deleted");
		}else {
			session.setAttribute("message", "Something Went Wrong");
		}
		return "redirect:/";
	}
}
