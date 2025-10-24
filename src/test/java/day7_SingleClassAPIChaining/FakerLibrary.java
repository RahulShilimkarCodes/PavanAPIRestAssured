package day7_SingleClassAPIChaining;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class FakerLibrary {
	
	@Test(description = "Generation of fake test data using Faker library")
	public void fakeDataGeneration()
	{
		Faker faker = new Faker();
		
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String fullName = faker.name().fullName();
		
		String address = faker.address().fullAddress();
		
		String email = faker.internet().emailAddress();
		//String email = faker.internet().safeEmailAddress();
		String password = faker.internet().password(8, 16);			//minimum and maximum length...
		
		String phoneNumber = faker.phoneNumber().cellPhone();
		
		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(fullName);
		System.out.println(address);
		System.out.println(email);
		System.out.println(password);
		System.out.println(phoneNumber);
		
		
		
	}

}
