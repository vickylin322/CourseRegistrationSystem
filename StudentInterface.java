import java.util.List;

public interface StudentInterface {
	//login
	public boolean login(String username, String pw);
	
	//Welcome Student
	//overLoading
	public void welcomeMessage(String username);
	
	// View all courses that are full
	public List<Course> notFullCourse();
	
	//Register for a course using course name, section number, first name+last name
	//Only the info matches the current student's info in the userList
	public void Register(String courseName,int sectionNum, String fn, String ln);
	
	//Withdraw from the course
	//Only the info matches the current student's info in the userList
	public void Withdraw(String fn,String ln, String coursename, int sectionNum);
	
	//view student's courses based on first name and list name
	public void studentCourse(String fn, String ln );
	
	//Similar to a toString method, print out the information in the customized format
	public String userInfo();
	
	//Find the usr index in the userList
	public int findIndex();

}
