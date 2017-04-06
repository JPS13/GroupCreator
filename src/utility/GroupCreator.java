package utility;

import java.util.*;
import model.*;

public class GroupCreator {
	
	public static List<Group> createGroups(Classroom c) {		
		List<Group> groups = new ArrayList<>();
		
		boolean allStudentsAssigned = false;	// All students placed in groups
		int frontCount; // Number of groups with students needing to sit up front
				
		do {
			// Create a random, working copy of studentList
			List<Student> students = copyList(c.getStudentList());
			Collections.shuffle(students);
			
			frontCount = 0; 
			
			groups = createGroups(students);
			
			if(students.size() == 0)
				allStudentsAssigned = true;
			
			for(Group g: groups) {
				if(g.getIsFrontGroup())
					frontCount++;
			}
			
		} while(!allStudentsAssigned || frontCount > c.getMaximumFrontGroups());
				
		return groups; 				
	}
	
	private static List<Student> copyList(List<Student> studentList) {		
		List<Student> newStudentList = new ArrayList<>();
		
		for(Student student: studentList) {
			newStudentList.add(student);
		}						
		return newStudentList;		
	}
		
	private static List<Group> createGroups(List<Student> students) {
		
		int numberOfGroups = students.size() / 4;
		int remainder = students.size() % 4;
		
		List<Group> groups = new ArrayList<>(numberOfGroups);		
		List<Student> remainingStudents = new ArrayList<>(2);
		
		int groupNumber = 1;  
				
		if(remainder == 1 || remainder == 2) {	
			
			// Find student(s) preferred to be in group of 5
			for(Student s: students) {
				if(s.getPreferredGroupOfFive()) {
					remainingStudents.add(s);
					if(remainingStudents.size() == remainder)
						break;
				}				
			}	
			
			// Find an average student to be in group of 5
			if(remainingStudents.size() < remainder) {
				for(Student s: students) {
					if(s.getAbilityLevel().equals(AbilityLevel.AVERAGE)) {						
						remainingStudents.add(s);
						if(remainingStudents.size() == remainder) 
							break;
					}
				}
			}			
			
			// Remove the students set aside for remainders
			for(Student s: remainingStudents) {
				students.remove(s);
			}				
		}		
		
		else if(remainder == 3) {			
			Group group = getGroup3(students);
			group.setGroupNumber(groupNumber); 
			groups.add(group);
		}	
			 
		// Groups of four or five students
		for(int i = 1; i <= numberOfGroups; i++) { 		
			if(remainder == 3)
				groupNumber = i + 1;
			else 
				groupNumber = i;
			
			Group group = getGroup(students, remainingStudents);
			group.setGroupNumber(groupNumber);
			groups.add(group);
		}	
		
		// Identify front groups
		for(Group g : groups) {
			for(Student s : g.getStudentList()) {				
				if(s.getFrontSeatNeeded()) {					
					g.setIsFrontGroup(true);
					break;
				}
			}			
		}		
		return groups;		
	}
		
