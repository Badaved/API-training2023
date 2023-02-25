package RestAssured;
//Fake data used in payload of JSON object type of Payload
//https://github.com/datafaker-net/datafaker
//added dependancy in pom xml for fake data
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Constants.Status_code;
import Pojo.request.CreateBooking.Booking;
import Pojo.request.CreateBooking.Bookingdates;
import Pojo.request.CreateBooking.Token;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.datafaker.Faker;

public class PayloadFaker  {

	String token;
	Booking payload;
	int booking_id;
	Bookingdates bookingdates;
	@BeforeTest
	void Gettoken() {
		RestAssured.baseURI = Status_code.URL;
		Token test1=new Token();
		test1.setPassword("password123");
		test1.setUsername("admin");
		Response test = RestAssured.given().log().all().header("Content-Type", "application/json")
				.body(test1)
				.when().post("/auth");
		test.then().log().all();
        token = test.jsonPath().getString("token");
		//System.out.println("Toekn is" + token);
		System.out.println("Response is" + test.asPrettyString());
		Assert.assertEquals(test.statusCode(), Status_code.OK);
		//System.out.println("Content type is" + test.getContentType());

	}

	@Test
	 void   CreateBooking()  {
	JSONObject json= new JSONObject();
		Faker fakedata=new Faker();
		
	json.put("firstname", fakedata.name().firstName());
	json.put("lastname", fakedata.name().lastName());
	json.put("totalprice",fakedata.number().positive());
	json.put("depositpaid", fakedata.bool().bool());
	json.put( "additionalneeds","extra");
	
	JSONObject dates =new JSONObject();
	dates.put( "checkin", fakedata.date().birthday("2013-02-23"));
	dates.put( "checkout", fakedata.date().birthday("2014-10-23"));
	json.put("bookingdates", dates);
		Response resp = RestAssured.given()

		.headers("Content-Type", "application/json").headers("Accept", "application/json")
		.body(json).when().post("/booking");
		resp.getStatusCode();
		//System.out.println("Content type is" + resp.getContentType());
		resp.then().log().all();
		booking_id=resp.jsonPath().getInt("bookingid");
		System.out.println(resp.getStatusCode());
		System.out.println("Booking id created is   " +booking_id);
		Assert.assertTrue(booking_id>0);
		//validateReponse(resp,payload,"booking.");
	}

}