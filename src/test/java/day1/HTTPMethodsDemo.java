package day1;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

public class HTTPMethodsDemo {
	
	int userID;
	
	@Test(priority=1,enabled=true)
	void getUser()
	{
		given()
			.header("x-api-key","reqres-free-v1")
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.statusCode(200)
			.body("page", equalTo(2))
			.body("data[5].email",equalTo("rachel.howell@reqres.in"))
			.body(containsString("email"))
			.header("Content-Type", equalTo("application/json; charset=utf-8"))
			.time(lessThan(2000L))
			.log().all();
		
	}
	
	@Test(priority=2)
	void createUser()
	{
	HashMap<String,String> data = new HashMap<String,String>();
	data.put("name", "morpheus");
	data.put("job", "zion resident");
	
	
	userID=given()
			.contentType("application/json")
			.body(data)
			.header("x-api-key","reqres-free-v1")
			.baseUri("https://reqres.in/api/users")
		.when()
			.post()
		.then()
			.statusCode(201)
			.statusLine("HTTP/1.1 201 Created")
			.body("name", equalTo("morpheus"))
			.body(containsString("id"))
			.body("job", equalTo("zion resident"))
			.time(lessThan(3000L))
			.log().all()
			.extract().response().jsonPath().getInt("id");
	
	}
	
	
	@Test(priority=3, dependsOnMethods= {"createUser"})
	void updateUser()
	{
	HashMap<String,String> data = new HashMap<String,String>();
	data.put("name", "rahul");
	data.put("job", "teacher");
	
	
		given()
			.contentType("application/json")
			.body(data)
			.header("x-api-key","reqres-free-v1")
		.when()
			.put("https://reqres.in/api/users/"+ userID)
		.then()
			.statusCode(200)
			.body("name", equalTo("rahul"))
			.body("job", equalTo("teacher"))
			.time(lessThan(3000L))
			.log().all();
	}
	
	@Test(priority=4, dependsOnMethods= {"createUser","updateUser"})
	void deleteUser()
	{	
		given()
			.header("x-api-key","reqres-free-v1")
		.when()
			.delete("https://reqres.in/api/users/"+ userID)
		.then()
			.statusCode(204)
			.body(emptyOrNullString())		//checks if no response body available
			.time(lessThan(3000L))
			.log().all();
	}

}
