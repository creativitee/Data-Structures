//Imports ArrayList, Collections, and Scanner
//Collectons is imported to sort the Course List
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class courseManager {


	//Goes through the Course List and returns the index of a specific course by checking the courseID's
	public static ArrayList<Integer> searchCourse(ArrayList<Course> currentCourses, String courseID) {
		
		ArrayList<Integer> indices = new ArrayList<Integer>();
		for (int i = 0; i < currentCourses.size(); i++) {
			if (currentCourses.get(i).getId().equals(courseID)) {
				indices.add(i);
			}
		}
		return indices;
	}
	
	

	//Overloads the previous method 
	//Goes through the Course List and returns the index of a specific course by checking the course name and section
	public static int searchCourse(ArrayList<Course> currentCourses, String courseName, int sectionNumber) {
		
		int index = 0;
		for (int i = 0; i < currentCourses.size(); i++) {
			if ((currentCourses.get(i).getCoursename().equals(courseName)) &&
					currentCourses.get(i).getSection() == sectionNumber) {
				index = i;
			}
		}
		return index;
	}

	

	//Adds a course to the Course List
	public static void addCourse(ArrayList<Course> currentCourses, Course newCourse) {
		currentCourses.add(newCourse);
	}

	
	//Deletes a course from the Course List
	public static void deleteCourse(ArrayList<Course> currentCourses, String courseName, int section) {
		currentCourses.remove(currentCourses.get(searchCourse(currentCourses, courseName, section)));
	}
	
	
	
	//Sorts the courses by current number of registered students by using Collections
	public static void sortCourses(ArrayList<Course> currentCourses) {
		Collections.sort(currentCourses);
	}

	
	
	//Registers a student into a course by using the course name and section
	public static void registerStudent(ArrayList<Course> currentCourses, String courseName, 
			int sectionNumber, Student currentStudent) {
		
		int index = searchCourse(currentCourses, courseName, sectionNumber);
		
		if (currentCourses.get(index).isFull())
			System.out.println("The course you are trying to register in is currently full.");
		
		else {
			currentCourses.get(index).getRegistered().add(currentStudent);
			currentStudent.registerCourse(currentCourses.get(index));
			currentCourses.get(index).setCurrent(currentCourses.get(index).getCurrent() + 1);
		}
	}


	//Removes a student from a course by using the course name
	public static void removeStudent(ArrayList<Course> currentCourses, String courseName,
			int section, Student currentStudent) {
		
		int index = searchCourse(currentCourses, courseName, section);
		Course currentCourse = currentCourses.get(index);
		
		if (currentCourse.getCurrent() == 0)
			System.out.println("The course currently has 0 students.");
	
		else {
			
			//Finds the index of the student in the course registered list
			int studentIndex = currentCourse.searchStudent(currentStudent.getFirstname(), 
					currentStudent.getLastname());
			
			//removes said student
			currentCourse.getRegistered().remove(studentIndex);
			
			//removes the course from the current student
			currentStudent.withdrawCourse(currentCourses.get(index));
			
			//the current number of students is decreased by 1
			currentCourse.setCurrent(currentCourse.getCurrent() - 1);
		}
	}

	

	//Displays the current course information using the index, but courseID was used to find the index
	public static void displayCourse(ArrayList<Course> currentCourses, int index) {
		System.out.println("Course Name: " + currentCourses.get(index).getCoursename());
		System.out.println("Course ID: " + currentCourses.get(index).getId());
		System.out.println("Max Students: " + currentCourses.get(index).getMax());
		System.out.println("Current Number of Students: " + currentCourses.get(index).getCurrent());
		System.out.println("Students Registered: " + currentCourses.get(index).getRegistered().toString());
		System.out.println("Instructor Name: " + currentCourses.get(index).getInstructor().toString());
		System.out.println("Course Section: " + currentCourses.get(index).getSection());
		System.out.println("Course Location: " + currentCourses.get(index).getLocation());

	}

	
	//Prints out all the courses in the Course List
	public static void showCourses(ArrayList<Course> currentCourses) {
		if (currentCourses.isEmpty())
			System.out.println("There are no courses available/registered to.");
		else
			for (int i = 0; i < currentCourses.size(); i++) {
				System.out.println((i + 1) + ". " + currentCourses.get(i).toString());
			}
	}

	//Hides the currently registered students in printing the Course List
	public static void studentShowCourses(ArrayList<Course> currentCourses) {
		if (currentCourses.isEmpty())
			System.out.println("There are no courses available/registered to.");
		else
			for (int i = 0; i < currentCourses.size(); i++) {
				System.out.println((i + 1) + ". " + currentCourses.get(i).studentString());
			}
	}
	
	
	//Prints the Full Courses in the Course List
	public static void showFullCourses(ArrayList<Course> currentCourses) {
		for (int i = 0; i < currentCourses.size(); i++) {
			Course current = currentCourses.get(i);
			if (current.getCurrent() == current.getMax())
				System.out.println((i + 1) + ". " + current.toString());
		}
	}
	
	
	//Prints the Non-Full Courses in the Course List
	public static void showNonFullCourses(ArrayList<Course> currentCourses) {
		for (int i = 0; i < currentCourses.size(); i++) {
			Course current = currentCourses.get(i);
			if (current.getCurrent() != current.getMax())
				System.out.println((i + 1) + ". " + current.studentString());
		}
	}
	
	
	//Displays the Course using Indices
	public static void showCourseByIndex(ArrayList<Course> currentCourses, ArrayList<Integer> indices) {
		for (int i = 0; i < indices.size(); i++) {
			Course current = currentCourses.get(indices.get(i));
			System.out.println((i + 1) + ". " + current.studentString());		
			}
	}

	
	//Edits the Course by previously searching for the course using its Course Name and Section
	public static void editCourseID(Admin currentAdmin, Scanner input, ArrayList<Course> currentCourses, 
			String courseName, int section) {
		
		
		//Get the index of the desired course to edit
		int index = searchCourse(currentCourses, courseName, section);
		
		
		//Instantiate a course that references the desired course to edit
		Course editCourse = currentCourses.get(index);
		
		
		//Asks the User what he/she would like to edit
		System.out.println("What edit would you like to make for this course?");
		System.out.println("1. Instructor \r\n" +
				"2. Section Number \r\n" + 
				"3. Max Number of Students \r\n" +
				"4. Location \r\n" +
				"5. Registered Students \r\n" +
				"6. Exit.");

		
		//Switch statement to give the user what it requested
		switch (Integer.parseInt(input.nextLine())) {

		
		//Edit the Instructor
		case 1: 
			System.out.println("Please enter the new "
					+ "instructor's name in the format:<FirstName> <LastName>");
			String[] instructorName = input.nextLine().split("\\s+");
			editCourse.setInstructor(new Instructor(instructorName[0],instructorName[1]));
			break;
			
			
		//Edit the Section Number
		case 2:
			System.out.println("Please enter the new Section Number, making sure "
					+ "there is no conflict in section numbers.");
			editCourse.setSection(Integer.parseInt(input.nextLine()));
			break;
			
			
		//Edit the Maximum Number of Students for a course
		case 3:
			System.out.println("Please enter a new maximum for the number of students "
					+ "in the course.");
			editCourse.setMax(Integer.parseInt(input.nextLine()));
			break;
			
			
		//Edit the Location for the course	
		case 4:
			System.out.println("Please enter a new location for the course.");
			editCourse.setLocation(input.nextLine());
			break;
			
	
		//Removes or adds a student from the Registered Students List
		case 5:
			System.out.println("Please enter 1 if you would like to remove a student "
					+ "and \n2 if you would like to add a student from the course.");
			switch (Integer.parseInt(input.nextLine())) {

			
			//Removes a student
			case 1:
				System.out.println("Please enter the name of a student that you wish to remove. \r\n"
						+ "In the format:<FirstName> <LastName>");
				String[] studentName = input.nextLine().split("\\s+");
				int studentIndex = currentAdmin.searchStudent(studentName[0], studentName[1]);
				Student currentStudent = currentAdmin.getallStudents().get(studentIndex);	
				removeStudent(currentCourses, courseName, editCourse.getSection(), currentStudent);

				break;


				
			//Adds a student
			case 2: 
				System.out.println("Please enter the name of a student that you wish to register. \r\n"
						+ "(In the format:<FirstName> <LastName> <Username> <Password>");
				String[] studentName2 = input.nextLine().split("\\s+");
				Student newStudent = new Student(studentName2[0], studentName2[1], studentName2[2], studentName2[3]);
				editCourse.getRegistered().add(newStudent);

				break;

			default:
				System.out.println("Invalid Input.");
			}
			break;

		default:
			System.out.println("Invalid Input.");

		}

	}
}





