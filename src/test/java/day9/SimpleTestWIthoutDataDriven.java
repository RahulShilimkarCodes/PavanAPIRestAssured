package day9;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

public class SimpleTestWIthoutDataDriven {
	
	private static final String BEARER_TOKEN = "Bearer b429a1baae4d0d013f6fc1bd7c81f34ee2e4ab26c1d4307da8e775b3b3bc8343";
	private static final String BASE_URL = "https://simple-books-api.glitch.me/orders";
	
	String orderId ; 
	@Test
	public void orderSubmission()
	{
		
		HashMap<String,Object> orderData = new HashMap<String,Object>();
		orderData.put("bookId", 3);
		orderData.put("customerName", "John Wohn");
		
		 orderId = given()
			.contentType("application/json")
			.header("Authorization",BEARER_TOKEN)
			.body(orderData)
		.when()
			.post(BASE_URL)
		.then()
			.statusCode(201)
			.log().body()
			.extract().response().jsonPath().getString("id");
	}
	
	@Test(dependsOnMethods="orderSubmission",priority=2)
	public void deletingOrder()
	{
		given()
			.header("Authorization",BEARER_TOKEN)
			.pathParam("orderID", orderId)
		.when()
			.delete(BASE_URL+"/{orderID}")
		.then()
			.statusCode(203)
			.log().all();
	}
	

}
