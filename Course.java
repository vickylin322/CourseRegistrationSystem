import java.util.List;

public class Course implements java.io.Serializable {
	// Define private variables corresponded MyUniversityCourse
	
	private String courseName;
	private String courseID;
	private int maxStudents;
	private int currentStudents;
	private List<String> listOfNames;
	private String courseInstructor;
	private int sectionNumber;
	private String courseLocation;

	// Constructors
	public Course() {
	}

	public Course(String cName, String cID, int maxStudent, int currentStudent, List<String> nameList,
			String cInstructor, int sectionNum, String cLocation) {
		courseName = cName;
		courseID = cID;
		maxStudents = maxStudent;
		currentStudents = currentStudent;
		listOfNames = nameList;
		courseInstructor = cInstructor;
		sectionNumber = sectionNum;
		courseLocation = cLocation;
	}

	// Getters and Setters

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public int getMaxStudents() {
		return maxStudents;
	}

	public void setMaxStudents(int maxStudents) {
		this.maxStudents = maxStudents;
	}

	public int getCurrentStudents() {
		return currentStudents;
	}

	public void setCurrentStudents(int currentStudents) {
		this.currentStudents = currentStudents;
	}

	public List<String> getListOfNames() {
		return listOfNames;
	}

	public void setListOfNames(List<String> listOfNames) {
		this.listOfNames = listOfNames;
	}

	public String getCourseInstructor() {
		return courseInstructor;
	}

	public void setCourseInstructor(String courseInstructor) {
		this.courseInstructor = courseInstructor;
	}

	public int getSectionNumber() {
		return sectionNumber;
	}

	public void setSectionNumber(int sectionNumber) {
		this.sectionNumber = sectionNumber;
	}

	public String getCourseLocation() {
		return courseLocation;
	}

	public void setCourseLocation(String courseLocation) {
		this.courseLocation = courseLocation;
	}

	// Method
	// courseInfo
	public String courseInfo() {
		return "Course Info:\nCourse Name: " + courseName +"\nCourse ID: "+courseID+ "\nMaximum Students: " + maxStudents
				+ "\nCurrent Students: " + currentStudents + "\nList of Name: " + listOfNames + "\nCourse Instructor: "
				+ courseInstructor + "\nCourse Section Number: " + sectionNumber + "\nCourse Location: "
				+ courseLocation;

	}



}
