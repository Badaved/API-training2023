package Utility;
import net.datafaker.*;
public class Data_Generator {
	private static Faker fake=new Faker();
	
	public static String getemailid() {
		return fake.name().firstName()+"." +fake.name().lastName()+"@gmail.com";
	}
	public static String getfullname() {
		return fake.name().firstName() +fake.name().lastName();
	}
	public static String PhoneNumber() {
		return fake.number().digits(10);
		
	}
}
