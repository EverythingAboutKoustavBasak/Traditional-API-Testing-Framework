package tests;

import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojo.RepoPojo;
import com.restapibase.BaseClass;

import io.restassured.response.Response;
import utiliity.AuthN;
import utiliity.CommonUtilFunction;
import utiliity.CreateURL;
import utiliity.PayloadGenerator;
import utiliity.RestFWLogger;

public class CreateRepoTest {

	private static final Logger logger = RestFWLogger.getLogger(CreateRepoTest.class);
	String endPoint = CreateURL.getBaseURI("/user/repos");
	String bearerToken = AuthN.getBearertoken();

	@Test(description = "Verify the repository is successfully cretaed by using .json as a payload")
	public void createRepoTestCase1() throws IOException {

		RestFWLogger.startTestCase(logger, "createRepoTestCase1");

		String req_payload = PayloadGenerator.generateStringPayload("createRepo.json");
		
		Response response = BaseClass.postRequest(endPoint, req_payload, bearerToken);
//		System.out.println("+++++++Test 1 - responses+++++++++");
//		System.out.println(response.asPrettyString());

		logger.info("Assertion for repo name - staring");
		Assert.assertEquals(CommonUtilFunction.getResponseKeyValue(response, "name"),
				CommonUtilFunction.getPayloadKeyValue(req_payload, "name"), "Repo name is not matching");
		logger.info("Assertion for repo name - successfully done");
		
		logger.info("Assertion for repo description - starting");
		Assert.assertEquals(response.jsonPath().getString("description"),
				CommonUtilFunction.getPayloadKeyValue(req_payload, "description"), "Repo description is not matching");
		logger.info("Assertion for repo description - successfully done");

		RestFWLogger.endTestCase(logger, "createRepoTestCase1");
	}

	@Test(description = "Verify the repository is successfully cretaed by using pojo as a payload")
	public void createRepoTestCase2() throws IOException {

		RestFWLogger.startTestCase(logger, "createRepoTestCase2");

		// set the values by using pojo setter methods
		RepoPojo repoPojo = new RepoPojo();
		repoPojo.setName("API-testing-repo-125");
		logger.info("the name of repo as payload is - " + repoPojo.getName());
		repoPojo.setDescription("Creeate the repo by using pojo");
		logger.info("the description of repo as payload is - " + repoPojo.getDescription());

		// Create ObjectMapper
		ObjectMapper objectMapper = new ObjectMapper();

		// Convert POJO into JSON
		String repoPayload = null;
		try {
			repoPayload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(repoPojo);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		Response response = BaseClass.postRequest(endPoint, repoPayload, bearerToken);
//		System.out.println("+++++++Test 2 - responses+++++++++");
//		System.out.println(response.asPrettyString());

		logger.info("Assertion for repo name - staring");
		Assert.assertEquals(CommonUtilFunction.getResponseKeyValue(response, "name"), repoPojo.getName(),
				"Repo name is not matching");
		logger.info("Assertion for repo name - Successfully Done");
		logger.info("Assertion for repo description - starting");
		Assert.assertEquals(response.jsonPath().getString("description"), repoPojo.getDescription(),
				"Repo description is not matching");
		logger.info("Assertion for repo description - successfully done");

		RestFWLogger.endTestCase(logger, "createRepoTestCase2");
	}

	@Test(description = "Verify delete test cases", dependsOnMethods = "createRepoTestCase1")
	public void deleteRepoTest1() throws IOException {

		RestFWLogger.startTestCase(logger, "deleteRepoTest1");

		// req payload
		String reqPayload = PayloadGenerator.generateStringPayload("createRepo.json");
		// end point
		String deleteEndpoint = CreateURL.getBaseURI("/repos/KoustavBasakMH/")
				+ CommonUtilFunction.getPayloadKeyValue(reqPayload, "name");

		Response response = BaseClass.deleteRequestWithBearerToken(deleteEndpoint, bearerToken);
		
		// Verify status code
		logger.info("Verify respose code - starting");
		Assert.assertEquals(response.statusCode(), 204, "Repository deletion failed");
		logger.info("Verify respose code - completed");
		// Verify empty response body
		logger.info("Verify respose body - starting");
		Assert.assertTrue(response.asString().isEmpty(), "Expected empty response body");
		logger.info("Verify respose body - completed");
		// Verify repository no longer exists
		Response getResponse = BaseClass.getRequest(deleteEndpoint, bearerToken);

		logger.info("Verify repo exsistance - starting");
		Assert.assertEquals(getResponse.statusCode(), 404, "Repository still exists after deletion");
		logger.info("Verify repo exsistance - Completed");
		RestFWLogger.endTestCase(logger, "deleteRepoTest1");
	}

	@Test(description = "Verify delete test cases using the pojo as payload", dependsOnMethods = "createRepoTestCase2")
	public void deleteRepoTest2() throws IOException {

		RestFWLogger.startTestCase(logger, "deleteRepoTest2");

		// set the values by using pojo setter methods
		RepoPojo repoPojo = new RepoPojo();
		repoPojo.setName("API-testing-repo-125");
		logger.info("the name of repo as payload is - " + repoPojo.getName());
		repoPojo.setDescription("Creeate the repo by using pojo");
		logger.info("the description of repo as payload is - " + repoPojo.getDescription());

//				// Create ObjectMapper
//						ObjectMapper objectMapper = new ObjectMapper();
//
//						// Convert POJO into JSON
//						String repoPayload = null;
//						try {
//							repoPayload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(repoPojo);
//						} catch (JsonProcessingException e) {
//							e.printStackTrace();
//						}
		// end point
		String deleteEndpoint = CreateURL.getBaseURI("/repos/KoustavBasakMH/") + repoPojo.getName();
		Response response = BaseClass.deleteRequestWithBearerToken(deleteEndpoint, bearerToken);

		// Verify status code
		logger.info("Verify respose code - starting");
		Assert.assertEquals(response.statusCode(), 204, "Repository deletion failed");
		logger.info("Verify respose code - completed");
		
		// Verify empty response body
		logger.info("Verify respose body - starting");
		Assert.assertTrue(response.asString().isEmpty(), "Expected empty response body");
		logger.info("Verify respose body - completed");
		
		// Verify repository no longer exists
		Response getResponse = BaseClass.getRequest(deleteEndpoint, bearerToken);
		logger.info("Verify repo exsistance - starting");
		Assert.assertEquals(getResponse.statusCode(), 404, "Repository still exists after deletion");
		logger.info("Verify repo exsistance - Completed");
		RestFWLogger.endTestCase(logger, "deleteRepoTest2");

	}

}
