package day5;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;

import static org.hamcrest.MatcherAssert.*;

public class ParsingResponseFromActualAPI2 {

	@Test
	public void storesAPITest()
	{
		ResponseBody response = given()
				.when()
					.get("http://localhost:3000/store")
				.then()
					.statusCode(200)
					.extract().response().body();
		
		JsonPath jsonResponse = new JsonPath(response.asString());
		
		int jsonSize = jsonResponse.getInt("book.size()");
		System.out.println(jsonSize);
		
		//Atleast one book to be in store
		assertThat(jsonSize, greaterThan(0));
		
		
		//Capturing titles of the books..
		
		for(int i = 0 ; i < jsonSize ; i++)
		{
			String title = jsonResponse.getString("book["+i+"].title");
			System.out.println(title);
		}
		
		// Verifying specific book

		boolean status = false;
		for (int i = 0; i < jsonSize; i++) {
			String title = jsonResponse.getString("book[" + i + "].title");
			
			if(title.equals("Moby Dick"))
			{
				status=true;
				break;
			}
			
		}
		
		assertThat(status, is(true));
		
		//total price of all the books
		double totalPrice = 0;
		for (int i = 0; i < jsonSize; i++) {
			double price = jsonResponse.getDouble("book[" + i + "].price");
			totalPrice = totalPrice + price;
	}
		
		System.out.println("Total price - "+totalPrice);
	
	}
}


