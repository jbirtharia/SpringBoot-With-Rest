package com.phoniex.demo.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.phoniex.demo.beans.Student;
import com.phoniex.demo.service.StudentService;

@RestController
@RequestMapping("/app")
public class HelloController {
	
	@Autowired
	StudentService studentService;

	@RequestMapping(method = RequestMethod.GET, value = "/student/{id}")
	public Student DataById(@PathVariable("id") String id)
	{
		return studentService.FetchById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/insert")
	@ResponseStatus(org.springframework.http.HttpStatus.CREATED)
	public void InsertTranscript(@RequestBody Student stud)
	{
		studentService.Insert(stud);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/allstudent")
	public List<Student> AllData()
	{
		return studentService.FindAll();
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/student/delete/{id}")
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	public void DeleteById(@PathVariable("id") String id)
	{
		studentService.Delete(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/update")
	@ResponseStatus(org.springframework.http.HttpStatus.OK)
	public void UpdateById(@RequestBody Student stud)
	{
		studentService.Update(stud);
	}

}
