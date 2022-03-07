package stepDefinations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Request_Response_Spec_Builder.addPlaceMapAPI;
import Request_Response_Spec_Builder.subClassForLocation;
import Resources.APIResources;
import Resources.Utils;
import Resources.testdata;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class StepDefination extends Utils{

	RequestSpecification r;
	ResponseSpecification outputRequest;
	Response rp;
	testdata data = new testdata();
	static String getPlaceId;
	JsonPath js;

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException {
		r = given().spec(requestSpecification())
				.body(data.addPlacePayload(name, language, address));
	}

	@When("users call {string} with {string} http request")
	public void users_call_with_post_http_request(String resources, String httpMethod) {
		
		APIResources resourceApi = APIResources.valueOf(resources);
		resourceApi.getResource();
		
		outputRequest = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(httpMethod.equalsIgnoreCase("Post")){
			rp = r.when().post(resourceApi.getResource());
			System.out.println("POST Passed");
		}
		else if(httpMethod.equalsIgnoreCase("Get")){
			rp = r.when().get(resourceApi.getResource());
		}
		//then().spec(outputRequest).extract().response();
	}

	@Then("the APi call got success with status code {int}")
	public void the_a_pi_call_got_success_with_status_code(Integer int1) {
		assertEquals(rp.getStatusCode(), 200);
		System.out.println("POST Passed with 200");
	}

	@Then("{string} in responce body is {string}")
	public void in_responce_body_is(String key, String value) {
//		String getStatusCode = rp.asString();
//		js = new JsonPath(getStatusCode);
		System.out.println(" Key and value -->" +key +"---" +value + "========" +getJsonPath(rp, key));
		assertEquals(getJsonPath(rp, key), value);
	}
	
	 @And("Verify place_Id created maps to {string} using {string}")
	    public void verify_placeid_created_maps_to_something_using_something(String name, String placeId) throws Exception {
		 getPlaceId = getJsonPath(rp, "place_id");
		 System.out.println("place ID ------- "+getPlaceId);
		 r = given().spec(requestSpecification()).queryParam("place_id", getPlaceId);
		 users_call_with_post_http_request(placeId, "GET");
		 String getName = getJsonPath(rp, "name");
		 System.out.println("Get Name ------- "+getName);
		 assertEquals(getName, name);
	    }

	 
	   @Given("Delete PayLoad")
	    public void delete_payload() throws Throwable {
		   System.out.println("new Place Id -- >" +getPlaceId);
		   r = given().spec(requestSpecification()).body(data.deletePlace(getPlaceId));
		   System.out.println("new Place Id -- >  1");
	    
	    }

	
}
