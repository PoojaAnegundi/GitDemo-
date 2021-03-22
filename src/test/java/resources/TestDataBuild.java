package resources;

import java.util.ArrayList;
import java.util.List;

import POJO.AddPlacePOJO;
import POJO.LocationPOJO;

public class TestDataBuild {
	
	public AddPlacePOJO addPlacePayload(String name, String language, String address) {
		
		// AddDeletePlace API.doc
		AddPlacePOJO addplace = new AddPlacePOJO();
		addplace.setAccuracy(50);
		addplace.setAddress(address);
		addplace.setLanguage(language);
		addplace.setName(name);
		addplace.setPhone_number("(+91) 983 893 3937");
		addplace.setWebsite("https://rahulshettyacademy.com/");
		List<String> types = new ArrayList<>();
		types.add("shoe park");
		types.add("shop") ;
		addplace.setTypes(types);
		
		//Small sub JSON for Location
		LocationPOJO location = new LocationPOJO();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		addplace.setLocation(location);
		return addplace;
	}
	
	public String deletePlacePayload(String place_id) {
		
		//convert jason to java converter to convert JASON payload to java string 
		return "{\\r\\n    \\\"place_id\\\":\\\""+place_id+"\\\"\\r\\n}";
		
	}

}
