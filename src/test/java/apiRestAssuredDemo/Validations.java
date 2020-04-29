package apiRestAssuredDemo;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
public class Validations 
{

	int coursesCount;
	int price;
	int copies;
	int amount;
	int sum=0;
	
	@Test
	public void sumOfCourses()
	{
		JsonPath js=new JsonPath(ReusableMethods.CoursePrice());
		coursesCount=js.getInt("courses.size()");
		for(int i=0;i<coursesCount;i++)
		{
			price=js.getInt("courses["+i+"].price");
			copies=js.getInt("courses["+i+"].copies");
			amount= price*copies;
			System.out.println(amount);
			sum=sum+amount;
		}
		System.out.println(sum);
	}
}



