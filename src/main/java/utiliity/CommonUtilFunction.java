package utiliity;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CommonUtilFunction {

	
	public static <T> T getResponseKeyValue(
	        Response response, String response_key) {

	    return response.jsonPath().get(response_key);
	}
	
	
	public static String getPayloadKeyValue(
			String payload, String payload_key) {
		
		JsonPath jsonPath = new JsonPath(payload);
		String keyValue = jsonPath.getString(payload_key);
		
		return keyValue;
	}
}
