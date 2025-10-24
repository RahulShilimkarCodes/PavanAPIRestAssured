package day7_SingleClassAPIChaining;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class JSONSchemaValidator {
	
	@Test
	public void schemaValidation()
	{
		given()
		.when()
			.get("https://mocktarget.apigee.net/json")
		.then()
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));
		
	}

}
