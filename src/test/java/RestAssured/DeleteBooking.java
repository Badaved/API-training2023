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

public class DeleteBooking {
	

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
		//System.out.println("Token is" + token);
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
		//validateReponse(resp,payload,"booking.");
	}
	
				
	@ Test
	void Delete() {
	
		Response update = RestAssured.given()
				
				.headers("Content-Type","application/json")
				//.headers("Accept","application/json")
				.headers("Cookie","token="+token)
				//.headers("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
				.when().log().all()
				.delete("/booking/"+booking_id);
		System.out.println(update.asPrettyString());
		//Booking serialize = update.as(Booking.class);
		//Assert.assertTrue(serialize.equals(payload));
		Assert.assertEquals(update.statusCode(), Status_code.Created);
		
	}
	@Test
	void verifyDeleted() {
		Response res =RestAssured.given()
				.headers("Content-Type", "application/json")
				.headers("Accept", "application/json")
				.when()
				.get("/booking/"+booking_id);
		Assert.assertEquals(res.asPrettyString(), "Not Found");
		System.out.println("booking id deleted");
	}
	
		
		
	

}
