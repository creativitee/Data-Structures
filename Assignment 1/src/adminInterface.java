import java.util.ArrayList;


//Admin Interface
public interface adminInterface {
	
	//No fields necessary since they are not static/final
	//Note: Username/Password could have been implemented here
	
	
	
	//Getter for the Student List
	public ArrayList<Student> getallStudents();
	
	
	
	//Registers student to the Student List
	public void registerStudent(Student newStudent);
	
	
	
	//Writes a file containing the FullCourses
	public void writeFullCoures(ArrayList<Course> currentCourses);
	
	
	
	//Searches for a specific student in the Student List
	public int searchStudent(String firstname, String lastname);
	
	
	
	//Prints the courses that a specific student is taking
	public void showStudentCourses(String firstname, String lastname);
		
}


