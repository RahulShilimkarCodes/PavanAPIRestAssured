package day8_RequestAndResponseSpecification;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class RequestAndResponseSpecification {
	
	RequestSpecification httpRequest;
	ResponseSpecification httpResponse;
	
	@BeforeClass	//In below we add things which are shared across all request's strictly...
	public void setup()
	{
		RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
		requestBuilder.setBaseUri("http://localhost:3000");
		requestBuilder.setBasePath("/employees");
		
		httpRequest=requestBuilder.build();
		
		
		ResponseSpecBuilder responseBuilder = new ResponseSpecBuilder();
		responseBuilder.expectStatusCode(200);
		responseBuilder.expectHeader("Content-Type",equalTo("application/json"));
		responseBuilder.expectContentType("application/json");
		
		
		httpResponse=responseBuilder.build();
		
	}
	
	@Test(priority=1)
	public void getEmployees()
	{
		given()
			.spec(httpRequest)
		.when()
			.get()
		.then()
			.spec(httpResponse)
			.body("size()",greaterThan(0));
	}
	
	@Test(priority=2)
	public void getMaleEmployees()
	{
		given()
			.spec(httpRequest)
			.queryParam("gender", "Male")
		.when()
			.get()
		.then()
			.spec(httpResponse)
			.body("size()",greaterThan(0))
			.body("gender", everyItem(equalTo("Male")));
	}
	
	@Test(priority=3)
	public void getFemaleEmployees()
	{
		given()
			.spec(httpRequest)
			.queryParam("gender", "Female")
		.when()
			.get()
		.then()
			.spec(httpResponse)
			.body("size()",greaterThan(0))
			.body("gender", everyItem(equalTo("Female")));
	}
	
	
	
	
	/*
	@Test(priority=1)
	public void getEmployees()
	{
		given()
		.when()
			.get("http://localhost:3000/employees")
		.then()
			.statusCode(200)
			.body("size()",greaterThan(0));
	}
	
	@Test(priority=2)
	public void getMaleEmployees()
	{
		given()
			.queryParam("gender", "Male")
		.when()
			.get("http://localhost:3000/employees")
		.then()
			.statusCode(200)
			.body("size()",greaterThan(0))
			.body("gender", everyItem(equalTo("Male")));
	}
	
	@Test(priority=3)
	public void getFemaleEmployees()
	{
		given()
			.queryParam("gender", "Female")
		.when()
			.get("http://localhost:3000/employees")
		.then()
			.statusCode(200)
			.body("size()",greaterThan(0))
			.body("gender", everyItem(equalTo("Female")));
	}
	
	*/

}
