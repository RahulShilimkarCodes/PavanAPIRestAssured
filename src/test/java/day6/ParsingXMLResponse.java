package day6;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

import static org.hamcrest.Matchers.*;

import java.util.List;

import static org.hamcrest.MatcherAssert.*;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParsingXMLResponse {
	
	@Test(priority=1,enabled=false)
	public void parsingXMLResponse()
	{
		given()
		
		.when()
			.get("https://mocktarget.apigee.net/xml")
		.then()
			.statusCode(200)
			.contentType("application/xml")
			.header("Content-type", "application/xml; charset=utf-8")
			.body("root.city", equalTo("San Jose"))
			.body("root.state", equalTo("CA"))
			.log().body();
	}
	
	
	@Test(description="verifying the attribute with @ tag",priority=2,enabled=false)
	public void parsingXMLResponsePart2()
	{
		given()
		.when()
			.get("https://httpbin.org/xml")
		.then()
			.statusCode(200)
			.contentType("application/xml")
			.body("slideshow.@title", equalTo("Sample Slide Show"))
			.body("slideshow.@date", equalTo("Date of publication"))
			.body("slideshow.@author", equalTo("Yours Truly"))
			.body("slideshow.slide[0].@type", equalTo("all"))
			.body("slideshow.slide[0].title", equalTo("Wake up to WonderWidgets!"))
			.body("slideshow.slide[1].@type", equalTo("all"))
			.body("slideshow.slide[1].title", equalTo("Overview"))
			.log().body();
	}
	
	@Test(description="parsing XML Response",priority=3,enabled=true)
	public void parsingXMLResponsePart3()
	{
		Response response = given()
		.when()
			.get("https://httpbin.org/xml")
		.then()
			.statusCode(200)
			.contentType("application/xml")
			.extract().response();
		
		XmlPath xmlPath = new XmlPath(response.asString());
		
		String title = xmlPath.getString("slideshow.@title");
		assertThat(title,is("Sample Slide Show"));
		
		String date = xmlPath.getString("slideshow.@date");
		assertThat(date,is("Date of publication"));
		
		String author = xmlPath.getString("slideshow.@author");
		assertThat(author,is("Yours Truly"));
		
		//Number of slides in the response...
		List<String> slideTitles = xmlPath.getList("slideshow.slide.title");
		
		//counting number of slides..
		assertThat(slideTitles.size(),is(2));
		
		//validate slide titles
		assertThat(slideTitles.get(0),is("Wake up to WonderWidgets!"));
		assertThat(slideTitles.get(1),is("Overview"));
		
		//checking all at ones
		assertThat(slideTitles,hasItems("Wake up to WonderWidgets!","Overview"));
		
		//Verifying Itemss...
		
		List<String> items = xmlPath.getList("slideshow.slide[1].item");
		
		assertThat(items.size(),is(3));
		
		assertThat(items.get(0),is("WonderWidgets"));
		assertThat(items.get(2),is("buys"));
		
		assertThat(items,hasItems("WonderWidgets","buys"));
		
		boolean itemPresent = false;
		//checking presence of item in the response
		for(int i = 0 ; i < items.size(); i++)
		{
			String itemName = items.get(i);
			if(itemName.equalsIgnoreCase("buys"))
			{
				itemPresent=true;
				break;
			}
		}
		
		assertThat(itemPresent,is(true));
		
	}
	
	

}
