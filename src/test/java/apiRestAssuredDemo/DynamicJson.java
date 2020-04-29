package apiRestAssuredDemo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DynamicJson
{
	
	@Test(dataProvider="BooksData")
	public void addBook(String isbn, String aisle)
	{
		String resp;
		String idVal;
		
		RestAssured.baseURI ="http://216.10.245.166";
		resp=given().header("content-type","application/json").body(ReusableMethods.addBooks(isbn,aisle))
		.when().post("Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js=new JsonPath(resp);	
		idVal=js.get("ID");
		System.out.println(idVal);
		
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getData()
	{
		//Arrays-collection of elements
		//Multi dimensional array- Collection of arrays
		return new Object[][] { {"erewrew","3435"},{"dsfdsf","3454365"},{"hjju","454367"} };	
	}	
	
}
