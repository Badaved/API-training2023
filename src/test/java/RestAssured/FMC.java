package RestAssured;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utility.Data_Generator;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.datafaker.Faker;

public class FMC {
	String token;
	String otp;
	int User_id;
	
	void createToken() {
		RestAssured.baseURI="http://Fmc-env.eba-5akrwvvr.us-east-1.elasticbeanstalk.com";
		Response test =RestAssured.given().headers("Accept", "application/json").log().all()
				.when().get("/fmc/token");
		test.then().log().all();
		test.asPrettyString();
		 token=test.jsonPath().get("accessToken");
		System.out.println(token);
		
	}
	
	public String EmailSignup(String Email) {
		
		//Faker fake=new Faker();
		//String email=fake.name().firstName()+"." +fake.name().lastName()+"@gmail.com";
		JSONObject json=new JSONObject();
		json.put("email_id", Email);
		RestAssured.baseURI="http://Fmc-env.eba-5akrwvvr.us-east-1.elasticbeanstalk.com";
		Response test =RestAssured.given().headers("Content-Type", "application/json").headers("Accept", "application/json").headers("Authorization","Bearer "+token).body(json).when().log().all().post("/fmc/email-signup-automation");
		test.then().log().all();
		System.out.println(test.asPrettyString());
		 otp=test.jsonPath().get("content.otp");	
		return otp;
	}

private int VerifyOTP(String email, String fullname, String phone, String pass, String otp2) {
		// TODO Auto-generated method stub
		JSONObject json= new JSONObject();
		json.put("email_id",email);
		json.put("full_name",fullname);
		json.put("phone_number",phone);
		json.put("password",pass);
		json.put("otp",otp2);
		RestAssured.baseURI="http://Fmc-env.eba-5akrwvvr.us-east-1.elasticbeanstalk.com";
		Response test1 =RestAssured.given().headers("Content-Type", "application/json").headers("Accept", "application/json").headers("Authorization","Bearer "+token).body(json).when().log().all().put("/fmc/verify-otp");
		test1.then().log().all();
		 User_id=test1.jsonPath().get("content.userId");
		System.out.println();
		return User_id;
		
	}
private void Login(String email, String pass) {
	// TODO Auto-generated method stub
	JSONObject json= new JSONObject();
	json.put("email_id",email);
	json.put("password",pass);
	RestAssured.baseURI="http://Fmc-env.eba-5akrwvvr.us-east-1.elasticbeanstalk.com";
	Response test2 =RestAssured.given().headers("Content-Type", "application/json").headers("Accept", "application/json").headers("Authorization","Bearer "+token).body(json).when().log().all().post("/fmc/login");
	test2.then().log().all();
	
	 int id=test2.jsonPath().get("content.userId");
	 Assert.assertEquals(User_id, id);
	System.out.println(test2.jsonPath().getString("status"));
}

@Test
void RunFinal() {
	createToken();
	String email=Data_Generator.getemailid();
	otp= EmailSignup(email);
	String fullname=Data_Generator.getfullname();
	String Phone=Data_Generator.PhoneNumber();
	String pass= "API";
	VerifyOTP(email,fullname,Phone,pass,otp);
	Login(email,pass);
}


	

}
