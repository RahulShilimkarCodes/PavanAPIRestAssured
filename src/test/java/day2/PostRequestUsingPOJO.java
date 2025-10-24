package day2;

/*
 * Ways to create data and use it in post request....
 * 
 * 1. HashMap - used in less number of data/fields...
 * 2. org.json library
 * 3. Using JAVA POJO Class
 * 4. External JSON File
 */

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class PostRequestUsingPOJO {

	String studentID;

	
	@Test
	public void createStudentUsingPOJO()
	{
		POJOClassData requestData = new POJOClassData();
		
		requestData.setName("Hardik");
		requestData.setLocation("France");
		requestData.setPhone("56587865");
		
		String coursesDetails[]= {"chapri","fraud"};
		requestData.setCourses(coursesDetails);
		
		
		studentID=given()
			.contentType("application/json")
			.body(requestData)
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name", equalTo(requestData.getName()))
			.body("location", equalTo(requestData.getLocation()))
			.body(containsString("phone"))
			.body("courses[1]", equalTo(requestData.getCourses()[1]))
			.header("Content-type", "application/json")
			.log().body()
			.extract().jsonPath().getString("id");
		
		
	}
	
	
	@AfterMethod
	public void deleteStudent()
	{
		
		given()
		
		.when()
			.delete("http://localhost:3000/students/"+studentID)
		.then()
			.statusCode(200)
			.log().body();
		
	}
	

}
