package Base;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Base_service {
	public Map<String,String> headermap= new HashMap<>();
	public Base_service() {
		RestAssured.baseURI="http://Fmc-env.eba-5akrwvvr.us-east-1.elasticbeanstalk.com";
	
		
	}

		public Base_service(String baseURI) {
			RestAssured.baseURI=baseURI;
		}
		public Map<String,String>GetHeaderwithAuth(String token){
		;
			headermap.put("Content-Type", "application/json");
			headermap.put("Accept", "application/json");
			headermap.put("Authorization","Bearer "+token);
			return headermap;
			
		}
		public Map<String,String>GetHeaderwithoutAuth(){
		
			headermap.put("Content-Type", "application/json");
			headermap.put("Accept", "application/json");
			
			return headermap;
			
		}
		
public Response executeGetAPI(String endpoint,Map<String,String>header) {
	return RestAssured.given()
	.headers(header)
	.log().all()
	.when()
	.get(endpoint)
	.then()
	.log().all().extract().response();
}

public Response executePostAPI(String endpoint,Map<String,String>header,Object json) {
	return RestAssured.given()
			.headers(header)
			.log().all()
			.body(json)
			.when()
			
		     .post(endpoint)
			.then()
			.log().all().extract().response();
}

public Response executePutAPI(String endpoint,Map<String,String>header,Object json) {
	return RestAssured.given()
			.headers(header)
			.log().all()
			.body(json)
			.when()
			
		    .put(endpoint)
			.then()
			.log().all().extract().response();
	
}
public Response executeDeleteAPIwithpayload(String endpoint,Map<String,String>header,Object json) {
	return RestAssured.given()
			.headers(header)
			.log().all()
			.body(json)
			.when()
		    .delete(endpoint)
			.then()
			.log().all().extract().response();
	
}
public Response executeDeleteAPIwithoutpayload(String endpoint,Map<String,String>header,Object json) {
	return RestAssured.given()
			.headers(header)
			.log().all()
			.body(json)
			.when()
			 .delete()
		    .then()
			.log().all().extract().response();
	
}
}