	private static Group getGroup3(List<Student> students) {
		
		List<Student> group = new ArrayList<>(4); // Current Group
		List<Student> incompatibles = new ArrayList<>(); // All incompatible students	
		
		int index = 0;
		int females = 0; 
		boolean found = false;
		
		while(!found && index < students.size()) {
		
			Student s = students.get(index);
			
			if(s.getAbilityLevel().equals(AbilityLevel.HIGH)) {
				
				group.add(s);
				
				for(Student student: s.getStudentList()) {
					incompatibles.add(student);
				}
				
				if(s.getGender().equals(Gender.FEMALE))
					females++;
				
				students.remove(s);
				found = true;
			}
			
			else 
				index++;
		}
		
		// Reset values
		index = 0;
		found = false;
		
		while(!found && index < students.size()) {
			
			boolean compatible = true;
			
			Student s = students.get(index);
			
			if((s.getAbilityLevel().equals(AbilityLevel.AVERAGE)) && 
					(females == 0 || !s.getGender().equals(Gender.FEMALE))) {
				
				if(!incompatibles.isEmpty()) {					
					// Compare student with all students in list
					for(Student student: incompatibles) {
						if(s.equals(student)) {
							compatible = false;
							break;
						}
					}							
				}
				
				if(!compatible) {
					index++;
				}
				
				else { // Compatible, average, and doesn't violate female rule
					
					group.add(s);
					
					for(Student student: s.getStudentList()) {
						incompatibles.add(student);
					}
					
					if(s.getGender().equals(Gender.FEMALE))
						females++;
					
					students.remove(s);
					found = true;					
				}				
			}
			
			else {
				index++;
			}				
		}
		
		// Reset values
		index = 0;
		found = false;
		
		while(!found && index < students.size()) {
			
			boolean compatible = true;
			
			Student s = students.get(index);
			
			if((s.getAbilityLevel().equals(AbilityLevel.LOW)) && 
					(females == 0 || !s.getGender().equals(Gender.FEMALE))) {
				
				if(!incompatibles.isEmpty()) {					
					// Compare student with all students in list
					for(Student student: incompatibles) {
						if(s.equals(student)) {
							compatible = false;
							break;
						}
					}							
				}
				
				if(!compatible) {
					index++;
				}
				
				else { // Compatible, low, and doesn't violate female rule
					// don't need to add incompatible students or increment females
					group.add(s);					
					students.remove(s);
					found = true;					
				}				
			}			
			else {
				index++;
			}			
		}			
		return new Group(group);		
	}
	
	private static Group getGroup(List<Student> students, List<Student> remainingStudents) {
		
		List<Student> group = new ArrayList<>(5); // Current Group
		List<Student> incompatibles = new ArrayList<>(); // All incompatible students
		
		int groupSize = 4;		
		int index = 0;
		int highs = 0;
		int lows = 0;
		int females = 0; 
		int males = 0;
		
		if(remainingStudents.size() > 0) {			
			groupSize++;
			
			Student student = remainingStudents.get(0);
			group.add(student);
			
			if(student.getGender().equals(Gender.FEMALE))
				females++;
			
			else if(student.getGender().equals(Gender.MALE))
				males++;
			
			for(Student incompatibleStudent: student.getStudentList()) {
				incompatibles.add(incompatibleStudent);
			}
			
			remainingStudents.remove(student);			
		}		
		
		while(group.size() < groupSize && index < students.size() && students.size() > 0 
				&& females < 2 && males <= 3) {			
			
			boolean compatible = true;							
				
			Student student = students.get(index);								
			
			if(!incompatibles.isEmpty()) {					
				// Compare student with all students in list
				for(Student s: incompatibles) {
					if(student.equals(s)) {
						compatible = false;
						break;
					}
				}							
			}
			
			if(!compatible) {
				index++;
			}
			
			else {				
				if(student.getAbilityLevel().equals(AbilityLevel.HIGH)) {						
					if(highs < 2) {						
						group.add(student);
						
						// Add new student's incompats to this group's incompats
						for(Student s: student.getStudentList()) {
							incompatibles.add(s);
						}
						
						students.remove(student);
						highs++;
						
						if(student.getGender().equals(Gender.FEMALE))
							females++;
						
						else if(student.getGender().equals(Gender.MALE))
							males++;						
					}
					
					else
						index++;
					
				}
				
				else if(student.getAbilityLevel().equals(AbilityLevel.LOW)) {
					if(lows < 2) {
						group.add(student);
						
						// Add new student's incompats to this group's incompats
						for(Student s: student.getStudentList()) {
							incompatibles.add(s);
						}
						
						students.remove(student);
						lows++;
						
						if(student.getGender().equals(Gender.FEMALE))
							females++;
						
						else if(student.getGender().equals(Gender.MALE))
							males++;
					}
					
					else
						index++;					
				}
				
				else {
					group.add(student);
					
					// Add new student's incompats to this group's incompats
					for(Student s: student.getStudentList()) {
						incompatibles.add(s);
					}
					
					students.remove(student);
					
					if(student.getGender().equals(Gender.FEMALE))
						females++;		
					
					else if(student.getGender().equals(Gender.MALE))
						males++;
				}				
			}				
		}			
		
		return new Group(group);
	}

}
