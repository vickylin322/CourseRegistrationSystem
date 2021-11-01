import java.util.ArrayList;
import java.util.List;

public class Student extends User implements java.io.Serializable, StudentInterface {

	// define private members
	private String username;
	private String password;
	private String firstName;
	private String lastName;

	public Student() {
	}

	public Student(String un, String pw, String fname, String lname) {
		this.username = un;
		this.password = pw;
		this.firstName = fname;
		this.lastName = lname;
	}

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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	// login
	public boolean login(String username, String pw) {
		for (int i = 0; i < CourseData.userList.size(); i++) {
			Student current = CourseData.userList.get(i);
			if (current.getUsername().equalsIgnoreCase(username) && current.getPassword().equalsIgnoreCase(pw)) {
				return true;
		}
		}
		return false;
	}

//Welcome Student
	// overLoading
	public void welcomeMessage(String username) {
		System.out.println("Welcome to the Course Manage System," + username);
	}

	// View all course
	public void vielAllCourses() {
		for (int i = 0; i < CourseData.courseList.size(); i++) {
			System.out.println(CourseData.courseList.get(i).courseInfo());
			System.out.println("");
		}
	}

	// View all courses that are full
	public List<Course> notFullCourse() {
		List<Course> notFullCourse = new ArrayList<>();
		for (int i = 0; i < CourseData.courseList.size(); i++) {
			Course current = CourseData.courseList.get(i);
			if (current.getCurrentStudents() < current.getMaxStudents()) {
				notFullCourse.add(current);
			}
		}
		return notFullCourse;
	}

	// Register for a course using course name, section number, first name+last name
	// assume the student will only register for a course once
	public void Register(String courseName, int sectionNum, String fn, String ln) {
		String fullname = fn + " " + ln;
		for (int i = 0; i < CourseData.courseList.size(); i++) {
			Course current = CourseData.courseList.get(i);
			if (current.getCourseName().equalsIgnoreCase(courseName) && current.getSectionNumber() == sectionNum) {
				if ((current.getCurrentStudents()) < (current.getMaxStudents())) {
					// System.out.print("check");
					current.getListOfNames().add(fullname);
					current.setListOfNames(current.getListOfNames());
					current.setCurrentStudents(current.getCurrentStudents() + 1);
					System.out.println("You successfully registered for the following class: \n" + current.courseInfo());
				}
			}
		}
		CourseData.Writer("CourseData.ser");
	}

	// Withdraw from the course
	// student will only withdraw from the course they registered
	public void Withdraw(String fn, String ln, String coursename, int sectionNum) {
		String fullname = fn + " " + ln;
		for (int i = 0; i < CourseData.courseList.size(); i++) {
			Course current = CourseData.courseList.get(i);
			if (current.getCourseName().equalsIgnoreCase(coursename) && current.getSectionNumber() == sectionNum) {
				if (current.getCurrentStudents() > 0 && current.getListOfNames().contains(fullname)) {
					current.getListOfNames().remove(fullname);
					// current.getListOfNames();
					current.setListOfNames(current.getListOfNames());
					current.setCurrentStudents(current.getCurrentStudents() - 1);
					System.out.println("You suceefully withdraw from the following class: " + current.getCourseName());
				} else {
					System.out.println("Error!");
				}
			}
		}
		CourseData.Writer("CourseData.ser");
		//System.out.println("System error, there's no matching information");
	}

	// view student's courses based on first name and list name
	public void studentCourse(String fn, String ln) {
		List<String> studentCourse = new ArrayList<>();
		for (int i = 0; i < CourseData.courseList.size(); i++) {
			Course current = CourseData.courseList.get(i);
			if (current.getListOfNames().contains(fn + " " + ln)) {
				studentCourse.add(current.getCourseName());
			}
		}
		System.out.println(fn + " " + ln + " currently enroll in the following courses: ");
		for (int i = 0; i < studentCourse.size(); i++) {
			System.out.println(studentCourse.get(i));
		}
	}

	public String userInfo() {
		return ("User Name: " + username + "\nPassWord: " + password + "\nFirst Name: " + firstName + "\nLast Name: "
				+ lastName);
	}

	public int findIndex() {
		int index = 0;
		for (int i = 0; i < CourseData.userList.size(); i++) {
			Student current = CourseData.userList.get(i);
			if (current.getFirstName().equals(username)) {
				index = i;
			}
		}
		return index;
	}
}
