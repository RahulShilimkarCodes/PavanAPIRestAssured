package day4;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

public class FileUpload {
	
	@Test(enabled=true)
	public void singleFileUpload()
	{
		
		File myFile = new File("C:\\Users\\silim\\Downloads\\APIsIntro.pdf");
				
		given()
			.multiPart("file",myFile)
			.contentType("multipart/form-data")
		.when()
			.post("https://the-internet.herokuapp.com/upload")
		.then()
			.statusCode(200)
			.log().body();
		
	}
	
	
	@Test(enabled=false)
	public void multipleFileUpload()
	{
		
		File file1 = new File("C:/Users/silim/Downloads/jira.pdf");
		File file2 = new File("C:/Users/silim/Downloads/API Testing Intro.pdf");
				
		given()
			.multiPart("file", "file1")
			.multiPart("file","file2")
			.contentType("multipart/form-data")
		.when()
			.post("http://localhost:8080/uploadMultipleFiles")
		.then()
			.statusCode(200)
//			.body("[0].fileName", equalTo("jira.pdf"))
//			.body("[1].fileName", equalTo("API Testing Intro.pdf"))
			.log().body();
	}
	
	@Test(enabled=true)
	public void downloadSingleFile()
	{
		given()
			.pathParam("fileName", "testfile.txt")
		.when()
			.get("https://the-internet.herokuapp.com/download/{fileName}")
		.then()
			.statusCode(200)
			.log().body();
	}

}
