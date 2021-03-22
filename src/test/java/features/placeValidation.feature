Feature: Validating Place API's

@AddPlace
Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
Given Add Place Payload with "<name>" and "<language>" and "<address>" 
When user calls "AddPlaceAPI" with "POST" HTTP request
Then the API call got success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And Verify place_Id created, maps to "<name>" using "GetPlaceAPI"

Examples:
| name   | language | address          |
| Ahouse | English  | Bangalore, India |
#| Bhouse | Spanish  | Solapur, India  |
#| Chouse | Germen   | Mumbai, India   |

@DeletePlace
Scenario:
Given DeletePlace Payload
When user calls "DeletePlaceAPI" with "POST" HTTP request
Then the API call got success with status code 200
And "status" in response body is "OK"