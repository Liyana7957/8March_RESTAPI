package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws Exception{
		StepDefination sd = new StepDefination();
		if(StepDefination.getPlaceId==null){
		sd.add_place_payload("kos", "language1", "address hook 1");
		sd.users_call_with_post_http_request("AddPlaceAPI", "Post");
		sd.verify_placeid_created_maps_to_something_using_something("place_id", "Post");
		}
	}

}
