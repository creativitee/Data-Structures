import java.util.ArrayList;

public class Instructor extends User{

	//Constructors
	public Instructor() {
		super();
	}
	
	
	//Note: No need for username/password since instructors are not given control
	public Instructor(String firstname, String lastname) {
		super(firstname, lastname);
	}
	
	
	//Prints out all the courses in the Course List
	public void showCourses(ArrayList<Course> currentCourses) {
		courseManager.showCourses(currentCourses);
	}
}
