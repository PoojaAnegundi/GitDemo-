package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//import static org.junit.Assert.*;
import POJO.AddPlacePOJO;
import POJO.LocationPOJO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class stepDefination extends Utils{

	RequestSpecification res;
	ResponseSpecification respspec;
	Response response;
	TestDataBuild testData = new TestDataBuild();
	static String  place_id;

	@Given("Add Place Payload with {string} and {string} and {string}")
	public void add_place_payload_with_and_and(String name, String language, String address) throws IOException {
				//respspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
				res = given().spec(requestSpecification()).body(testData.addPlacePayload(name,language,address));	
	}
	
	@When("user calls {string} with {string} HTTP request")
	public void user_calls_with_http_request(String resource, String method) {
		
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
		respspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST"))
			response = res.when().post(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("GET"))
			response = res.when().get(resourceAPI.getResource());
	}	   
	
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		  assertEquals(response.getStatusCode(),200);
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
//	   String resp = response.asString();
//	   JsonPath js = new JsonPath(resp);
	   assertEquals(getJasonPathKaysValue(response,keyValue).toString(), expectedValue);
	   }
	
	@Then("Verify place_Id created, maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		place_id = getJasonPathKaysValue(response, "place_id");
		//prepare reqSpec
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "GET");
		String actualnameGotFromResponse = getJasonPathKaysValue(response, "name");
		assertEquals(actualnameGotFromResponse, expectedName);
	}	
	
	@Given("DeletePlace Payload")	
	public void delete_place_payload() throws IOException {
		res = given().spec(requestSpecification()).body(testData.deletePlacePayload(place_id));
	}
}
