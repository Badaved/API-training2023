package RestAssured;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Constants.Status_code;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CreateBookingVersion2 {
	@BeforeTest	
	public void Gettoken() {
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
	}
		@Test
		public void createbooking() {
			Response booking= RestAssured.given()
					.headers("Content-Type","application/json")
					.headers("Accept","application/json")
					.body("{\r\n"
							+ "					    \"firstname\" : \"Jim\",\r\n"
							+ "					    \"lastname\" : \"Brown\",\r\n"
							+ "					    \"totalprice\" : 111,\r\n"
							+ "					    \"depositpaid\" : true,\r\n"
							+ "					    \"bookingdates\" : {\r\n"
							+ "					        \"checkin\" : \"2018-01-01\",\r\n"
							+ "					        \"checkout\" : \"2019-01-01\"\r\n"
							+ "					    },\r\n"
							+ "					    \"additionalneeds\" : \"Breakfast\"\r\n"
							+ "					}")
					.when().log().all()
					.post("/booking");
			booking.then().log().all();
			System.out.println(booking.statusCode());//displays status code
			System.out.println(booking.statusLine()); //displays status line 
			System.out.println(booking.getStatusCode());// using getstatus code method
			System.out.println(booking.getContentType());
			
			Assert.assertEquals(booking.statusCode(), Status_code.OK);//verified Status code of actual response and expected 
			
			
		}
	}


