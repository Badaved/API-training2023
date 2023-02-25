package RestAssured;



import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.*;
import io.restassured.response.Response;

public class Createtoken_Version1 {
	
	
	//Given- all input details(URI,Parameter,path parameter,query parameter,payload,hearderType)
	//When- submitting Api,headertype(which type of request post, get and set endpoint)
	//Then- Validate the response

	@Test
	public void Booking_Version1() {
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		Response test=RestAssured.given()
		.log().all()//this will give APIrequest body data
		.headers("Content-Type","application/json")
		.body("{\r\n"
				+ "    \"username\" : \"admin\",\r\n"
				+ "    \"password\" : \"password123\"\r\n"
				+ "}")
		.when()
		.post("/auth");
		//test.prettyPrint(); //we can use this inbuilt method to print response data
		
		
		
		//.then().log().all().extract().response();
		// we can Use SOP  to check response or we can use logs on line above
		
		System.out.println(test.statusCode());//will print code from response
		
		Assert.assertEquals(test.statusCode(),200);
		// on above line comparing code from response to expected status_code 
		
		//System.out.println(test.asPrettyString());//this will print response body
		
		//As response is json we can get string /token out of it
		
		System.out.println(test.jsonPath().getString("token"));
		
		
		
		
	}
}

