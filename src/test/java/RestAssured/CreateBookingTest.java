package RestAssured;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.*;

import io.restassured.response.Response;


public class CreateBookingTest {
	@Test
	public void CreateBooking() {
		// System.out.println("Test");
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		Response res = RestAssured.given().log().all().headers("Content-Type", "application/json")
				.body("{\r\n" + "    \"username\" : \"admin\",\r\n" + "    \"password\" : \"password123\"\r\n" + "}")
				.when().post("/auth");
		// then().log().all().extract().response()

		// System.out.println(res.statusCode());
		Assert.assertEquals(res.statusCode(), 200);
		// System.out.println(res.asPrettyString());
		String token = res.jsonPath().getString("token");
		System.out.println(token);

	}

	@Test
	public void createBooking1() {
		Response test = RestAssured.given().headers("Content-Type", "application/json")
				.headers("Accept", "application/json")
				.body("{\r\n" + "    \"firstname\" : \"Jim\",\r\n" + "    \"lastname\" : \"Brown\",\r\n"
						+ "    \"totalprice\" : 111,\r\n" + "    \"depositpaid\" : true,\r\n"
						+ "    \"bookingdates\" : {\r\n" + "        \"checkin\" : \"2018-01-01\",\r\n"
						+ "        \"checkout\" : \"2019-01-01\"\r\n" + "    },\r\n"
						+ "    \"additionalneeds\" : \"Breakfast\"\r\n" + "}")
				.when().post("/booking");

		Assert.assertEquals(test.getStatusCode(), 200);
	}
}
//	@Test
//	public void nobdd() {
//String Payload =("{\r\n"
//		+ "    \"username\" : \"admin\",\r\n"
//		+ "    \"password\" : \"password123\"\r\n"
//		+ "}");
//RequestSpecification resp =RestAssured.given();
//resp.baseUri("https://restful-booker.herokuapp.com");
//resp.headers("Content-Type", "application/json");
//resp.body(Payload);
//resp.when();
//Response res1=resp.post("/auth");
////System.out.println(res1.statusCode());
////Assert.assertEquals(res1.statusCode(), 200);
//System.out.println((res1.response().)));
//}
//}
