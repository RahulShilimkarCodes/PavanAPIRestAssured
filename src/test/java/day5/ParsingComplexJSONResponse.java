package day5;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONObject;
import org.json.JSONTokener;

@SuppressWarnings("unused")
public class ParsingComplexJSONResponse {
	
	
	//reading JSON Response file
	
	JSONObject getJSONResponse()
	{
		File myFile = new File(".\\src\\test\\resources\\complex.json");
		
		FileReader fileReader=null;
		try {
			fileReader = new FileReader(myFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		JSONTokener jsonToken = new JSONTokener(fileReader);
		
		JSONObject responseData = new JSONObject(jsonToken);
		
		return responseData;
	}
	
	@Test(priority=1,enabled=true)
	public void userDetailsValidation()
	{
		//parsing json response into json path to locate the response elements..(Extract data from response)
		//json path is same as xpath...
		//Converting JSONObject to JSONPath, to extract response....
		String response = getJSONResponse().toString();
		JsonPath jsonPath = new JsonPath(response);
		
		//1...........status verification---
		
		String status = jsonPath.getString("status");
		assertThat(status, is("success"));
		
		//2.......id,name and email validation
		
		int id = jsonPath.getInt("data.userDetails.id");
		assertThat(id, is(12345));
		
		String name = jsonPath.getString("data.userDetails.name");
		assertThat(name, is("John Doe"));
		
		String email = jsonPath.getString("data.userDetails.email");
		assertThat(email, is("john.doe@example.com"));
		
		
		
		//3............validate phone numbers
		
		String phoneType = jsonPath.getString("data.userDetails.phoneNumbers[0].type");
		assertThat(phoneType, is("home"));
		
		String phoneNumber = jsonPath.getString("data.userDetails.phoneNumbers[0].number");
		assertThat(phoneNumber, is("123-456-7890"));
		
		
		//4.........geo-coordinates validation
		
		double latitude = jsonPath.getDouble("data.userDetails.address.geo.latitude");
		assertThat(latitude, is(39.7817));
		
		double longitude = jsonPath.getDouble("data.userDetails.address.geo.longitude");
		assertThat(longitude, is(-89.6501));
		
		
		
		//5............preference validation,....
		
		boolean notifications = jsonPath.getBoolean("data.userDetails.preferences.notifications");
		assertThat(notifications, is(true));
		
		String theme = jsonPath.getString("data.userDetails.preferences.theme");
		assertThat(theme, is("dark"));
		
		String language = jsonPath.getString("data.userDetails.preferences.languages[1]");
		assertThat(language, is("Spanish"));
		
	}

}
