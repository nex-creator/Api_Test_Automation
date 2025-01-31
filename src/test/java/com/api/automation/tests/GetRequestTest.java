package com.api.automation.tests;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import io.restassured.response.Response;
@Listeners({AllureTestNg.class})
public class GetRequestTest extends APITestBase {
	@Test
	 public void testGetUser() {
		Response response = given().spec(requestSpec)
				.when().get("/users");
		Assert.assertEquals(response.getStatusCode(),200);
		Assert.assertTrue(response.getBody().asString().contains("Leanne Graham"));
		System.out.println("Get Response\n  "+ response.prettyPrint());
		
	}
}
