import java.util.ArrayList;

public interface studentInterface {

	//No fields necessary for Student Interface
	
	
	//Getter for the Student's individual Course List
	public ArrayList<Course> getstudentCourses();
	
	
	//Overrides the toString method from User/Object
	public String toString();
	
	
	//Adds a new course to the Student Course List
	public void registerCourse(Course newCourse);
	
	
	//Removes a course from the Student Course List
	public void withdrawCourse(Course course);
	
	
	//Searches for a specific course in the Course List
	public int searchCourse(ArrayList<Course> currentCourses, String courseName);
	
}


