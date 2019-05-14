//Import Serializable so subclasses are Serializable

import java.io.Serializable;
import java.util.ArrayList;


//Implement the Serializable Class
public abstract class User implements Serializable{ 
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	//Fields for the User Object
	private String firstname, lastname;
	private String username, password;


	//Default Constructor
	public User() {
		this("default","default");
	}
	

	//Constructors for User Object
	public User(String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	
	public User(String firstname, String lastname, String username, String password) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
	}
	
	
	
	
	//Getters and Setters for Fields
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	
	public abstract void showCourses(ArrayList<Course> currentCourses);
	                                
	//Overrides Object toString() method
	public String toString() {
		return this.firstname + " " + this.lastname;
	}
	

}
