package RestAssured;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Constants.Status_code;
import Pojo.request.CreateBooking.Booking;
import Pojo.request.CreateBooking.Bookingdates;
import Pojo.request.CreateBooking.Token;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetBooking_Deserialization {

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
	
		bookingdates = new Bookingdates();
		bookingdates.setCheckin("2013-02-23");
		bookingdates.setCheckout("2014-10-23");
	    payload = new Booking();
		payload.setBookingdates(bookingdates);
		payload.setFirstname("Gargi");
		payload.setLastname("Badave");
		payload.setAdditionalneeds("Breakfast");
		payload.setDepositpaid(true);
		payload.setTotalprice(100);
        Response resp = RestAssured.given()

		.headers("Content-Type", "application/json").headers("Accept", "application/json")
		.body(payload).when().post("/booking");
		resp.getStatusCode();
		//System.out.println("Content type is" + resp.getContentType());
		resp.then().log().all();
		booking_id=resp.jsonPath().getInt("bookingid");
		System.out.println(resp.getStatusCode());
		System.out.println("Booking id created is   " +booking_id);
		Assert.assertTrue(booking_id>0);
		validateReponse(resp,payload,"booking.");
	}
	@Test
	
	void getbooking() {
		//int booking_id=2151;
			Response res = RestAssured.given()
			.headers("Accept","application/json")
			.when().log().all()
			.get("/booking/"+booking_id);
			System.out.println(res.getStatusCode());
			System.out.println(res.asPrettyString());
			Booking serialize = res.as(Booking.class);
			//System.out.println("Deserilization result" +serialize);
			Assert.assertEquals(payload.getFirstname(), serialize.getFirstname());
			Assert.assertEquals(payload.getLastname(), serialize.getLastname());
			System.out.println("Fresponse is"+ res.asPrettyString());
			Assert.assertTrue(serialize.equals(payload));
			//validateReponse(res,payload,"");
				
	}
	

	private void validateReponse(Response res,Booking payload,String object) 
	{
		Assert.assertEquals(res.jsonPath().getString(object+"firstname"), payload.getFirstname());
		Assert.assertEquals(res.jsonPath().getString(object+"lastname"), payload.getLastname());
		Assert.assertEquals(res.jsonPath().getString(object+"additionalneeds"),payload.getAdditionalneeds());
		Assert.assertEquals(res.jsonPath().getString(object+"bookingdates.checkin"), payload.getBookingdates().getCheckin());
		Assert.assertEquals(res.jsonPath().getString(object+"bookingdates.checkout"), payload.getBookingdates().getCheckout());
	    Assert.assertSame(res.jsonPath().getBoolean(object+"depositpaid"),payload.getDepositpaid());
	    Assert.assertSame(res.jsonPath().getInt(object+"totalprice"),payload.getTotalprice());
	}

}

