package day4;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.Cookie;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeadersTesting {

	
	@Test
	public void headers()
	{
		
		//Getting entire response into a variable...
		
		Response response = 
		given()
		.when()
			.get("https://www.google.com/")
		.then()
			.statusCode(200)
			.log().headers()
			.header("Content-Type", containsString("text/html"))
			.header("Content-Encoding", notNullValue())
			.header("Content-Encoding", equalTo("gzip"))
			.header("X-Frame-Options", equalTo("SAMEORIGIN"))
			.extract().response();
		
		
		String headerDateInfo = response.getHeader("Date");
		
		System.out.println();
		
		System.out.println("Current date value in header is :- "+headerDateInfo);
		
		//Getting all the headers
		
		Headers headersValue = response.getHeaders();
		
		for(Header header : headersValue)
		{
			System.out.println(header.getName() +" ===========-> "+header.getValue());
		}
		
		
}
	
}
