package day7;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class DeleteUser {
	
	public static final String BASE_URL = "https://gorest.co.in/public/v2/users";
	public static final String BEARER_TOKEN = "abcdBearerTokenValue";
	
	
	@Test(dependsOnMethods={"day7.CreateUser.createUser","day7.GetUser.getUser","day7.UpdateUser.updateUser"},priority=4,enabled=true)
	public void deleteUser(ITestContext context)
	{
		given()
			.header("Authorization","Bearer "+BEARER_TOKEN)
			.pathParam("id", (Integer)context.getAttribute("contextUserID"))
		.when()
			.delete(BASE_URL+"/{id}")
		.then()
			.statusCode(204)
			.log().all();
			
	}
	

}


