package sr_code;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

record Course(String courseId, String name, String subject) {}

record Purchase(String courseId, int studentId, double price, int year, int dateOfYear) {
	//here the date of year variable can have value in range between 1-365
	
	public LocalDate purchaseDate() {
		return LocalDate.ofYearDay(year, dateOfYear);
		//here by passing the year int and dateOfYear int as argument to LocalDate.ofYearDay which will intern return a complete date object 
	}
}

public class Student {
	private static int lastId =1;
	
	private String name;
	private int id;
	private List<Course> courseList;
	
	public Student(String name, List<Course> courseList) {
		this.name = name;
		this.courseList = courseList;
		id = lastId++;
	}
	
	public Student(String name, Course courseList) {
		this(name, new ArrayList<>(List.of(courseList)));
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}
	
	public void addCourse(Course course) {
		this.courseList.add(course);
	}

	@Override
	public String toString() {
		String [] courseNames = new String[courseList.size()]; // this will create a string array with the size of courseList list to store all courseNames enrolled by the student
		Arrays.setAll(courseNames, i -> courseList.get(i).name()); // this will set the courses in the courseNames String array according to the courseList 
		return "[%d] : %s".formatted(id, String.join(",", courseNames)); // here join is used to give all the elements of the array separated by the delimiter , taken as arguments
	}
	
	
}
