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

import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class PostRequestUsingJSONLibrary {
	
	String studentID;
	
	@Test
	public void createStudentUsingJSONLibrary()
	{
		JSONObject requestDataJSON = new JSONObject();
		
		requestDataJSON.put("name", "KL Rahul");
		requestDataJSON.put("location", "India");
		requestDataJSON.put("phone", "000025532");
		
		String[] courseData = {"JAVA","CRICKET"};
		
		requestDataJSON.put("courses", courseData);
		
		studentID=given()
			.contentType("application/json")
			.body(requestDataJSON.toString())	//Need to convert JSON Object to String type...De-Serialization
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name", equalTo("KL Rahul"))
			.body("location", equalTo("India"))
			.body(containsString("phone"))
			.body("courses[1]", equalTo("CRICKET"))
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
