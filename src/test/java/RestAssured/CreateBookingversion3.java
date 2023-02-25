
package RestAssured;

import org.testng.Assert;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Constants.Status_code;
import Pojo.request.CreateBooking.Booking;
import Pojo.request.CreateBooking.Bookingdates;
import Pojo.request.CreateBooking.Token;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateBookingversion3 {
	@BeforeTest
	public void Gettoken() {
//		String token;
//		Token payloadToken = new Token();
//		payloadToken.setUsername("admin");
//		payloadToken.setPassword("password123");
		String token;
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
		 token=test.jsonPath().getString("token");
		System.out.println(token);
		System.out.println(test.getContentType());
	}

	@Test
	public void createbookingUsingpojo() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		Bookingdates bookingDates = new Bookingdates();
		bookingDates.setCheckin("2023-02-27");
		bookingDates.setCheckout("2023-03-05");
		Booking payload = new Booking();
		payload.setFirstname("Medhaj");
		payload.setLastname("Badave");
		payload.setBookingdates(bookingDates);
		payload.setDepositpaid(false);
		payload.setTotalprice(500);
		payload.setAdditionalneeds("Breakfast");

		Response booking= RestAssured.given()
			
				.headers("Content-Type","application/json")
				.headers("Accept","application/json")	.body(payload)
				.when().log().all()
				.post("/booking");
				
				
				
		System.out.println(booking.getContentType());
		booking.then().log().all();
		System.out.println(booking.statusCode());// displays status code
		System.out.println(booking.statusLine()); // displays status line
		System.out.println(booking.getStatusCode());// using getstatus code method

		//Assert.assertEquals(booking.statusCode(), Status_code.OK);// verified Status code of actual response and
																	// expected

	}
}


