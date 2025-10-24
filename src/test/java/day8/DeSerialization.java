package day8;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import groovy.util.logging.Log;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;

public class DeSerialization {
	
	
	@Test(dependsOnMethods="day8.Serialization.SerializationTest",priority=2)
	public void DeSerializationTest(ITestContext context)
	{

		Response response = given()
			.pathParam("userID", (String)context.getAttribute("userID"))
		.when()
			.get("http://localhost:3000/students/"+"{userID}")
		.then()
			.statusCode(200)
			.extract().response();
		
		//1-Way of getting ID:-
//		JsonPath jsonResponse = new JsonPath(response.asString());
//		String id = jsonResponse.getString("id");
		
		String extractedID = response.jsonPath().getString("id");
		
		//DeSerialization -> Conversion of JSON to JAVA Object
		StudentPOJOClass studentResponse = response.as(StudentPOJOClass.class);
		
		assertThat(studentResponse.getName(),is("James Maske"));
		assertThat(studentResponse.getLocation(),is("CA"));
		assertThat(studentResponse.getPhone(),is("2323"));
		assertThat(studentResponse.getCourses()[0],is("Selenium"));
		assertThat(studentResponse.getCourses()[1],is("Java"));
		assertThat(studentResponse.getCourses()[2],is("Python"));
		
		
		System.out.println("Student Data : - "+studentResponse+extractedID);
			
	}
	

}
