package apiRestAssuredDemo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.Assert.*;
import org.testng.Assert;



//Add place -> Update place with new address ->Get place to validate if new address is present in the response
public class Basics 
{
		public static void main(String[] args)
	{
		//given - All input details
		//when 	-Submit the API - Https methods - GET,PUT,POST,DELETE
		//then 	- Validate the response;
		
		String placeID;
		String response;
		String getPlaceResponse;
		String actualAddress;
			
		//Add place
		RestAssured.baseURI="https://rahulshettyacademy.com";
		response=given().queryParam("key", "qaclick123").header("content-type","application/json").body(ReusableMethods.addPlace())
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope",equalTo("APP")).header("server", "Apache/2.4.18 (Ubuntu)")
		.extract().response().asString();
		
		//Extract place id -unique id			
		JsonPath js=new JsonPath(response); //for parsing Json
		placeID=js.getString("place_id");
		
		//Update place
		String newAddress= "70 summer walk, USA";
		given().queryParam("key", "qaclick123").header("content-type","application/json").body("{\r\n" + 
				"\"place_id\":\""+placeID+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + "}")
		.when().put("/maps/api/place/update/json")
		.then().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));
	
		//Get place
		getPlaceResponse=given().queryParam("key", "qaclick123").queryParam("place_id", placeID)
		.when().get("/maps/api/place/get/json")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js1=new JsonPath(getPlaceResponse); //for parsing Json
		actualAddress= js1.getString("address");
		Assert.assertEquals(actualAddress, newAddress);
		
		
		
	}
	

}
