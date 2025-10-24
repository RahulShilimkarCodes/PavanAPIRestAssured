package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class APIKeyAuthentication {
	
	@Test
	public void apiKeyAuthenttication()
	{
		given()
			.queryParam("q", "Delhi")
			.queryParam("appid", "bd3bb4911b880ff9ea1aa4e2ac9c6054")		//authentication
		.when()
			.get("https://api.openweathermap.org/data/2.5/weather")
		.then()
			.statusCode(200)
			.log().body();
		
	}

}
