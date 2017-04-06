package model;

import java.util.*;

public abstract class Model {
	
	protected int id;
	protected List<Student> studentList = new ArrayList<>();
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id; 
	}
	
	public List<Student> getStudentList() {
		return studentList;
	}
	
	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

}
