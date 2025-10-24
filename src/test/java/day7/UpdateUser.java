package day7;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class UpdateUser {
	
	
	
	public static final String BASE_URL = "https://gorest.co.in/public/v2/users";
	public static final String BEARER_TOKEN = "abcdBearerTokenValue";
	

	Faker faker = new Faker();
	
	@Test(dependsOnMethods={"day7.CreateUser.createUser","day7.GetUser.getUser"},priority=3,enabled=true)
	public void updateUser(ITestContext context)
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
			.pathParam("id", (Integer)context.getAttribute("contextUserID"))
		.when()
			.put(BASE_URL+"/{id}")
		.then()
			.statusCode(203)
			.log().body();
	}
	

}


