package apiRestAssuredDemo;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class StaticJson
{
	
	@Test
	public void addBook() throws IOException
	{
		String resp;
		String idVal;
		
		RestAssured.baseURI ="http://216.10.245.166";
		resp=given().header("content-type","application/json").body(GenerateStringFromResource("C:\\Users\\HP\\Desktop\\AddBook.json"))
		.when().post("Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js=new JsonPath(resp);	
		idVal=js.get("ID");
		System.out.println(idVal);
		
	}
	
	public static String GenerateStringFromResource(String path)throws IOException
	{
		return new String(Files.readAllBytes(Paths.get(path)));
		
	}



}
