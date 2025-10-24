package day4;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.Cookie;
import io.restassured.response.Response;

public class CookiesTesting {
	
	@Test
	public void cookies()
	{
		
		//Getting entire response into a variable...
		
		Response response = 
		given()
		.when()
			.get("https://www.google.com/")
		.then()
			.statusCode(200)
			.log().cookies()
			.cookie("AEC", notNullValue())
			.extract().response();
		
		
		//operation on the response
		
		//getting specific cookies..
		
		String cookie = response.getCookie("AEC");
		
		System.out.println();
		
		System.out.println("Value of AEC cookie is :- " +cookie);
		
		System.out.println();
		
		//extracting all the cookies
		
		
		Map<String,String> allCookies = response.getCookies();
		
		System.out.println("All cookies:- "+allCookies);
		
		
		System.out.println();
		
		for(Map.Entry<String, String> cookieValue : allCookies.entrySet())
		{
			System.out.println(cookieValue.getKey()+" :- "+cookieValue.getValue());
		}
		
		//get detailed info about specific cookie....
		
		Cookie cookie_object = response.getDetailedCookie("AEC");
		
		System.out.println();
		
		System.out.println(cookie_object.hasExpiryDate());
		
		System.out.println(cookie_object.getExpiryDate());
		
		System.out.println(cookie_object.getValue());
		
		System.out.println(cookie_object.hasValue());
		
		System.out.println(cookie_object.isSecured());
			
	}

}
