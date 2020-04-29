package apiRestAssuredDemo;

import io.restassured.path.json.JsonPath;

public class ComplefJsonParse 
{
	
	public static void main(String[] args)
	{
		int coursesCount;
		int totalAmount;
		String firstTitle;
		String courseTitles;
		int coursePrice;
		
		JsonPath js=new JsonPath(ReusableMethods.CoursePrice());
		
		//1. Print No of courses returned by API
		coursesCount=js.getInt("courses.size()");
		System.out.println(coursesCount);
		
		//2.Print Purchase Amount
		totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);
		
		//3. Print Title of the first course
		firstTitle = js.getString("courses[0].title");
		System.out.println(firstTitle);
		
		//4. Print All course titles and their respective Prices
		for(int i=0; i<coursesCount; i++)
		{
			System.out.println(js.getString("courses["+i+"].title"));
			//System.out.println(courseTitles);
			System.out.println(js.getInt("courses["+i+"].price"));
			//System.out.println(coursePrice);			
		}
		
		//5. Print no of copies sold by RPA Course
		for(int i=0; i<coursesCount; i++)
		{
			courseTitles=js.getString("courses["+i+"].title");
			if(courseTitles.equalsIgnoreCase("RPA"))
			{
				int copies=js.get("courses["+i+"].copies");
				System.out.println(copies);
				break;
			}		
		}
		
		//6. Verify if Sum of all Course prices matches with Purchase Amount
		
	}
}
