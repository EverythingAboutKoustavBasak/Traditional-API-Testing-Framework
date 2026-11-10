package com.restapibase;

import org.apache.logging.log4j.Logger;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utiliity.RestFWLogger;

public class BaseClass {

	private static final Logger logger = RestFWLogger.getLogger(BaseClass.class);
	
	// basic function of framework

	public static Response getRequest(String requestURI) {
		
		logger.info("Get Request Url: {}", requestURI);
		logger.info("Performing Get Request Action");
		RequestSpecification requestSpecification = RestAssured.given();

		requestSpecification.contentType(ContentType.JSON);

		Response response = requestSpecification.get(requestURI);
		logger.info("Get Request's Response - {}", response.asPrettyString());

		return response;
	}
	public static Response getRequest(String requestURI, String bearer_token) {
		logger.info("Get Request Url: {}", requestURI);
		logger.info("Performing Get Request with bearer token mechanism Action");
		RequestSpecification requestSpecification = RestAssured.given();
		
		requestSpecification.contentType(ContentType.JSON);
		requestSpecification.header("Authorization", "Bearer " +bearer_token);
		Response response = requestSpecification.get(requestURI);
		logger.info("Get Request's Response - {}", response.asPrettyString());
		return response;
		
	}

	public static Response postRequest(String requestURI, Object reqPayload) {
		
		logger.info("Post Request Url: {}", requestURI);
		logger.info("Performing post Request with only payload mechanism Action");
		RequestSpecification requestSpecification = RestAssured.given().body(reqPayload);

		requestSpecification.contentType(ContentType.JSON);

		Response response = requestSpecification.post(requestURI);
		logger.info("Post Request's Response - {}", response.asPrettyString());
		return response;

	}
	public static Response postRequest(String requestURI, Object reqPayload, String bearer_token) {
		logger.info("Post Request Url: {}", requestURI);
		logger.info("Performing post Request with payload and bearer token mechanism Action");
		
		RequestSpecification requestSpecification = RestAssured.given().body(reqPayload);
		
		requestSpecification.contentType(ContentType.JSON);
		requestSpecification.header("Authorization", "Bearer " +bearer_token);
		Response response = requestSpecification.post(requestURI);
		logger.info("Post Request's Response - {}", response.asPrettyString());
		
		return response;
		
	}

	public static Response putRequest(String requestURI, Object reqPayload, String bearer_token) {
		logger.info("Put Request Url: {}", requestURI);
		logger.info("Performing put Request with payload and bearer token mechanism Action");
			
		RequestSpecification requestSpecification = RestAssured.given().body(reqPayload);

		requestSpecification.contentType(ContentType.JSON);
		requestSpecification.header("Authorization", "Bearer " +bearer_token);
		Response response = requestSpecification.put(requestURI);
		logger.info("Put Request's Response - {}", response.asPrettyString());
		return response;
	}

	public static Response deleteRequest(String requestURI) {
		logger.info("Delete Request Url: {}", requestURI);
		logger.info("Performing Delete Request Action");
			
		RequestSpecification requestSpecification = RestAssured.given();

		requestSpecification.contentType(ContentType.JSON);

		Response response = requestSpecification.delete(requestURI);
		logger.info("Delete request's reponse code - {}", response.getStatusCode());
		return response;
	}
	public static Response deleteRequestWithBearerToken(String requestURI, String bearerToken) {
		logger.info("Delete Request Url: {}", requestURI);
		logger.info("Performing Delete Request with bearer token mechanism Action");
		
		
		RequestSpecification requestSpecification = RestAssured.given();
		
		requestSpecification.contentType(ContentType.JSON);
		requestSpecification.header("Authorization", "Bearer " +bearerToken);
		
		Response response = requestSpecification.delete(requestURI);
		logger.info("Delete request's reponse code - {}", response.getStatusCode());
		return response;
	}

	public static Response deleteRequestWithPayload(String requestURI, Object reqPayload) {
		logger.info("Delete Request Url: {}", requestURI);
		logger.info("Performing Delete Request with payload mechanism Action");
		
		
		RequestSpecification requestSpecification = RestAssured.given().body(reqPayload);

		requestSpecification.contentType(ContentType.JSON);

		Response response = requestSpecification.delete(requestURI);
		logger.info("Delete request's reponse code - {}", response.getStatusCode());
		return response;
	}

}
