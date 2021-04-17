package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.StudentRepo;
import com.example.demo.model.Student;

@RestController
public class StudentController {
	static int i = 1;
	@Autowired
	StudentRepo repo;

	// gettting student
	@RequestMapping("/stud/{sid}")
	@ResponseBody
	public Optional<Student> getStudent(@PathVariable("sid") int sid) {
		System.out.println("getting student...!!! " + i);
		i++;
		return repo.findById(sid);
	}

	// getting data either json or XML
	@RequestMapping(path="/stud/",produces = {"application/json"})
	@ResponseBody
	public List<Student> getJsonXmlStudent() {
		System.out.println("getting student...!!! " + i);
		i++;
		return repo.findAll();

	}
	
	@PutMapping(path = "/upstud",produces = {"application/json"})
	public Student updateAndCreate(@RequestBody Student stud){
		repo.save(stud);
		return stud;
	}
	
	//deleting record
	@DeleteMapping("student/{aid}")
	public String deleteStudent(@PathVariable int aid) {
		Student stud=repo.getOne(aid);
		repo.delete(stud);
		return "deltedd";
	}
	
	//saving,updating 
	@PostMapping(path="/student",consumes = {"application/json"})
	public Student addStudent(@RequestBody Student stud) {
		repo.save(stud);
		return stud;
	}
	
}
