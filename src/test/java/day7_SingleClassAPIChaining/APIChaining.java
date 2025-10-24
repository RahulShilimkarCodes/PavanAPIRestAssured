package day7_SingleClassAPIChaining;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class APIChaining {
	
	public static final String BASE_URL = "https://gorest.co.in/public/v2/users";
	public static final String BEARER_TOKEN = "abcdBearerTokenValue";
	
	int userID;
	
	Faker faker = new Faker();
	
	HashMap<String,String> userdata = new HashMap<>();
	
	@Test(priority=1)
	public void createUser()
	{
		
		JSONObject requestData = new JSONObject();
		requestData.put("name", faker.name().fullName());
		requestData.put("gender", "Male");
		requestData.put("email", faker.internet().emailAddress());
		requestData.put("status", "inactive");
		
		userID=given()
			.header("Authorization", "Bearer "+BEARER_TOKEN)
			.contentType("application/json")
			.body(requestData.toString())

		.when()
			.post(BASE_URL)
		.then()
			.statusCode(201)
			.extract().response().jsonPath().getInt("id");
		
	}
	
	@Test(dependsOnMethods="createUser",priority=2,enabled=true)
	public void getUser()
	{
		given()
			.header("Authorization", "Bearer "+BEARER_TOKEN)
			.pathParam("id", userID)
		.when()
			.get(BASE_URL+"/{id}")
		.then()
			.statusCode(200)
			.log().body();
	}
	
	@Test(dependsOnMethods={"createUser","getUser"},priority=3,enabled=true)
	public void updateUser()
	{
		
		JSONObject requestData = new JSONObject();
		requestData.put("name", faker.name().fullName());
		requestData.put("gender", "Male");
		requestData.put("email", faker.internet().emailAddress());
		requestData.put("status", "active");
		
		given()
			.header("Authorization", "Bearer "+BEARER_TOKEN)
			.contentType("application/json")
			.body(requestData.toString())
			.pathParam("id", userID)
		.when()
			.put(BASE_URL+"/{id}")
		.then()
			.statusCode(203)
			.log().body();
	}
	
	
	@Test(dependsOnMethods={"createUser","getUser","updateUser"},priority=4,enabled=true)
	public void deleteUser()
	{
		given()
			.header("Authorization","Bearer "+BEARER_TOKEN)
			.pathParam("id", userID)
		.when()
			.delete(BASE_URL+"/{id}")
		.then()
			.statusCode(204)
			.log().all();
			
	}
	

}


