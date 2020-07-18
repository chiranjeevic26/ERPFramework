package com.dell;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import com.files.payLoad;

public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("Key", "qaclick123").header("Content-type", "application/json")
		.body(payLoad.addPlace())
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200)
		.body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		JsonPath json=new JsonPath(response);
		String placeId=json.getString("place_id");
		System.out.println(placeId);
	}

}
