package sr_code;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class Main {
	private static Map<String, Purchase> purchases = new LinkedHashMap<>();
	private static NavigableMap<String, Student> students = new TreeMap<>();
	
	public static void main(String[] args) {
		Course jmc = new Course("jmc101", "Java Master Class",
                "Java");
        Course python = new Course("pyt101", "Python Master Class",
                "Python");

        addPurchase("Mary Martin", jmc, 129.99);
        addPurchase("Andy Martin", jmc, 139.99);
        addPurchase("Mary Martin", python, 149.99);
        addPurchase("Joe Jones", jmc, 149.99);
        addPurchase("Bill Brown", python, 119.99);

        addPurchase("Chuck Cheese", python, 119.99);
        addPurchase("Davey Jones", jmc, 139.99);
        addPurchase("Eva East", python, 139.99);
        addPurchase("Fred Forker", jmc, 139.99);
        addPurchase("Greg Brady", python, 129.99);
        
        displayHeading("Purchase details: ");
        purchases.forEach((key, value) -> System.out.println(key+" : "+ value));
        System.out.println("-".repeat(90));
        
        displayHeading("Student details: ");
        students.forEach((key, value) -> System.out.println(key+" : "+ value));
        System.out.println("-".repeat(90));
        
	}
	
	private static void addPurchase(String name, Course course, double price) {
		Student existingStudent = students.get(name); // this will make the existingStudent ref variable points to ths student object if available in students map if not available it points to null 
		if(existingStudent == null) {
			// if students map doesn't have the Student obj with that name we can create a new Student obj with the course and adding it to the students map
			existingStudent = new Student(name, course);
			students.put(name, existingStudent);
		}else {
			// if Student obj is there in the students map then we need to add new course to the already existingStudent object 
			existingStudent.addCourse(course);
		}
		
		int day = new Random().nextInt(1,5);
		int year = LocalDate.now().getYear();
		String keyForPuchasesMap = course.courseId()+"_"+existingStudent.getId();
		Purchase purchase = new Purchase(course.courseId(), existingStudent.getId(), price, year, day);
		purchases.put(keyForPuchasesMap, purchase);
	}
	
	
	private static void displayHeading(String heading) {
		System.out.println(heading);
		System.out.println("=".repeat(heading.length()));
	}
}
