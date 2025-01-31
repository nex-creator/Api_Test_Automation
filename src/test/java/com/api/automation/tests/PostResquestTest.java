package com.api.automation.tests;

import java.util.HashMap;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

@Listeners({AllureTestNg.class})
public class PostResquestTest extends APITestBase {
	
	
	@Test
	public void testCreateUser() {
		
		HashMap<String,String> requestBody = new HashMap<>();
		requestBody.put("name", "John Doe");
		requestBody.put("username", "johnd");
		requestBody.put("email","john.dpoe@example.com");
		Response response= given()
				.spec(requestSpec)
				.body(requestBody)
				.when()
				.post("/users");
		Assert.assertEquals(response.getStatusCode(),201);
		Assert.assertTrue(response.getBody().asString().contains("John Doe"));
		
		System.out.println("POST Response:\n  "+response.prettyPrint());
	}

}
