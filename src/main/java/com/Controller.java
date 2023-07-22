package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@org.springframework.stereotype.Controller
public class Controller {

	@Autowired
	Repo repo;
	@GetMapping("/")
	public String view() {
		return "view";
	}
	@GetMapping("/view")
	public String show(Model model) {
		model.addAttribute("students", repo.findByApproved(0));
		return "showtable";
	}
	@GetMapping("/approve/{id}")
	public String app(@PathVariable("id") String id) {
		Student student=repo.findById(id).get();
		student.setApproved(1);
		repo.save(student);
		return "redirect:/";
	}
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") String id, Model model) {
		Student student=repo.findById(id).get();
		model.addAttribute("std", student);
		return "confdel";
	}
	@GetMapping("/delproc")
	public String dele(Student student) {
		repo.deleteById(student.getId());
		return "redirect:/";
	}
	
}
