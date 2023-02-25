package RestAssured;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateTokenPlain {
	@Test
	
	public void tokenplain() {
		String payload="{\r\n"
				+ "    \"username\" : \"admin\",\r\n"
				+ "    \"password\" : \"password123\"\r\n"
				+ "}";
	
	RequestSpecification resp= RestAssured.given();
	resp.baseUri("https://restful-booker.herokuapp.com");
	resp.headers("Content-Type","application/json");
	resp.body(payload);
	Response test=resp.post("/auth");
	Assert.assertEquals(test.statusCode(),200);
	System.out.println(test.asPrettyString());
	}

}
