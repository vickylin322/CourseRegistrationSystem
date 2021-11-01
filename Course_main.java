import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Course_main implements java.io.Serializable {
	
	public static void main(String[] args) {
		//This Program is Case Sensitive!
	

		// take input from the user(Admin or Student)
		Admin admin = new Admin();
		Student student = new Student();
		CourseData coursedata = new CourseData();
		Scanner input = new Scanner(System.in);

		System.out.println("Hi, are you a Student or Admin? ");
		String position = input.nextLine();

		// if the user choose Admin
		if (position.equals("Admin")) {
			//welcome
			admin.welcomeMessage();
			// check login info
			System.out.println("Please enter your username: ");
			String username = input.nextLine();

			System.out.println("Please enter your password: ");
			String pw = input.nextLine();
			//username:Admin password:Admin001
			if (admin.login(username, pw)) {
				while (true) {
					System.out.println(
							"Please choose from the following task: enter 1 for Courses Management, 2 for Reports");
					int option = input.nextInt();
					input.nextLine();

					if (option == 1) {
						System.out.println("1.Create a new course\n2.Delete a course\n3.Edit a course\n"
								+ "4.Display information for a given course\n5.Register a student\n6.Exit");
						int task = input.nextInt();
						input.nextLine();

						if (task == 1) {
							// Task 1: Create a new course
							//assume the logical current student number will be enter(not larger than max number)
							System.out.println("Create a New Course");
							// input
							System.out.println("Enter the Course Name: ");
							String courseName = input.nextLine();

							System.out.println("Enter the Course Id: ");
							String cID = input.nextLine();

							System.out.println("Enter the Course Max Students Number: ");
							int maxStudent = input.nextInt();
							input.nextLine();

							System.out.println("Enter the Course Current Students Number: ");
							int currentStudent = input.nextInt();
							input.nextLine();

							List<String> listOfName = new ArrayList<String>();
							if (currentStudent > 0) {
								for (int i = 0; i < currentStudent; i++) {
									System.out.println("Enter the first name of students: ");
									// input.nextLine();
									String fn = input.nextLine();

									System.out.println("Enter the last name of students: ");
									// input.nextLine();
									String ln = input.nextLine();
									listOfName.add(fn + " " + ln);
									// input.nextLine();
								}
							}

							System.out.println("Enter the Course Instructor: ");
							String courseInstructor = input.nextLine();

							System.out.println("Enter the Course Section Number: ");
							int sectionNum = input.nextInt();

							System.out.println("Enter the Course Location");
							String location = input.next();
							Course c = new Course(courseName, cID, maxStudent, currentStudent, listOfName,
									courseInstructor, sectionNum, location);

							admin.createCourse(c);
							for (int i = 0; i < CourseData.courseList.size(); i++) {
								System.out.println(CourseData.courseList.get(i).courseInfo());
								System.out.println("");
							}
						}

						else if (task == 2) {
							// Task 2 Delete A Course
							//Assume Admin will only delete courses that in the system
							System.out.println("Delete a Course");
							// input
							System.out.println("Enter the Course ID: ");
							String cID = input.nextLine();
							
							System.out.println("Enter the Course Section Number: ");
							int sectionNum = input.nextInt();
							admin.deleteCourse(cID, sectionNum);
							for (int i = 0; i < CourseData.courseList.size(); i++) {
								System.out.println(CourseData.courseList.get(i).courseInfo());
								System.out.println("");
							}

						} else if (task == 3) {
							// Task 3 Edit a Course
							//Assume all the names are unique,not add twice and Admin will only delete names that were in the List
							System.out.println("Edit a Course");
							// input
							System.out.println("Enter the Course ID: ");
							String cID = input.nextLine();
							
							System.out.println("Enter the Course Section Number: ");
							int sectionNum = input.nextInt();
							// method
							admin.editCourse(cID,sectionNum);
							for (int i = 0; i < CourseData.courseList.size(); i++) {
								System.out.println(CourseData.courseList.get(i).courseInfo());
								System.out.println("");
							}
						} else if (task == 4) {
							System.out.println("Display information for a given course");
							// input
							System.out.println("Enter the Course ID: ");
							String cID = input.nextLine();
							System.out.println("Enter the Course Section Number: ");
							int sectionNum = input.nextInt();
							System.out.println(admin.viewCourse(cID,sectionNum));
							System.out.println("");
						}
						// Register a Student
						// Notify if already in the system
						else if (task == 5) {
							// register a student
							System.out.println("Register a Student");
							// input
							System.out.println("Enter the Student's User Name: ");
							String userName = input.nextLine();

							System.out.println("Enter the Student's Password: ");
							String password = input.nextLine();

							System.out.println("Enter the Student's First Name: ");
							String fname = input.nextLine();

							System.out.println("Enter the Student's Last Name: ");
							String lname = input.nextLine();

							Student s = new Student(userName, password, fname, lname);
							// System.out.println(s.getUsername());
							admin.registerStudent(s);
							System.out.println("");
						}

						// Exit
						else if (task == 6) {
							System.out.println("Thank you! System closed!");
							break;
						}
					}
					// Reports
					else if (option == 2) {
						System.out.println(
								"1.View all courses\n2.View all courses that are Full\n3.Write to a file the list of course that are full\n"
										+ "4.View the names of the students being registered in a specific course \n5.View the list of courses that a given student is being registered on"
										+ "\n6.Sort(Ascending)\n7.Exit");
						int task = input.nextInt();
						input.nextLine();

						// View all course
						if (task == 1) {
							admin.viewAllCourses();
						}
						// View courses that are full
						else if (task == 2) {
							List<Course> fullCourse = admin.fullCourse();
							for (int i = 0; i < fullCourse.size(); i++) {
								System.out.println(fullCourse.get(i).courseInfo());
								System.out.println("");
							}
						}

						// Write to the file with the full courses info
						else if (task == 3) {
							admin.writeFile("fullCourseList.txt");
							System.out.println("Writing Completed!");
						}

						// View the names of the students being registered in a specific course
						else if (task == 4) {
							System.out.println("Enter the Course Name: ");
							String courseName = input.nextLine();
							System.out.println("Enter the Course Section Number: ");
							int sectionNum = input.nextInt();
							admin.viewStudent(courseName,sectionNum);
							System.out.println("");
						
						// View the courses of the students registered 
						//Return nothing if the student not in any classes
						} else if (task == 5) {
							System.out.println("Enter the first name of students: ");
							String fn = input.nextLine();

							System.out.println("Enter the last name of students: ");
							String ln = input.nextLine();

							admin.viewStudentCourse(fn, ln);
							System.out.println("");
							
						// Sort the courses in ascending order
						//Encourage to register for course with less student		
						} else if (task == 6) {
							List<Course> sortedCourse = CourseData.courseList;
							Collections.sort(sortedCourse,
									(course1, course2) -> course1.getCurrentStudents() - course2.getCurrentStudents());
							for (int i = 0; i < sortedCourse.size(); i++) {
								System.out.println(sortedCourse.get(i).courseInfo());
								System.out.println("");
							}
						}
						// Exit
						else if (task == 7) {
							System.out.println("Thank you! System closed!");
							break;
						}

					}
				}
			}
		} else if (position.equals("Student")) {
			//read from userList.ser file for the login info
			CourseData.UserReader("userList.ser");
			
			//Login in info
			System.out.println("Please enter your username: ");
			String username = input.nextLine();
			System.out.println("Please enter your password: ");
			String pw = input.nextLine();
			//Find the index to much to following info(only current student can do modification)
			int index = 0;
			for (int i = 0; i < CourseData.userList.size(); i++) {
				Student current = CourseData.userList.get(i);
				//System.out.println(current.userInfo());
				if (current.getUsername().equalsIgnoreCase((username))) {
					index = i;
				}
			}
			//Login in check
			if (student.login(username, pw)) {
				student.welcomeMessage(username);
				while (true) {
					System.out.println(
							"Please select:\n1.View all courses\n2.View all courses that are not FULL\n3.Register on a course\n4.Withdraw from a course"
									+ "\n5.View all courses that the current student is being registered in\n6.Exit");
					int option = input.nextInt();
					input.nextLine();

					// View all course
					if (option == 1) {
						student.vielAllCourses();
					}
					// View all courses that are not FULL
					else if (option == 2) {
						List<Course> NotfullCourse = student.notFullCourse();
						for (int i = 0; i < NotfullCourse.size(); i++) {
							System.out.println(NotfullCourse.get(i).courseInfo());
							System.out.println("");
						}
					}
					// Register in a course
					// Student can only register for themselves and their full name must match the name admin registered
					// e.g. in userList: full name is Vicky Lin under the username, when login Student with that username
					//System only can register for Vicky Lin
					else if (option == 3) {
						System.out.println("Enter the Course Name: ");
						String courseName = input.nextLine();
						System.out.println("Enter the Course Section Number: ");
						int sectionNum = input.nextInt();
						input.nextLine();
						System.out.println("Enter the first name: ");
						String fn = input.nextLine();

						System.out.println("Enter the last name: ");
						String ln = input.nextLine();

						if (CourseData.userList.get(index).getFirstName().equalsIgnoreCase(fn)
								&& CourseData.userList.get(index).getLastName().equalsIgnoreCase(ln)) {
							student.Register(courseName, sectionNum, fn, ln);
						} else {
							System.out.println("Error! You can only register courses for yourself!");
						}
						
						
					//Withdraw from a course
					// Student can only withdraw for themselves and their full name must match the name admin registered
					} else if (option == 4) {
						System.out.println("Enter the first name: ");
						String fn = input.nextLine();

						System.out.println("Enter the last name: ");
						String ln = input.nextLine();
						System.out.println("Enter the Course Name: ");
						String courseName = input.nextLine();

						System.out.println("Enter the Course Section Number: ");
						int sectionNum = input.nextInt();
						input.nextLine();
						if (CourseData.userList.get(index).getFirstName().equalsIgnoreCase(fn)
								&& CourseData.userList.get(index).getLastName().equalsIgnoreCase(ln)) {
							student.Withdraw(fn, ln, courseName, sectionNum);
						} else {
							System.out.println("Error! You can only withdraw courses for yourself!");
						}
						
					//View Course
					// Student can only view course for themselves and their full name must match the name admin registered
					} else if (option == 5) {
						System.out.println("Enter the first name: ");
						String fn = input.nextLine();

						System.out.println("Enter the last name: ");
						String ln = input.nextLine();

						if (CourseData.userList.get(index).getFirstName().equalsIgnoreCase(fn)
								&& CourseData.userList.get(index).getLastName().equalsIgnoreCase(ln)) {
							student.studentCourse(fn, ln);
						} else {
							System.out.println("Error! You can only view courses for yourself!");
						}
						System.out.println("");
					} else if (option == 6) {
						System.out.println("Thank you! System closed!");
						break;

					}

				}

			}
			//if login info invalid
			else {
				System.out.println("The Username/Password is invalid");
				System.out.println("Access denied!");
			}
			}
		}
	}

