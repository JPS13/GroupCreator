package model;

import java.util.*;

public abstract class Model {
	
	protected int id;
	protected Set<Student> students = new HashSet<>();
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id; 
	}
	
	public Set<Student> getStudents() {
		return students;
	}
	
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
}
