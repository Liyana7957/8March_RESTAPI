Feature: Validating place api

@AddPlace
Scenario Outline: Verify if place is being sucessfully added using addPlaceAPI
Given Add Place Payload with "<name>" "<language>" "<address>"
When users call "AddPlaceAPI" with "Post" http request
Then the APi call got success with status code 200
And "status" in responce body is "OK"
And "scope" in responce body is "APP"
And Verify place_Id created maps to "<name>" using "getPlaceAPI"



Examples:
		|name   | language | address|
		|amit1  |lang1     |add1    |
	#	|amit2  |lang2     |add2    |

	
	@DeletePlace
	Scenario: Verify if delete place functionality is working
	Given Delete PayLoad
	When users call "deletePlaceAPI" with "Post" http request
	Then the APi call got success with status code 200
	And "status" in responce body is "OK"
	
	