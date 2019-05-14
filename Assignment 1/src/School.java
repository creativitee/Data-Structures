import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class School {

	//Initializes the Course List
	static ArrayList<Course> currentCourses = new ArrayList<Course>();

	//Initializes the single Admin
	static Admin currentAdmin = new Admin();

	
	//Main Method
	public static void main(String[] args){
		
		//Scanner the reads in inputs
		Scanner input = new Scanner(System.in);

		//Serialized Files
		File c = new File("currentCourses.ser");
		File s = new File("admin.ser");
		
		//Checks if the serialized files exists	
		if(c.exists() && s.exists()) {
			
			//Code to Deserialize
			Deserialize();
		
			//Starts the Program by checking the User inputs
			checkUser(input);
		}
		else{
			
			//Reads the files since it is the first time using the program
			readFile("MyUniversityCourses.csv", currentCourses);
			checkUser(input);
		}


	}

	
	//Checks if the User is an Admin or a Student, then sends it through the necessary phases
	public static void checkUser(Scanner input){
		
		
		//Asks the user
		System.out.println("Please enter Admin if you are an admin, or Student "
				+ "if you are a student.");
		String in = input.nextLine();

		
		//If they are an Admin, does the Admin part
		if (in.equals("Admin"))
			isAdmin(input);
		
		//If they are a Student, does the Student part
		else if (in.equals("Student"))
			isStudent(input);
		
		//If the user inputs an invalid String, goes back to checking the User 
		else {
			System.out.println("*Invalid Input* \r\n");
			checkUser(input);
		}
		
		//Always close Scanners
		input.close();
	}


	
	//Checks the username and password and returns true if they are correct, false if incorrect
	public static boolean checkCredentials(User user, String username, String password) {
		if ( (! username.equals(user.getUsername())) || 
				! (password.equals(user.getPassword()))) {
			System.out.println("*INVALID USERNAME OR PASSWORD* \n");
			return false;
		}
		else 
			return true;
	}
 
	
	
	
	//Admin Section
	public static void isAdmin(Scanner input) {

	
		
		//Requests Admin Information
		System.out.println(
				"Please input your first name and last name. \r\n"
				+ "Format:<firstname> <lastname>");
		String[] names = input.nextLine().split("\\s+");
		currentAdmin.setFirstname(names[0]);
		currentAdmin.setLastname(names[1]);
		System.out.println("Please enter your username.");
		String username = input.nextLine();
		System.out.println("Please enter your password.");
		String password = input.nextLine();

		
		
		//Checks User Credentials, if wrong goes back to checking the User 
		if (!(checkCredentials(currentAdmin, username, password)))
			checkUser(input);
		
		
		//Prints options for the User
		System.out.println("\t1. Course Management \r\n" +
				"\t2. Course Reporting\r\n" +
				"\t3. Exit");
		

		
		//Begins Phase 1 for the Admin
		AdminPhase1(input);
	}


	
	
	
	//Admin Phase 1
	public static void AdminPhase1(Scanner input) {
		
		//User Decision 
		int in = Integer.parseInt(input.nextLine());
	
		
		
		//Switch Statements to give correct Output
		switch (in) {
		
		
		//Sends the User to Course Management
		case 1:
			courseManagement(input);
			break;

		//Sends the User to Course Reporting
		case 2: 
			courseReporting(input);
			break;

		//Serialize and Exit 
		case 3: 
			input.close();
			Serialize();
			System.exit(0);

		default:
			System.out.println("Input Invalid.");
			break;
		}

	}



	
	//Course Management 
	public static void courseManagement(Scanner input) {
		
		
		
		//Gives the User Options
		System.out.println(
				"\t1. Create a new course\r\n" + 
				"\t2. Delete a course\r\n" + 
				"\t3. Edit a course\r\n" + 
				"\t4. Display information for a given course \r\n" + 
				"\t5. Register a student \r\n" +
				"\t6. Exit");
		
		int in = Integer.parseInt(input.nextLine());

	
		//Switch Statements to give Correct Output
		switch (in) {

		
		//Creates a new Course given course information
		case 1: 
			
			//Requests the Course Info
			System.out.println(
					"Input a name, course id, max students, instructor name,"
					+ "section number, and location for the new course. \r\n" + ""
					+ "Format: <name>,<course id>,<max students>,<instructor firstname>"
					+ " <instructor lastname>,<section number>,<location>");
			
			
			String[] courseInfo = input.nextLine().split(",");
			String[] instructorName = courseInfo[3].split("\\s+");
			
			
			currentCourses.add(new Course(courseInfo[0], courseInfo[1], Integer.parseInt(courseInfo[2]),
					0, new ArrayList<Student>(), new Instructor(instructorName[0],instructorName[1]),Integer.parseInt(courseInfo[4]),
					courseInfo[5]));
			System.out.println("Course Added!");
			adminAgain(input);
			break;

			
		//Deletes a given course using course ID
		case 2:
			System.out.println("Input the course name of the couse you would like to delete.");
			String courseName = input.nextLine();
			System.out.println("Please enter the section of the course you would like to delete.");
			int section = Integer.parseInt(input.nextLine());
			courseManager.deleteCourse(currentCourses, courseName, section);
			System.out.println("Course Deleted!");
			adminAgain(input);
			break;
			
			
		//Edits a course using the Course Name and Section
		case 3: 
			System.out.println("Please enter the Course Name of the course you would like to edit.");
			String courseName1 = input.nextLine();
			System.out.println("Please enter the Section for the course you would like to edit.");
			int section1 = Integer.parseInt(input.nextLine());
			courseManager.editCourseID(currentAdmin, input, currentCourses, courseName1, section1);
			adminAgain(input);
			break;
			
			
		//Displays a course using the course ID	
		case 4:
			System.out.println("Please enter the ID of the course you would like to view.");
			String currentCourseID = input.nextLine();
			ArrayList<Integer> indices = courseManager.searchCourse(currentCourses, currentCourseID);
			System.out.println("\nThere are currently " + indices.size() + " courses with the same course id.");
			System.out.println("\nPlease input the corresponding number to the course you would like to display.");
			courseManager.showCourseByIndex(currentCourses, indices);
			int index = indices.get(Integer.parseInt(input.nextLine()) - 1);
			courseManager.displayCourse(currentCourses,index);
			adminAgain(input);
			break;
			
			
		//Registers a new Student and adds them to the Admin's Student List
		case 5: 
			System.out.println("Please input their first name and last name. \r\n"
					+ "Format:<firstname> <lastname>");
			String[] names2 = input.nextLine().split("\\s+");
			System.out.println("Please enter the username for the student.");
			String studentUser = input.nextLine();
			System.out.println("Please enter the password for the student.");
			String studentPass = input.nextLine();
			currentAdmin.registerStudent(new Student(names2[0], names2[1], studentUser
					, studentPass));
			adminAgain(input);
			break;

			
		//Serialize and Exit
		case 6:
			input.close();
			Serialize();
			System.exit(0);
		default: 
			System.out.println("Invalid Input.");
			break;

		}

	}
	
	
	
	//Sends the User back to the Admin Options
	public static void adminAgain(Scanner input) {
		
		System.out.println("\nPlease enter the corresponding number for: \r\n" +
				"\t1. Course Management \r\n" + 
				"\t2. Course Reporting \r\n" +
				"\t3. Exit.");
		int decision = Integer.parseInt(input.nextLine());
		
		if (decision == 1)
			courseManagement(input);
		else if (decision == 2) {
			courseReporting(input);
		}
		
		//Serialize and Exit
		else {
			input.close();
			Serialize();
			System.exit(0);
		}
		input.close();
	}



	//Course Reporting for the Admin
	public static void courseReporting(Scanner input) {
		
		System.out.println("\t1. View All Courses \r\n" + 
				"\t2. View all Full Courses\r\n" + 
				"\t3. Write to a file the list of courses that are full \r\n" + 
				"\t4. View the names of the students that are registered in a specific course \r\n" + 
				"\t5. View the courses that a student is currently registered in \r\n" +
				"\t6. Sort all Courses by Number of Students Registered \r\n" +
				"\t7. Exit");

		int in = Integer.parseInt(input.nextLine());

		
		//Switch to handle Admin Requests
		switch (in) {

		
		//Displays all current courses
		case 1:
			currentAdmin.showCourses(currentCourses);
			adminAgain(input);
			break;

		//Displays the full courses
		case 2:
			courseManager.showFullCourses(currentCourses);
			adminAgain(input);
			break;

			
		//Writes the full courses to a file
		case 3:
			currentAdmin.writeFullCoures(currentCourses);
			adminAgain(input);
			break;
			
			
		//Prints the Registered Students in a course	
		case 4:
			System.out.println("Please enter a the ID of the course.");
			String courseID = input.nextLine();
			System.out.println("Please enter the section number of the course.");
			int section = Integer.parseInt(input.nextLine());
			int index = courseManager.searchCourse(currentCourses, courseID, section);
			currentCourses.get(index).printRegistered();
			adminAgain(input);
			break;

			
		//Displays all the courses that a student is currently registered in
		case 5:
			System.out.println("Please input their first name and last name. \r\n"
					+ "Format:<firstname> <lastname>");
			String[] names3 = input.nextLine().split("\\s+");
			currentAdmin.showStudentCourses(names3[0], names3[1]);
			adminAgain(input);
			break;
			
		
		//Sorts the courses by current number of students
		case 6:
			courseManager.sortCourses(currentCourses);
			adminAgain(input);
			break;

			
		//Serialize and Exit
		case 7:
			input.close();
			Serialize();
			System.exit(0);
			
		default:
			System.out.println("ser");
			break;
		}
		input.close();
	}




	//Student Part 
	public static void isStudent(Scanner input) {

		
		//Requests the name of the Student to check if the student is currently registered
		System.out.println("Please input your first name and last name. \r\n"
				+ "Format:<firstname> <lastname>");
		String[] names = input.nextLine().split("\\s+");
		int i = currentAdmin.searchStudent(names[0], names[1]);
		if (i == -1) {
			System.out.println("Student is not currently registered.");
			checkUser(input);
		}

		//Requests and Checks the Username and Password of the Student
		Student currentStudent = currentAdmin.getallStudents().get(i);
		System.out.println("Please enter your username.");
		String username = input.nextLine();
		System.out.println("Please enter your password.");
		String password = input.nextLine();

		if (!(checkCredentials(currentStudent, username, password)))
			checkUser(input);

		
		//Phase 1 for Student
		StudentPhase1(input, currentStudent);
	}
	
	
	
	//Phase 1 for Student
	public static void StudentPhase1(Scanner input, Student currentStudent) {
		
		
		//Student Options
		System.out.println(
				"\t1. View all courses\r\n" + 
				"\t2. View all courses that are not FULL\r\n" + 
				"\t3. Register for a course \r\n" + 
				"\t4. Withdraw from a course \r\n" + 
				"\t5. View all courses that the current student is being registered in\r\n" + 
				"\t6. Exit");

		int in = Integer.parseInt(input.nextLine());

		//Switch to handle Student choice
		switch(in) {
		
		
		//Displays all the current Courses
		case 1:
			currentStudent.showCourses(currentCourses);	
			studentAgain(input, currentStudent);
			break;

			
		//Displays all the current courses that are not full
		case 2:
			courseManager.showNonFullCourses(currentCourses);
			studentAgain(input, currentStudent);
			break;

			
		//Registers the current student into a course
		case 3:
			System.out.println("Please input a Course Name.");
			String courseName = input.nextLine();
			
			System.out.println("Please input a Section Number");
			int sectionNumber = Integer.parseInt(input.nextLine());

			
			System.out.println("Please input your first and last name for verification.");

			String[] names = input.nextLine().split("\\s+");
			
			
			courseManager.registerStudent(currentCourses, courseName, sectionNumber, currentStudent);
			studentAgain(input, currentStudent);

			break;

			
		//Withdraws the Student from the current course
		case 4:
			
			System.out.println("Please enter your name for verification.");
			
			String[] names2 = input.nextLine().split("\\s+");

			System.out.println("Please input a Course Name.");
			
			String courseName2 = input.nextLine();
			
			System.out.println("Please input the Course Section.");
			
			int sectionNum = Integer.parseInt(input.nextLine());
			courseManager.removeStudent(currentCourses, courseName2, sectionNum, currentStudent);
			studentAgain(input, currentStudent);
			break;

			
		//Displays the courses the Student is currently taking
		case 5:
			System.out.println(
					"Please input your first name and last name. \r\n"
					+ "Format:<firstname> <lastname>");
			String name = input.nextLine();
			String[] names3 = name.split("\\s+");
			currentAdmin.showStudentCourses(names3[0], names3[1]);
			studentAgain(input, currentStudent);
			break;

		//Serialize and Exit
		case 6:
			input.close();
			Serialize();
			System.exit(0);

		default:
			Serialize();
			break;

		}
		
		//Closes the Scanner
		input.close();
	}


	
	
	//Goes back to the Student Option Menu
	public static void studentAgain(Scanner input, Student currentStudent) {
		System.out.println("\nPlease enter the corresponding number to: \r\n "
				+ "\t1. Manage Your Courses again \r\n"
				+ "\t2. Exit.");
		int decision = Integer.parseInt(input.nextLine());
		if (decision == 1)
			StudentPhase1(input, currentStudent);
		else {
			input.close();
			Serialize();
			System.exit(0);
		}
	}

	

	//Reads the MyUniveristyCourses CSV file and adds the courses to the currentCourses list
	//Note: Most of this code is copied from the Serialization example
	public static void readFile(String fileName, ArrayList<Course> currentCourses) {

		try{

			//FileReader
			FileReader fileReader = new FileReader(fileName);

			BufferedReader bufferedReader = new BufferedReader(fileReader);

			
			//SKIPS the first line of the csv file
			bufferedReader.readLine();
			String line  = null;

			
			//Continues to read if the line exists
			while((line = bufferedReader.readLine()) != null) {	
				
				//Splits the csv file by commas
				String[] course = line.split(",");

				
				//Creates a registered students list
				ArrayList<Student> registered = new ArrayList<Student>();


				//Splits the instructor name by space
				String[] instructor = course[5].split("\\s+");
				
				//Initializes the Professor
				User Professor = new Instructor(instructor[0],instructor[instructor.length-1]);

				
				//Creates a new Course and adds the course to the current courses list
				Course current = new Course(course[0],course[1]
						,Integer.parseInt(course[2]), Integer.parseInt(course[3])
						,registered, Professor, Integer.parseInt(course[6]), course[7]);
				courseManager.addCourse(currentCourses, current);

			}


			//Always close files
			bufferedReader.close();
		}

		//The catch block performs a specific action depending on the exception
		catch(FileNotFoundException ex){
			System.out.println( "Unable to open file '" + fileName + "'");
			//the printStackTrace method will print out an error output stream ("What went wrong?" report);

			ex.printStackTrace();
		}

		catch (IOException ex) {
			System.out.println( "Error reading file '" + fileName + "'");
			ex.printStackTrace();
		}

	}

	

	
	//Serializes the Admin and Course List
	//Note: Most of the code was copied from the Serialization example
	public static void Serialize() {
		
		//instantiate the course and admin objects
		ArrayList<Course> courses = currentCourses;
		User admin = currentAdmin;

		try {
			//FileOutput Stream writes data to a file
			FileOutputStream fos = new FileOutputStream("currentCourses.ser");
			FileOutputStream fos2 = new FileOutputStream("admin.ser");

			//ObjectOutputStream writes objects to a stream (A sequence of data)
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			ObjectOutputStream oos2 = new ObjectOutputStream(fos2);

			//Writes the specific object to the OOS
			oos.writeObject(courses);
			oos2.writeObject(admin);

			//Close both streams
			oos.close();
			fos.close();
			fos2.close();
			oos2.close();
			System.out.println("*Exiting Program*");
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}



	}

	
	//Reads in the Serialization Files and deserializes the Course and Admin Objects
	//Note: Most of this code was copied from the Serialziation example
	public static void Deserialize() {

		try{
			
			//Admin and Course List placeholders
			ArrayList<Course> de = null;
			Admin de2 = null;
			
			
			//FileInputSystem recieves bytes from a file
			FileInputStream fis = new FileInputStream("currentCourses.ser");
			FileInputStream fis2 = new FileInputStream("admin.ser");

			//ObjectInputStream does the deserialization-- it reconstructs the data into an object
			ObjectInputStream ois = new ObjectInputStream(fis);
			ObjectInputStream ois2 = new ObjectInputStream(fis2);

			//Cast as ArrayList of Courses and Admin. readObject will take the object from ObjectInputStream
			de = (ArrayList<Course>)ois.readObject();
			de2 = (Admin)ois2.readObject();

			currentCourses = de;
			currentAdmin = de2;
			ois.close();
			fis.close();
			fis2.close();
			ois2.close();
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
			return;
		}
		catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return;
		}
	}
}


