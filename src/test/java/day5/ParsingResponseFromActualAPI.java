package day5;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;

import static org.hamcrest.MatcherAssert.*;

public class ParsingResponseFromActualAPI {
	
	@Test(priority=1)
	public void employeesAPIResponse()
	{
		ResponseBody responseBody = given()
		.when()
			.get("http://localhost:3000/employees")
		.then()
			.statusCode(200)
			.extract().response().body();
		
		JsonPath jsonPath = new JsonPath(responseBody.asString()); 
		
		//finding total number of object(size of json array)
		
		int jsonSize = jsonPath.getInt("size()");
		System.out.println("Size of the array :- "+jsonSize);
		
		
		//printing all the details of employees
		for(int i = 0 ; i < jsonSize; i++)
		{
			String id = jsonPath.getString("["+i+"].id");
			String fname = jsonPath.getString("["+i+"].first_name");
			String lname = jsonPath.getString("["+i+"].last_name");
			String email = jsonPath.getString("["+i+"].email");
			String gender = jsonPath.getString("["+i+"].gender");
			
			System.out.println("Details for employee "+id+" is :- "+fname+" "+lname+" email is "+email+" and gender is "+gender);
					
		}
		
		//verifying details of particular employee
		boolean employeeStatus = false;
		for(int i = 0; i < jsonSize; i++)
		{
			
			String employeeName = jsonPath.getString("["+i+"].first_name");
			if(employeeName.equals("Laura")) {
				employeeStatus=true;
				System.out.println("Employee Laura is present at location "+i);
				break;
			}
		}
		
		assertThat(employeeStatus,is(true));
	}

}
