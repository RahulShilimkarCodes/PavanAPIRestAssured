package day7_SingleClassAPIChaining;

import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class XMLSchemaValidator {
	
	@Test
	public void xmlSchemaValidation()
	{
		given()
		.when()
			.get("https://mocktarget.apigee.net/xml")
		.then()
			.assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("FileName.xsd"));
		
		//Note:- keep xsd file under resources,same as Json..
		
	}

}
