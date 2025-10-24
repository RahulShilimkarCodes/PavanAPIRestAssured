package day7;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class CreateUser {
	
	public static final String BASE_URL = "https://gorest.co.in/public/v2/users";
	public static final String BEARER_TOKEN = "abcdBearerTokenValue";
	
	int userID;
	
	Faker faker = new Faker();
	
	@Test(priority=1)
	public void createUser(ITestContext context)
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
		
		context.setAttribute("contextUserID", userID);		//will set this to global level, helps accessing in all the classes..
		
	}
	
	

}


