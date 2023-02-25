package RestAssured;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.Test;

import Base.Base_service;
import Utility.Data_Generator;
import io.restassured.response.Response;

public class FMC_Sourcefiles {
	String token;
	String otp;
	int User_id;
	String email=Data_Generator.getemailid();
	String fullname=Data_Generator.getfullname();
	String Phone=Data_Generator.PhoneNumber();
	String pass= "API";
	Base_service service= new Base_service();
	
	@Test(priority=1)
	void createToken() {
	
		service.GetHeaderwithoutAuth();
		Response test=service.executeGetAPI("/fmc/token",service.headermap);
	    token=test.jsonPath().get("accessToken");
		System.out.println(token);
		
	}

	@SuppressWarnings("unchecked")
	@Test(priority=2)
	void  EmailSignup() {
		
		JSONObject json=new JSONObject();
		json.put("email_id", email);
		
		service.GetHeaderwithAuth(token);
		Response test1 = service.executePostAPI("/fmc/email-signup-automation",service.headermap,json);
		
		System.out.println(test1.asPrettyString());
		 otp=test1.jsonPath().get("content.otp");	
		
	}
	@Test(priority=3)
	@SuppressWarnings({ "unused", "unchecked" })
	private void VerifyOTP() {
		JSONObject json= new JSONObject();
		json.put("email_id",email);
		json.put("full_name",fullname);
		json.put("phone_number",Phone);
		json.put("password",pass);
		json.put("otp",otp);
		service.GetHeaderwithAuth(token);
		Response test2 = service.executePutAPI("/fmc/verify-otp",service.headermap,json);
	
		User_id=test2.jsonPath().get("content.userId");
		System.out.println();
		
		
	}
	@Test (priority=4)
	private void Login() {
		// TODO Auto-generated method stub
		JSONObject json= new JSONObject();
		json.put("email_id",email);
		json.put("password",pass);
        service.GetHeaderwithAuth(token);
        
		Response test2 =service.executePostAPI("/fmc/login",service.headermap, json);
		
		
		 int id=test2.jsonPath().get("content.userId");
		 Assert.assertEquals(User_id, id);
		 System.out.println(test2.jsonPath().getString("status"));
	}
	
	
	
}
