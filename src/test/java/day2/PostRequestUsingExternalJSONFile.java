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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class PostRequestUsingExternalJSONFile {
	
	String studentID;

	//1. Using HashMap..
	
	@Test
	public void createStudentUsingExternalJSONFile() throws FileNotFoundException
	{
		File myFile = new File(".\\src\\test\\java\\day2\\Extrabody.json");
		FileReader fileReader = new FileReader(myFile);
		JSONTokener jsonTokener = new JSONTokener(fileReader); 	//Open the file using tokener....
		
		JSONObject requestDataJSON = new JSONObject(jsonTokener);
		
		
		studentID=given()
			.contentType("application/json")
			.body(requestDataJSON.toString())	//Need to convert JSON Object to String type...
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name", equalTo("KL Rahul"))
			.body("location", equalTo("India"))
			.body(containsString("phone"))
			.body("courses[0]", equalTo("Cricbuzz"))
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
