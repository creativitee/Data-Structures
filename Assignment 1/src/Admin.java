//Import writers to write all Full Courses to a file
//Import ArrayList to store students
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


//Implements the Admin Interface,Inherits fields and methods from User
public class Admin extends User implements adminInterface{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	//ArrayList to store students
	private ArrayList<Student> allStudents = new ArrayList<Student>();
	
	
	//Default Constructor
	public Admin() {
		super("default","default","Admin","Admin001");
	}

	
	//Another Constructor
	//Note: Both constructors instantiate the Admin object with the only username and password available
	public Admin(String firstname, String lastname) {
		super(firstname, lastname, "Admin", "Admin001");
	}
	
	
	//Getter for the Student List, inherited all getters and setters from User
	public ArrayList<Student> getallStudents(){
		return allStudents;
	}
	
	
	
	//Adds a student to the Student List
	//Note: students are never removed from the Student List
	public void registerStudent(Student newStudent) {
		allStudents.add(newStudent);
	}
	
	
	
	//Takes an input, currentCourses, and checks which courses are full to write to a file, "FullCourses.txt"
	//Note: I took the code from the serialization examples
	public void writeFullCoures(ArrayList<Course> currentCourses) {
		String fileName = "FullCourses.txt";
		
		try{
			FileWriter fileWriter = new FileWriter(fileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			for (int i = 0; i < currentCourses.size(); i++) {
				Course next = currentCourses.get(i);
				if (next.getCurrent() == next.getMax()) {
				String text = next.toString();
				bufferedWriter.append(text);
				bufferedWriter.newLine();
			}
			}
			

			//Always close writer
			bufferedWriter.close();
		}

		//Always close files

		catch (IOException exk) {
			System.out.println( "Error writing file '" + fileName + "'");
			exk.printStackTrace();
		}	
	}
	
	
	
	//Parses through the Student List to give the index of a specific student
	public int searchStudent(String firstname, String lastname) {

		ArrayList<Student> find = this.getallStudents();
		for (int i = 0; i < find.size(); i++) {
			if ((find.get(i).getFirstname().equals(firstname)) 
					&& (find.get(i).getLastname().equals(lastname))) 
				return i;
		}
		return -1;	
	}
	
	
	//Prints out all the courses with all information in the Course List
	public void showCourses(ArrayList<Course> currentCourses) {
		courseManager.showCourses(currentCourses);
	}

	
	
	
	//Displays the courses that the current student is registered in
	public void showStudentCourses(String firstname, String lastname) {
		int index = searchStudent(firstname, lastname);
		if (index == -1)
			System.out.println("The current student has not been registered.");
		else {
			courseManager.studentShowCourses(this.getallStudents().get(index).getstudentCourses());
		}
			
		
	}
	
	
	
}
