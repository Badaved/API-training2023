package RestAssured;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Constants.Status_code;
import Pojo.request.CreateBooking.Booking;
import Pojo.request.CreateBooking.Bookingdates;
import Pojo.request.CreateBooking.Token;
import io.restassured.RestAssured;

import io.restassured.response.Response;

public class CreateBookingFinal {

	@BeforeTest
	void Gettoken() {
		
		String token;
		RestAssured.baseURI = Status_code.URL;
		Token test1=new Token();
		test1.setPassword("password123");
		test1.setUsername("admin");
		Response test = RestAssured.given().log().all().header("Content-Type", "application/json")
				.body(test1)
				.when().post("/auth");
		test.then().log().all();
	
		token = test.jsonPath().getString("token");
		System.out.println("Toekn is" + token);
		System.out.println("Response is" + test.asPrettyString());
		Assert.assertEquals(test.statusCode(), Status_code.OK);
		System.out.println("Content type is" + test.getContentType());

	}

	@Test(priority=1)

	 void   CreateBookingpojo()  {
		int booking_id;
		Bookingdates bookingdates = new Bookingdates();
		bookingdates.setCheckin("2013-02-23");
		bookingdates.setCheckout("2014-10-23");
		Booking payload = new Booking();
		payload.setBookingdates(bookingdates);
		payload.setFirstname("Gargi");
		payload.setLastname("Badave");
		payload.setAdditionalneeds("Breakfast");
		payload.setDepositpaid(true);
		payload.setTotalprice(100);

		Response resp = RestAssured.given()

				.headers("Content-Type", "application/json").headers("Accept", "application/json")
				.body(payload).log().all().when().post("/booking");
			resp.getStatusCode();
		System.out.println("Content type is" + resp.getContentType());
		resp.then().log().all();
		booking_id=resp.jsonPath().getInt("bookingid");
		System.out.println(resp.getStatusCode());
			System.out.println("Booking id created is   " +booking_id);
		Assert.assertTrue(booking_id>0);
		Assert.assertEquals(resp.jsonPath().getString("booking.firstname"), payload.getFirstname());
		Assert.assertEquals(resp.jsonPath().getString("booking.lastname"), payload.getLastname());
		Assert.assertEquals(resp.jsonPath().getString("booking.additionalneeds"),payload.getAdditionalneeds());
		Assert.assertEquals(resp.jsonPath().getString("booking.bookingdates.checkin"), payload.getBookingdates().getCheckin());
		Assert.assertEquals(resp.jsonPath().getString("booking.bookingdates.checkout"), payload.getBookingdates().getCheckout());
	    Assert.assertSame(resp.jsonPath().getBoolean("booking.depositpaid"),payload.getDepositpaid());
	    Assert.assertSame(resp.jsonPath().getInt("booking.totalprice"),payload.getTotalprice());
	 
		
	
	}
		//Assert.assertTrue(Integer.valueOf(resp.jsonPath().getInt("bookingid")) instanceof Integer);
		
		@Test(priority=2)
	    void Getid()  {
			int bookingid= 15550;
			Response GetID= RestAssured.given()
					.when().get("/booking");
			
			 
			//System.out.println("Response IS " + GetID.asPrettyString());
			List<Integer> IDs=GetID.jsonPath().getList("bookingid");
			
			System.out.println("List Size is " + IDs.size());
			
			Assert.assertTrue( IDs.contains(bookingid));
			
		}

	}



