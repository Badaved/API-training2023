package RestAssured;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Constants.Status_code;
import Pojo.request.CreateBooking.Booking;
import Pojo.request.CreateBooking.Bookingdates;
import Pojo.request.CreateBooking.Token;
import io.restassured.RestAssured;

import io.restassured.response.Response;

public class Demo_test_Phonenumbers {


 @Test

	void Test1() {
		
	 RestAssured.baseURI=("https://021730d6-1049-4573-a934-a68ade16f9f4.mock.pstmn.io");
	 Response resp = RestAssured.given()
			 .headers("Content-Type", "application/json")
             .when()
             .get("/test");
	//System.out.println(resp.asPrettyString()); 
	//List<String> Phone=resp.jsonPath().getList("phoneNumbers.type");
	List<Object> Phone1=resp.jsonPath().getList("phoneNumbers");
	for(Object obj:Phone1 )
	{
	Map<String,String> MapofPhonenumber= (Map<String, String>)obj;
	System.out.println(MapofPhonenumber.get("type") + " " + MapofPhonenumber.get("number"));
	if (MapofPhonenumber.get("type").equals("iPhone"))
			Assert.assertTrue( MapofPhonenumber.get("number").startsWith("345"));
	else if(MapofPhonenumber.get("type").equals("home")){
		Assert.assertTrue(MapofPhonenumber.get("number").startsWith("012"));}
	}
	
	System.out.println(Phone1.size());
	
	//System.out.println(Phone);
	
             

	}
	
}
