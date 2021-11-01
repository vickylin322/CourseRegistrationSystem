import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Admin extends User implements java.io.Serializable,AdminInterface {

	// Welcome and login
	// Admin login info

	@Override
	public boolean login(String username, String pw) {
		if (username.equalsIgnoreCase("Admin") && pw.equalsIgnoreCase("Admin001")) {
			return true;
		} else {
			System.out.println("The Username/Password is invalid");
			System.out.println("Access denied!");
			return false;
		}
	}

	public void welcomeMessage() {
		System.out.println("Welcome to the Course Manage System, Admin!");
	}

	// Course Management
	// create a new course
	public void createCourse(Course c) {
		CourseData.courseList.add(c);
		CourseData.Writer("CourseData.ser");
		System.out.println("The following course was added successfully!");
		System.out.println(c.courseInfo());
	}

	// delete course
	public void deleteCourse(String cID, int sectionNum) {
		for (int i = 0; i < CourseData.courseList.size(); i++) {
			Course current = CourseData.courseList.get(i);
			if (current.getCourseID().equalsIgnoreCase(cID) && current.getSectionNumber() == sectionNum) {
				CourseData.courseList.remove(current);
			}
			CourseData.Writer("CourseData.ser");
		}
		System.out.println("The following course was deleted successfully");
	}

	// Edit course info
	// assume the user will enter the valid type for every info
	public String editCourse(String cID, int sectionNum) {
		Scanner scan = new Scanner(System.in);
		System.out.println("What info do you want to edit?Please select from (1-6)\n1.Maximum Students"
				+ "\n2.Current Students\n3.List of Names\n4.Course Instructor\n5.Course Section Number\n6.Course Location");

		int option = scan.nextInt();
		scan.nextLine();
		String message = "";
		if (option < 1 || option > 6) {
			message = "Option invalid!";
		} else {
			for (int i = 0; i < CourseData.courseList.size(); i++) {
				Course current = CourseData.courseList.get(i);
				if (current.getCourseID().equalsIgnoreCase(cID) && (current.getSectionNumber())==sectionNum) {
					// maxStudent
					if (option == 1) {
						System.out.println("Please enter the new maximum students number (Integer)");
						int newMaxStudent = scan.nextInt();
						scan.nextLine();
						current.setMaxStudents(newMaxStudent);
					}
					// currentStudent
					//input for current student is always less than max student
					else if (option == 2) {
						System.out.println("Please enter the new current students number (Integer)");
						int newCurrStudent = scan.nextInt();
						scan.nextLine();
						if(newCurrStudent <= current.getMaxStudents()) {
						current.setCurrentStudents(newCurrStudent);
						}
						else {
							System.out.println("The current student number can not be larger than max student number");
						}
					}
					// List of Names
					//list of name will not exceed the size of max student
					//Assume all the names are unique,not add twice and Admin will only delete names that were in the List
					else if (option == 3) {
						System.out.println("Please select(1-2): 1.Add a name 2.Delete a name");
						int choice = scan.nextInt();
						scan.nextLine();
						if (choice == 1) {
							System.out.println("Please enter the new name");
							String name = scan.nextLine();
							List<String> currentlist = current.getListOfNames();
							if (current.getCurrentStudents()<current.getMaxStudents()) {
							currentlist.add(name);
							current.setCurrentStudents(current.getCurrentStudents()+1);
							}
							else {
								System.out.println("Error!There's no space!");
							}
						} else if (choice == 2) {
							System.out.println("Please enter the name to delete: ");
							String name = scan.nextLine();
							List<String> currentlist = current.getListOfNames();
							if (current.getListOfNames().size()!=0) {
							currentlist.remove(name);
							current.setCurrentStudents(current.getCurrentStudents() - 1);
							}
							else {
								System.out.println("Error! There's no one in the class list");
							}
						}
					}
					// Instructor
					else if (option == 4) {
						System.out.println("Please enter the new Instructor name (String)");
						String newInstructor = scan.nextLine();
						current.setCourseInstructor(newInstructor);
					}

					else if (option == 5) {
						System.out.println("Please enter the new section number (Integer)");
						int newsectionNum = scan.nextInt();
						scan.nextLine();
						current.setSectionNumber(newsectionNum);
					} else if (option == 6) {
						System.out.println("Please enter the new location (String)");
						String newLocation = scan.nextLine();
						current.setCourseLocation(newLocation);
					}
					CourseData.Writer("CourseData.ser");

				}
				message = "The course editing was successful!";
			}

		}
		return message;
	}

	// View Course using Course Id
	public String viewCourse(String cId, int sectionNum) {
		String courseInfo = "";
		for (int i = 0; i < CourseData.courseList.size(); i++) {
			Course current = CourseData.courseList.get(i);
			if (current.getCourseID().equalsIgnoreCase(cId) && (current.getSectionNumber())==sectionNum) {
				courseInfo = current.courseInfo();
			}
		}
		return courseInfo;
	}

	// Register a student without assigning to the course
	public boolean registerStudent(Student s1) {
		CourseData.userInfo(s1.getUsername(), s1.getPassword(), s1.getFirstName(), s1.getLastName());
		// System.out.println(s1.getUsername());
		return true;
	}

	// Reports
	// View all course
	public void viewAllCourses() {
		for (int i = 0; i < CourseData.courseList.size(); i++) {
			System.out.println(CourseData.courseList.get(i).courseInfo());
			System.out.println("");
		}
	}

	// View all courses that are full
	public List<Course> fullCourse() {
		List<Course> fullCourse = new ArrayList<>();
		for (int i = 0; i < CourseData.courseList.size(); i++) {
			Course current = CourseData.courseList.get(i);
			if (current.getCurrentStudents() == current.getMaxStudents()) {
				fullCourse.add(current);
			}
		}
		return fullCourse;
	}

	// write to a file the list of courses that are full
	public void writeFile(String file) {
		try {
			FileWriter writer = new FileWriter(file);
			BufferedWriter bwr = new BufferedWriter(writer);
			for (int i = 0; i < CourseData.courseList.size(); i++) {
				Course current = CourseData.courseList.get(i);
				if (current.getCurrentStudents() == current.getMaxStudents()) {
					String course = ("Course name: " + current.getCourseName() + "Course ID: " + current.getCourseID()
							+ "Course Maximum Num: " + current.getMaxStudents() + "Course Current Num: "
							+ current.getCurrentStudents());
					bwr.write(course);
					bwr.newLine();
				}

			}
			bwr.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	// View student names
	public String viewStudent(String courseName,int sectionNum) {
		List<String> name = new ArrayList<>();
		for (int i = 0; i < CourseData.courseList.size(); i++) {
			Course current = CourseData.courseList.get(i);
			if (courseName.equalsIgnoreCase(current.getCourseName())&& (current.getSectionNumber())==sectionNum) {
				name = current.getListOfNames();
				System.out.println(name);
			}

		}
		return ("The students enrolled this class:\n " + name.toString());
	}
//View Student courses
	public void viewStudentCourse(String fn, String ln) {
		List<String> studentCourse = new ArrayList<>();
		for (int i = 0; i < CourseData.courseList.size(); i++) {
			Course current = CourseData.courseList.get(i);
			if (current.getListOfNames().contains(fn + " " + ln)) {
				studentCourse.add(current.getCourseName());
			}
		}
		System.out.println(fn + " " + ln + "currently enroll in the following courses: ");
		for (int i = 0; i < studentCourse.size(); i++) {
			System.out.println(studentCourse.get(i));
		}
	}
	// sort course
	//App
	public ArrayList<Course> Sort(ArrayList<Course> courseList) {
		for (int i = 0; i < courseList.size() - 1; i++) {
			for (int j = 1; j < courseList.size(); j++) {
				if (courseList.get(i).getCurrentStudents() > courseList.get(j).getCurrentStudents()) {
					Course swap = courseList.get(j);
					CourseData.courseList.set(j, courseList.get(i));
					CourseData.courseList.set(i, swap);
				}
			}
		}
		return courseList;
	}

}
