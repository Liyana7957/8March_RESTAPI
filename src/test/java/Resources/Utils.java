package Resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {

	ResponseSpecification outputRequest;
	static RequestSpecification inputRequest;

	public RequestSpecification requestSpecification() throws IOException  {
		
		if(inputRequest ==null){
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		//RestAssured.baseURI = "https://rahulshettyacademy.com";
		//RestAssured.baseURI = "https://rahulshettyacademy.com";
		inputRequest = new RequestSpecBuilder().setBaseUri(getGlobalProperties("baseUrl"))
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		return inputRequest;
	}
		return inputRequest;
	}
	
	public static String getGlobalProperties(String key) throws IOException{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(new File(System.getProperty("user.dir")+ "\\src\\test\\java\\Resources\\global.properties"));
		prop.load(fis);
		System.out.println(prop.getProperty(key));
		return prop.getProperty(key);
		
	}
	
	public String getJsonPath(Response response, String key){
		String getStatusCode = response.asString();
		JsonPath js = new JsonPath(getStatusCode);
		return js.get(key).toString();
	}

}
