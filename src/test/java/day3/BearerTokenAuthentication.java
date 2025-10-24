package day3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class BearerTokenAuthentication {
	
	@Test
	public void bearerTokenAuth()
	{
		String bearerToken = "hgp_abc_xxxRandon Token";
		
		given()
			.header("Authorization","Bearer "+bearerToken)
		.when()
			.get("github repo")
		.then()
			.statusCode(200)
			.log().body();
	}
	

}
