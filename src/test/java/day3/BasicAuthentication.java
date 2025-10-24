package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;


public class BasicAuthentication {
	
	@Test(enabled=false)
	public void basicAuth()
	{
		given()
			.auth().basic("postman", "password")
		.when()
			.get("http://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().body();
	}
	
	@Test(enabled=false)
	public void basicPreEmptiveAuth()     //password will be encrypted
	{
		given()
			.auth().preemptive().basic("postman", "password")
		.when()
			.get("http://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().body();
	}
	
	@Test
	public void digestAuth()     //password will be encrypted
	{
		given()
			.auth().digest("postman", "password")
		.when()
			.get("http://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().body();
	}

}
