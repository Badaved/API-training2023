package RestAssured;
	import org.testng.Assert;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.Test;

	import Constants.Status_code;
	import io.restassured.RestAssured;
	import io.restassured.response.Response;

	public class MockServer_createBooking {
		@BeforeTest	
		public void Gettoken() {
			String token;
			RestAssured.baseURI="https://7ec7139a-94ff-4e50-94e9-ddcfde8380d5.mock.pstmn.io"; 
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
			int booking_id= booking.jsonPath().getInt("bookingid");
			System.out.println(booking_id  +"Booking id is negative");
			Assert.assertTrue(booking_id>0);
			
			/*
			1. Changed Base uri from original to  Mock server URI from post man
			2. In postman create Mockserver
			3. Select post and wirte /booking in request and hi in request body
			4. Select own environment option for new mock server with some name
			5. select default from mock server request
			6. edit response to expected response as J-son like here we want to test if booking_id is negative test should fail
			7. added booking is as -14
			8. click Try on default request and get mock base url from postman
			9. Use that url to test negative testing



			*/
				
				//Assert.assertEquals(booking.statusCode(), Status_code.OK);//verified Status code of actual response and expected 
				
				
			}
		}




