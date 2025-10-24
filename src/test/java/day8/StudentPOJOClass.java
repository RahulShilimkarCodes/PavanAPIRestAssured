package day8;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)		//to ignore additional properties in JSON response..
public class StudentPOJOClass {
	
	//should have variables, constructors, getter/setter and toString() methods..
	
	String name,location,phone;
	String[] courses;
	
	public StudentPOJOClass() {}		//default constructor...
	
	public StudentPOJOClass(String name,String location,String phone, String[] courses)
	{
		this.name = name;
		this.location = location;
		this.phone = phone;
		this.courses = courses;	
	}
	
	
	public String getName()
	{
		return name;
	}
	public String getLocation()
	{
		return location;
	}
	public String getPhone()
	{
		return phone;
	}
	public String[] getCourses()
	{
		return courses;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
	public void setLocation(String location)
	{
		this.location=location;
	}
	public void setPhone(String phone)
	{
		this.phone=phone;
	}
	public void setCourses(String[] courses)
	{
		this.courses=courses;
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(name).append(" ").append(location).append(" ").append(phone).append(" ");
		
		if(courses!=null && courses.length>0)
		{
			for(String course:courses) {
				sb.append(course).append(" ");
			}
		}
		
		return sb.toString();
	}

}
