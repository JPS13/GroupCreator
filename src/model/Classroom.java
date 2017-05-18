package model;

import java.util.*;

public class Classroom extends Model {
	
	private int schoolYear;
	private String teacher;
	private String subject;
	private int grade;
	private int maximumFrontGroups;		
	private List<Group> groupList = new ArrayList<>();
	
	public Classroom() {
		maximumFrontGroups = 10;		
	}

	/**
	 * This method takes in two students and adds them to each other's list of
	 * incompatible students as long as each student's list has room. This will 
	 * be used when groups are created to avoid these two students being in the 
	 * same group. Both lists must have enough room for another student to return
	 * true. A false return means that the students were not added to each other's
	 *  list. 
	 * 
	 * @param student1 The first incompatible student.
	 * @param student2 The second incompatible student.
	 * @return Returns true if the students were added to each other's list,
	 * 			otherwise false.
	 */
	
	public boolean addIncompatibleStudents(Student student1, Student student2) {
		
		if(student1.getStudents().size() < 4 && 
				student2.getStudents().size() < 4) {
			student1.getStudents().add(student2);
			student2.getStudents().add(student1);
			return true;
		} else 
			return false;			
	}
	
	/**
	 * This method adds a student to the list of students in the class if 
	 * doing so does not cause too many students requiring front seats.
	 * 
	 * @param student The student to be added to the list.
	 * @return True if student added to classList, otherwise false.
	 */
	
	public boolean addStudent(Student student) {
		
		int maxFrontStudents = maximumFrontGroups * 2;
		int frontStudents = 0;
		
		// Count how many students need to sit up front
		for(Student s: students) {
			if(s.getFrontSeatNeeded())
				frontStudents++;
		}
		
		// Include this student
		if(student.getFrontSeatNeeded())
			frontStudents++;
		
		if(frontStudents <= maxFrontStudents) {
			students.add(student);
			return true;
		}
		
		else {
			System.out.println("Too many students required to sit up front. Limit to " 
					+ maxFrontStudents + " students.");
			return false;
		}
		
	}
	
	/**
	 * This method compares this classroom to another
	 * classroom. 
	 * 
	 * @param classroom The other classroom compared.
	 * @return True if fields match, otherwise false.
	 */
	
	public boolean equals(Classroom classroom) {
		return( this.schoolYear == classroom.getSchoolYear() &&
				this.teacher.equals(classroom.getTeacher()) &&
				this.grade == classroom.getGrade() &&
				this.subject == classroom.getSubject() &&
				this.maximumFrontGroups == classroom.getMaximumFrontGroups() &&
				this.students.equals(classroom.getStudents()) &&
				this.groupList.equals(classroom.getGroupList()));
	}
	
	/**
	 * This method returns the grade level of the classroom.
	 * 
	 * @return The classroom grade is returned. 
	 */
	
	public int getGrade() {
		return grade;
	}
	
	/**
	 * This method returns the ArrayList of groups.
	 * 
	 * @return The list of groups is returned.
	 */
	
	public List<Group> getGroupList() {
		return groupList;
	}
		
	/**
	 * This method returns the maximum number of groups that
	 * can be located in the front of the classroom. This is
	 * determined by the user.
	 * 
	 * @return The maximum number of front groups is returned. 
	 */
	
	public int getMaximumFrontGroups() {
		return maximumFrontGroups;
	}
	
	/**
	 * The school year is returned.
	 * 
	 * @return The school year is returned.
	 */
	
	public int getSchoolYear() {
		return schoolYear;
	}
	
	/**
	 * The classroom subject is returned.
	 * 
	 * @return The subject is returned. 
	 */
	
	public String getSubject() {
		return subject;
	}
	
	/**
	 * The teacher's name is returned.
	 * 
	 * @return The teacher's name is returned. 
	 */
	
	public String getTeacher() {
		return teacher;
	}
		
	/**
	 * This class removes two students from each other's list of 
	 * incompatible students.
	 * 
	 * @param student1 The first student to be removed.
	 * @param student2 The second student to be removed.
	 */
	
	public void removeIncompatibleStudents(Student student1, Student student2) {
		student1.getStudents().remove(student2);
		student2.getStudents().remove(student1);
	}
	
	/**
	 * This method sets the classroom's grade level.
	 * 
	 * @param grade The grade level to be set.
	 * @throws IllegalArgumentException if invalid grade passed.
	 */
	
	public void setGrade(int grade) {		
		if(grade >= 0 && grade <= 12) {
			this.grade = grade;
		} else
			throw new IllegalArgumentException("Invalid grade.");		
	}
		
	/**
	 * This method accepts a list of groups and sets the data field
	 * groups to equal the list.
	 * 
	 * @param groupList The list of groups.
	 */
	
	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}
	
	/**
	 * This method sets the maximum number of groups that can be
	 * located at the front of the classroom.
	 * 
	 * @param maxFrontGroups The maximum number of groups up front.
	 */
	
	public void setMaximumFrontGroups(int maxFrontGroups) {
		if(maxFrontGroups > 0 && maxFrontGroups < 10)
			maximumFrontGroups = maxFrontGroups;
	}
	
	/**
	 * This method sets the school year for the classroom.
	 * 
	 * @param schoolYear The school year.
	 */
	
	public void setSchoolYear(int schoolYear) {		
		if(schoolYear >= 0 && schoolYear < 3000)
			this.schoolYear = schoolYear;
	}
	
	/**
	 * This method sets the subject for the classroom.
	 * 
	 * @param subject The classroom's subject.
	 */
	
	public void setSubject(String subject) {
		if(subject != null)
			this.subject = subject.trim();
	}
	
	/**
	 * This method sets the teacher for the classroom. The user
	 * can enter the title and name or just the name of the teacher.
	 * 
	 * @param teacher The classroom's teacher.
	 */
	
	public void setTeacher(String teacher) {
		if(teacher != null)
			this.teacher = teacher.trim();
	}	
	
	/**
	 * This method returns a String representation of the classroom.
	 * 
	 * @return The String representation of this classroom is returned. 
	 */
	
	public String toString() {
		
		StringBuilder title = new StringBuilder("");
		
		if(schoolYear > 0) {
			title.append(String.valueOf(schoolYear));
		}
		
		if(teacher != null) {
			title.append(" " + teacher);
		}
		
		if(grade > 0) {			
			String suffix;
			
			if(grade == 1)
				suffix = "st";
			else if(grade == 2)
				suffix = "nd";
			else if(grade == 3)
				suffix = "rd";
			else
				suffix = "th";
			
			title.append(" " + grade + suffix + " Grade");			
		}
		
		if(subject != null) {
			title.append(" " + subject);
		}
		
		if(maximumFrontGroups > 0 && maximumFrontGroups < 10) {
			title.append(", Max Front Groups: " + maximumFrontGroups);			
		}
				
		title.trimToSize();
		
		return title.toString();
	}

}