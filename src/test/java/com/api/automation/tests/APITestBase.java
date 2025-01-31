package com.api.automation.tests;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import io.restassured.specification.RequestSpecification;

@Listeners({AllureTestNg.class})
public class APITestBase {
protected static RequestSpecification requestSpec; // Request Specification Defines a reusable request configuration, so we don't have to repeat headers in every test


@BeforeClass  // A TestNG annotation that ensures setup is run once before any test in this class.


public void setup() {
	RestAssured.baseURI ="https://jsonplaceholder.typicode.com";
	requestSpec = given().header("Content-Type","application/json")
			.header("Accept","application/json");
	
}

}
