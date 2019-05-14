//Import ArrayList to store the courses for each Student
import java.util.ArrayList;

//Implements the Student Interface, Inherits fields and methods from User
public class Student extends User implements studentInterface{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Inherits the fields from the User class and has it's own field, studentCourses, to store courses
	private ArrayList<Course> studentCourses = new ArrayList<Course>();
	
	
	//Default Constructor, uses superclass constructor
	public Student(){
		super("Student","Student001");
	}
	
	
	//Constructors for Student
	
	//This constructor takes a first name and last name
	public Student(String firstname, String lastname) {
		super(firstname, lastname);
	}
	
	//This constructor takes first name, last name, username, and password
	public Student(String firstname, String lastname, String username, String password) {
		super(firstname, lastname, username, password);
		
	}
	
	
	
	//Getter for the Student Courses List, inherited getters and setters from User
	public ArrayList<Course> getstudentCourses(){
		return studentCourses;
	}
	
	
	//Overrides the User toString method
	public String toString() {
		return super.toString();
	}
	
	//Hides the currently registered students in printing the Course List
	public void showCourses(ArrayList<Course> currentCourses) {
		courseManager.studentShowCourses(currentCourses);
	}
	
	//Adds a course to the student's individual course list
	public void registerCourse(Course newCourse) {
		studentCourses.add(newCourse);
	}
	
	
	//Removes a course from the student's individual course list
	public void withdrawCourse(Course course) {
		studentCourses.remove(course);
	}
	
	
	//Takes two inputs:currentCourses and courseName
	//to find a course in currentCourses using its course name and returns its index
	public int searchCourse(ArrayList<Course> currentCourses, String courseName) {
		int index = 0;
		for (int i = 0; i < currentCourses.size(); i++) {
			if (currentCourses.get(i).getCoursename().equals(courseName))
				index = i;
		}
		return index;
	}
	
	
}
