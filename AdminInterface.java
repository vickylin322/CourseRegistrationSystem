import java.util.ArrayList;
import java.util.List;

public interface AdminInterface {
	//welcome message
	public void welcomeMessage();
	
	//create a new course
	public void createCourse(Course c);
	
	//Delete a course based on Course ID and Section Number
	public void deleteCourse(String cID, int sectionNum);
	
	// Edit course info
	// assume the user will enter the valid type for every info
	public String editCourse(String cID, int sectionNum);
	
	// View Course using Course Id
	public String viewCourse(String cId, int sectionNum);
	
	// Register a student without assigning to the course
	public boolean registerStudent(Student s1);
	
	// Reports
	// View all course
	public void viewAllCourses();
	
	// View all courses that are full
	public List<Course> fullCourse();
	
	// write to a file the list of courses that are full
	public void writeFile(String file);
	
	// View student names
	public String viewStudent(String courseName,int sectionNum);
	
	//View Student courses
	//by first name and last name
	public void viewStudentCourse(String fn, String ln);
	
	// sort course based on current student number
	//result in ascending order, encourage register for less people's classes.
	public ArrayList<Course> Sort(ArrayList<Course> courseList);
	
	
}
