package tests;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class APIAutomation {
	
	public static JSONObject createUser() {
		
		  JSONObject request = new JSONObject(); 
		  request.put("id", 11); 
		  request.put("name","MP"); 
		  request.put("username", "mp2187"); 
		  request.put("email","mp.2187@gmail.com");
		  JSONObject address = new JSONObject();
		  address.put("street", "Queen strt");
		  address.put("suite", "block 54");
		  address.put("city", "London");
		  address.put("zipcode", "8458");
		  JSONObject geo = new JSONObject();
		  geo.put("lat", "-87.8576");
		  geo.put("lng", "54.89223");
		  address.put("geo", geo);
		  request.put("address", address);
		  request.put("phone", "09-76564-94");
		  request.put("website", "gfc.net");
		  JSONObject cmpny = new JSONObject();
		  cmpny.put("name", "gfc");
		  cmpny.put("catchPhrase", "hgtf jhty");
		  cmpny.put("bs", "hg");
		  request.put("geo", geo);
		  
		  given().
			body(request.toJSONString()).
			when().
			post("https://jsonplaceholder.typicode.com/users").
			then().log().all();
		  
		  boolean responseValidity = given()
	                .contentType(ContentType.JSON)
	                .when()
	                .get("https://jsonplaceholder.typicode.com/users")
	                .then()
	                .extract().jsonPath().getString("id").equals("11");
		  
		  System.out.println(responseValidity);
		  return request;
	}
	
	public static void validateExistingUser(JSONObject jobj) {
		Map<String, String> userMap = new HashMap<String,String>();
		userMap.put("id", jobj.get("id").toString());
		Response response = given()
        .contentType(ContentType.JSON)
        .params(userMap)
        .when()
        .get("https://jsonplaceholder.typicode.com/users");
		response.then().extract().jsonPath().getString("id").isEmpty();
		response.then().extract().jsonPath().getString("name").isEmpty();
		response.then().extract().jsonPath().getString("username").isEmpty();
		response.then().extract().jsonPath().getString("email").isEmpty();
		response.then().extract().jsonPath().getString("address").isEmpty();
		response.then().extract().jsonPath().getString("phone").isEmpty();
		response.then().extract().jsonPath().getString("website").isEmpty();
		response.then().extract().jsonPath().getString("company").isEmpty();
	}
	
	public static void updateUser(JSONObject jobj) {
		System.out.println(jobj.put("name", "PM"));
		String id = jobj.get("id").toString();
		given()
		     .body(jobj.toJSONString())
		     .contentType(ContentType.JSON)
		     .pathParam("id", id)
		     .when()
		     .put("https://jsonplaceholder.typicode.com/users/{id}");
		
		given()
        .contentType(ContentType.JSON)
        .pathParam("id", id)
        .when()
        .get("https://jsonplaceholder.typicode.com/users/{id}").then().log().all();
		System.out.println(jobj.get("name").equals("PM"));
	}
	
	public static void deleteUser(JSONObject jobj) {
		given()
			.body(jobj.toJSONString())
			.contentType(ContentType.JSON)
			.pathParam("id", jobj.get("id"))
			.when()
			.delete("https://jsonplaceholder.typicode.com/users/{id}");
		
		given()
        .contentType(ContentType.JSON)
        .pathParam("id", jobj.get("id"))
        .when()
        .get("https://jsonplaceholder.typicode.com/users/{id}").then().assertThat().statusCode(404);
	}
	
	public static JSONObject createNewPost() {
		JSONObject request = new JSONObject(); 
		request.put("userId", 1); 
		request.put("id",11); 
		request.put("title", "New Post"); 
		request.put("body","This is new Post");
		
		given().
		body(request.toJSONString()).
		when().
		post("https://jsonplaceholder.typicode.com/posts").
		then().log().all();
		
		boolean responseValidity = given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .extract().jsonPath().getString("id").equals("11");
	  
		System.out.println(responseValidity);
		return request;
	}
	
	public static void validatePost(JSONObject jobj) {
		Map<String, String> userMap = new HashMap<String,String>();
		userMap.put("id", jobj.get("id").toString());
		Response response = given()
        .contentType(ContentType.JSON)
        .params(userMap)
        .when()
        .get("https://jsonplaceholder.typicode.com/posts");
		response.then().extract().jsonPath().getString("userId").isEmpty();
		response.then().extract().jsonPath().getString("id").isEmpty();
		response.then().extract().jsonPath().getString("title").isEmpty();
		response.then().extract().jsonPath().getString("body").isEmpty();
	}
	
	public static void updatePost(JSONObject jobj) {
		System.out.println(jobj.put("title", "Updated Post"));
		String id = jobj.get("id").toString();
		given()
		     .body(jobj.toJSONString())
		     .contentType(ContentType.JSON)
		     .pathParam("id", id)
		     .when()
		     .put("https://jsonplaceholder.typicode.com/posts/{id}");
		
		given()
        .contentType(ContentType.JSON)
        .pathParam("id", id)
        .when()
        .get("https://jsonplaceholder.typicode.com/posts/{id}").then().log().all();
		System.out.println(jobj.get("title").equals("Updated Post"));
	}
	
	public static void deletePost(JSONObject jobj) {
		given()
		.body(jobj.toJSONString())
		.contentType(ContentType.JSON)
		.pathParam("id", jobj.get("id"))
		.when()
		.delete("https://jsonplaceholder.typicode.com/posts/{id}");
	
		given()
	    .contentType(ContentType.JSON)
	    .pathParam("id", jobj.get("id"))
	    .when()
	    .get("https://jsonplaceholder.typicode.com/posts/{id}").then().assertThat().statusCode(404);
	}
	
	public static JSONObject createComment() {
		JSONObject request = new JSONObject(); 
		request.put("postId", 1); 
		request.put("id",6); 
		request.put("name", "KT"); 
		request.put("email","KT.26@ymail.com");
		request.put("body","This is KT's comment");
		
		given().
		body(request.toJSONString()).
		when().
		post("https://jsonplaceholder.typicode.com/comments").
		then().log().all();
		
		boolean responseValidity = given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://jsonplaceholder.typicode.com/comments")
                .then()
                .extract().jsonPath().getString("id").equals("6");
	  
		System.out.println(responseValidity);
		return request;
	}
	
	public static void validateComment(JSONObject jobj) {
		Map<String, String> userMap = new HashMap<String,String>();
		userMap.put("id", jobj.get("id").toString());
		Response response = given()
        .contentType(ContentType.JSON)
        .params(userMap)
        .when()
        .get("https://jsonplaceholder.typicode.com/comments");
		response.then().extract().jsonPath().getString("postId").isEmpty();
		response.then().extract().jsonPath().getString("id").isEmpty();
		response.then().extract().jsonPath().getString("name").isEmpty();
		response.then().extract().jsonPath().getString("email").isEmpty();
		response.then().extract().jsonPath().getString("body").isEmpty();
	}

	public static void main(String[] args) {
		JSONObject jobUser = createUser();
		validateExistingUser(jobUser);
		updateUser(jobUser);
		deleteUser(jobUser);
		JSONObject jobPost = createNewPost();
		validatePost(jobPost);
		updatePost(jobPost);
		deletePost(jobPost);
		JSONObject jobCmnt = createComment();
		validateComment(jobCmnt);
	}

}
