package day7;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class GetUser {
	
	public static final String BASE_URL = "https://gorest.co.in/public/v2/users";
	public static final String BEARER_TOKEN = "abcdBearerTokenValue";
	

	@Test(dependsOnMethods="day7.CreateUser.createUser",priority=2,enabled=true)
	public void getUser(ITestContext context)
	{
		given()
			.header("Authorization", "Bearer "+BEARER_TOKEN)
			.pathParam("id", (Integer)context.getAttribute("contextUserID"))
		.when()
			.get(BASE_URL+"/{id}")
		.then()
			.statusCode(200)
			.log().body();
	}
	
	
}


