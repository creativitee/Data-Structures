//ArrayList and Serializable are imported
import java.util.ArrayList;
import java.io.Serializable;


//Implements Serializable to serialize the Courses
//Implements Comparable to sort the courses
public class Course implements Serializable,Comparable<Course>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Fields for the Course class
	private User instructor = new Instructor();
	private String coursename, id, location;
	private int max, current, section;
	private ArrayList<Student> registered = new ArrayList<Student>();


	//Default Constructor
	public Course() {
		this("default", "default", 0, 0,null,null, 0,"default");
	}

	//Constructor 
	public Course(String coursename, String id, int max, int current, 
			ArrayList<Student> registered, User instructor, int section, String location) {
		this.coursename = coursename;
		this.current = current;
		this.id = id;
		this.max = max;
		this.registered = registered;
		this.instructor = instructor;
		this.section = section;
		this.location = location;
	}


	//Getters and Setters
	public User getInstructor() {
		return instructor;
	}
	public void setInstructor(User instructor) {
		this.instructor = instructor;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getCurrent() {
		return current;
	}
	public void setCurrent(int current) {
		this.current = current;
	}
	public int getSection() {
		return section;
	}
	public void setSection(int section) {
		this.section = section;
	}
	public ArrayList<Student> getRegistered() {
		return registered;
	}
	public void setRegistered(ArrayList<Student> registered) {
		this.registered = registered;
	}



	//Returns true if the course is full, false otherwise
	public boolean isFull() {
		return (this.max == this.current);
	}



	//Overrides the toString() method from the Object class
	public String toString() {
		return  (this.coursename + ", " + this.id + ", " + this.max + ", "
				+ this.current + ", " + this.registered + ", " 
				+ this.instructor.toString() + ", "
				+ this.section + ", " + this.location);
	}


	//Encapsulates the registered students in a course so student's don't have access to this information
	public String studentString() {
		return  (this.coursename + ", " + this.id + ", " + this.max + ", "
				+ this.current + ", "
				+ this.instructor.toString() + ", "
				+ this.section + ", " + this.location);
	}


	//IMPORTANT: Overrides the compareTo method in comparable by comparing the current students
	public int compareTo(Course other) {
		return Integer.compare(this.current, other.current);
	}
	


	//Parses through the Registered Students List and prints the registered Students
	public void printRegistered() {
		String result = "";
		for (int i = 0; i < this.getRegistered().size(); i++) {
			result += this.getRegistered().get(i).toString() + ", ";
		}
		System.out.println(result);
	}

	

	//Parses through the Registered Students List to return the index of a specific Student
	//Returns -1 if the student has not been found
	public int searchStudent(String firstname, String lastname) {
		ArrayList<Student> find = this.getRegistered();
		for (int i = 0; i < find.size(); i++) {
			if ((find.get(i).getFirstname().equals(firstname)) 
					&& (find.get(i).getLastname().equals(lastname))) 
				return i;
		}
		return -1;	
	}
}
