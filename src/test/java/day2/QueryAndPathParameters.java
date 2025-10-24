package day2;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class QueryAndPathParameters {
	
	@Test(enabled=false)
	public void pathParameters()
	{
		given()
			.pathParam("country", "India")
		.when()
			.get("https://restcountries.com/v2/name/{country}")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	
	@Test
	public void queryParameter()
	{
		given()
			.queryParam("page",2)
			.queryParam("id",5)
			.header("x-api-key","reqres-free-v1")
		.when()
			.get("https://reqres.in/api/users")
		.then()
			.statusCode(200)
			.log().body();
	}

}
