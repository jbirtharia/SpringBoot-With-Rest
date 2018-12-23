package com.phoniex.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.phoniex.demo.beans.Student;

@Service
public class StudentService {

	@Autowired
	MongoTemplate mongoTemplate;
	
	private static String STUDENT="studentDetails";
	
	public Student FetchById(String id)
	{
		Query query = new Query(Criteria.where("_id").is(id));
		Student stud = mongoTemplate.findOne(query, Student.class,STUDENT);
		return stud;
	}
	
	public Boolean Insert(Student stud)
	{
		mongoTemplate.insert(stud,STUDENT);
		return true;
	}
	
	public List<Student> FindAll() {
		List<Student> students =  mongoTemplate.findAll(Student.class, STUDENT);    		
		return students;
	}
	
	public void Delete(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		mongoTemplate.findAndRemove(query,Student.class,STUDENT);
	}
	
	public void Update(Student student) {
		Update updateList = new Update();
		updateList.set("marks",student.getMarks());
		updateList.set("name",student.getName());
		mongoTemplate.updateFirst(new Query(Criteria.where("_id").is(student.getId())),updateList, STUDENT);
	}

	
}
