package Resources;

import java.util.ArrayList;
import java.util.List;

import Request_Response_Spec_Builder.addPlaceMapAPI;
import Request_Response_Spec_Builder.subClassForLocation;

public class testdata {
	
	public addPlaceMapAPI addPlacePayload(String name, String language, String address){
		addPlaceMapAPI ap = new addPlaceMapAPI();
		List<String> arr = new ArrayList<String>();
		ap.setAccuracy(30);
		
		
		ap.setAddress(address);
		ap.setLanguage(language);
		ap.setName(name);
		
		
		ap.setPhone_number("123321456");
		ap.setWebsite("www.yahoo.com");
		arr.add("asdf");
		arr.add("lll");
		ap.setTypes(arr);
		subClassForLocation sl = new subClassForLocation();
		sl.setLat(-33.3333);
		sl.setLng(-22.3333);
		ap.setLocation(sl);
		return ap;
	}
	
	public String deletePlace(String placeId){
		return "{\r\n" + 
				"   \"place_id\": \""+placeId+"\"\r\n" + 
				"}";
	}

}
