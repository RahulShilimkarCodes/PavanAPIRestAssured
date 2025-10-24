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

public class PostRequestUsingHashMap {
	
	String studentID;

	//1. Using HashMap..
	
	@Test
	public void createStudentUsingHashMap()
	{
		HashMap<String,Object> requestData = new HashMap<String,Object>();
		//String,Object is because we have Array of string as well in it, hence to simplify the key-value, we gave value as object..
		
		requestData.put("name", "virat");
		requestData.put("location", "england");
		requestData.put("phone", "000025566");
		
		String[] courseData = {"C","C++"};
		
		requestData.put("courses", courseData);
		
		studentID=given()
			.contentType("application/json")
			.body(requestData)
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name", equalTo("virat"))
			.body("location", equalTo("england"))
			.body(containsString("phone"))
			.body("courses[0]", equalTo("C"))
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
