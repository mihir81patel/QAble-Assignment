package tests;
import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class APIAutomation {
	
	/**
	 * #1 creating new user with POST
	 * request and validating the created user with GET request
	 * @return: request
	 */
	
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
		  System.out.println("User created");
		  return request;
	}
	
	/**
	 * #2 retrieving existing user using
	 * GET request and validating all
	 * the required fields
	 * @param: jobj
	 */
	
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
		System.out.println("User validated");
	}
	
	/**
	 * #3 updating the existing user using PUT
	 * request and verifying whether user is
	 * updated or not by retrieving user with GET request
	 * @param: jobj
	 */
	
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
		System.out.println("User updated");
	}
	
	/**
	 * #4 deleting the existing user by sending DELETE request and
	 * validating that user is deleted or not 
	 * by retrieving using GET request
	 * @param: jobj
	 */
	
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
		System.out.println("User deleted");
	}
	
	/**
	 * #5 creating new post for an existing user with POST
	 * request and validating the created post with GET request
	 * @return: request
	 */
	
	public static JSONObject createNewPost() {
		JSONObject request = new JSONObject(); 
		request.put("userId", 1); 
		request.put("id",101); 
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
		System.out.println("Post created");
		return request;
	}
	
	/**
	 * #6 retrieving existing post using
	 * GET request and validating all
	 * the required fields
	 * @param: jobj
	 */
	
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
		System.out.println("Post validated");
	}
	
	/**
	 * #7 updating the existing post using PUT
	 * request and verifying whether post is
	 * updated or not by retrieving the post with GET request
	 * @param: jobj
	 */
	
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
		System.out.println("Post updated");
	}
	
	/**
	 * #8 deleting the existing post by sending DELETE request and
	 * validating that post is deleted or not 
	 * by retrieving post using GET request
	 * @param: jobj
	 */
	
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
		System.out.println("Post deleted");
	}
	
	/**
	 * #9 creating new comment for an existing post with POST
	 * request and validating the created comment with GET request
	 * @return: request
	 */
	
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
		System.out.println("Comment created");
		return request;
	}
	
	/**
	 * #10 retrieving existing comment using
	 * GET request and validating all
	 * the required fields
	 * @param: jobj
	 */
	
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
		System.out.println("Comment validated");
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
