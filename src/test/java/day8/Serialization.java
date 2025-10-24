package day8;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import groovy.util.logging.Log;

import static org.hamcrest.MatcherAssert.assertThat;

public class Serialization {
	
	String id;
	
	@Test(priority=1)
	public void SerializationTest(ITestContext context)
	{
		String[] courses = {"Selenium","Java","Python"};
		StudentPOJOClass sp = new StudentPOJOClass("James Maske","CA","2323",courses);
		id=given()
			.contentType("application/json")
			.body(sp)		//Serialization - JAVA Object to JSON
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.log().body()
			.extract().response().jsonPath().getString("id");
		
		
		context.setAttribute("userID", id);
	}
	
	@Test(dependsOnMethods={"SerializationTest","day8.DeSerialization.DeSerializationTest"},priority=3,enabled=true)
	public void deleteUser()
	{
		given()
			.contentType("application/json")
			.pathParam("id",id)
		.when()
			.delete("http://localhost:3000/students/"+"{id}")
		.then()
			.statusCode(200)
			.log().body();
		
	}

}
