import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CourseData implements java.io.Serializable {
	// this is the class to read course data from the CSV and store the course data
	// in an arrayList courseList
	public static ArrayList<Course> courseList = new ArrayList<>();
	public static ArrayList<Student>userList = new ArrayList<Student>(); 


// Ser Writer(courseList)
	public static void Writer(String file) {
		try {
			// FileOutput Stream writes data to a file
			FileOutputStream fos = new FileOutputStream(file);

			// ObjectOutputStream writes objects to a stream (A sequence of data)
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			// Writes the specific object to the OOS
			oos.writeObject(courseList);

			// Close both streams
			oos.close();
			fos.close();
			System.out.println("Serialization complete");
		} catch (IOException ioe) {
			//ioe.printStackTrace();
		}
	}

// Reader for course objects
	public static void Reader(String file) {
		try {
			// FileInputSystem receives bytes from a file
			FileInputStream fis = new FileInputStream(file);

			// ObjectInputStream does the deserialization-- it reconstructs the data into an
			// object
			ObjectInputStream ois = new ObjectInputStream(fis);

			// Cast as courseList. readObject will take the object from ObjectInputStream
			courseList = (ArrayList<Course>) ois.readObject();
			ois.close();
			fis.close();
		} catch (IOException ioe) {
			//ioe.printStackTrace();
			//return;
		} catch (ClassNotFoundException cnfe) {
			//cnfe.printStackTrace();
			//return;
		}
	}
	// ReaderUser, for user/student objects
		@SuppressWarnings("unchecked")
		public static void UserReader(String file) {
			try {
				// FileInputSystem receives bytes from a file
				FileInputStream fis = new FileInputStream(file);

				// ObjectInputStream does the deserialization-- it reconstructs the data into an
				// object
				ObjectInputStream ois = new ObjectInputStream(fis);

				// Cast as courseList. readObject will take the object from ObjectInputStream
				userList = (ArrayList<Student>) ois.readObject();
				//System.out.println(userList);
				ois.close();
				fis.close();
			} catch (IOException ioe) {
			//CourseDataioe.printStackTrace();
				return;
			} catch (ClassNotFoundException cnfe) {
				//cnfe.printStackTrace();
				return;
			}
		}
		//read csv from course data
		public static void readCSV(String fileName) {
			  fileName = "src/MyUniversityCourses.csv";
			  String line = null;
			  try {
			   FileReader fileReader = new FileReader(fileName);
			   BufferedReader bufferedReader = new BufferedReader(fileReader);
			   //added
			   bufferedReader.readLine();
			   while ((line = bufferedReader.readLine()) != null) {
				 //initialized the course object  
			     Course course = new Course();
			     //Start parsing
			     String[] row = line.split(",");
			     course.setCourseName(row[0]);
			     course.setCourseID(row[1]);
			     course.setMaxStudents(Integer.parseInt(row[2]));
			     course.setCurrentStudents(Integer.parseInt(row[3]));
			     List<String> nameList = new ArrayList<>();
			     // the position of the next element depends on the size of listOfName 
			     //That can be get from the current student size
			     for (int i = 4; i < Integer.parseInt(row[3]+4); i++) {
			      nameList.add(row[i]);
			     }
			     course.setListOfNames(nameList);
			     int size = nameList.size();
			     // 
			     if (size == 0) {
			      course.setCourseInstructor(row[5]);
			      course.setSectionNumber(Integer.parseInt(row[6]));
			      course.setCourseLocation(row[7]);
			     } else {
			      course.setCourseInstructor(row[size + 4]);
			      course.setSectionNumber(Integer.parseInt(row[size + 5]));
			      course.setCourseLocation(row[size + 6]);
			     }
			     //System.out.println(course);
			     courseList.add(course);

			   }
			  }
			  catch (FileNotFoundException ex) {
			   System.out.println("Unable to open file '" + fileName + "'");
			   // the printStackTrace method will print out an error output stream ("What went
			   // wrong?" report);

			   ex.printStackTrace();
			  }

			  catch (IOException ex) {
			   System.out.println("Error reading file '" + fileName + "'");
			   ex.printStackTrace();
			  }

			 }

	
	//First read the ser file(after changed)
	//Then read from the CSV
	 static {
		File Course = new File("CourseData.ser");
		if (!Course.exists()) {
			readCSV("MyUniversityCourses.csv");
		}else {
			Reader("CourseData.ser");
		}
	}

	// if the user is in the list,store info to it's existed row
	// if not write new row for the user
	public static void userInfo(String userName, String pw, String fn, String ln) {
		boolean existing = false;
		try {
			FileInputStream fis = new FileInputStream("userList.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			userList = (ArrayList<Student>) ois.readObject();
			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException ioe) {
			// ioe.printStackTrace();
			/*
			 * for (int i = 0; i < CourseData.courseList.size(); i++) { Course current =
			 * CourseData.courseList.get(i); if (current.getListOfNames().contains(fn + "" +
			 * ln)) { System.out.println("Welcome to the System, " + userName); break; } }
			 */
		}
		//System.out.println(userList);
		if ((userList.size() == 0)) {
			Student newUser = new Student(userName, pw, fn, ln);
			System.out.println("Welcome New User!");
			userList.add(newUser);
			//System.out.println(userList.size());
			System.out.println(userInfo(newUser));
		}

		else {
			
			for (int i = 0; i < userList.size(); i++) {
				//old user
				if (userList.get(i).getUsername().equals(userName)) {
					System.out.println("You are already registered," + userName);
					existing =  true;
				}
				//new users
			}
			if(!existing==true) {
			//existing = false;
			System.out.println("Welcome New User!");
			Student newUser = new Student(userName, pw, fn, ln);
			userList.add(newUser);
			System.out.println(newUser.userInfo());
		   //System.out.println(newUser.toString());
			}	
			}


		try {
			// FileOutput Stream writes data to a file
			FileOutputStream fos = new FileOutputStream("userList.ser");

			// ObjectOutputStream writes objects to a stream (A sequence of data)
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			// Writes the specific object to the OOS
			oos.writeObject(userList);

			// Close both streams
			oos.close();
			fos.close();

			System.out.println("Serialization complete!");

		} 
		catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}
	public static String userInfo(Student s) {
		return( "User Name: "+s.getUsername()+"\nPassWord: "+s.getPassword()+"\nFirst Name: "+s.getFirstName()+"\nLast Name: "+s.getLastName());
	}

}
