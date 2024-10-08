package api.endpoints;

import static io.restassured.RestAssured.given;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONTokener;

import api.path.Routes;
import api.payload.User;
import io.restassured.response.Response;

public class UserEndpoints {
	
	public static Response createUser(User uspayload)
	{
		Response response = given()
				.contentType("application/json")
				.accept("application/json")
				.body(uspayload)
		
		.when()
			.post(Routes.user_post_url);
		
		return response;
	}
	
	
	public static Response readUser(String userName)
	{
		Response response = given()
				.pathParam("username", userName)
		
		.when()
			.get(Routes.user_get_url);
		
		return response;
	}
	
	
	public static Response updateUser(User payload, String userName)
	{
		Response response = given()
				.accept("application/json")
				.contentType("application/json")
				.body(payload)
				.pathParam("username", userName)
		
		.when()
			.put(Routes.user_put_url);
		
		return response;
	}
	
	
	public static Response deleteUser(String userName)
	{
		Response response = given()
				.pathParam("username", userName)
		
		.when()
			.delete(Routes.user_delete_url);
		
		return response;
	}

	public static Response createUsers(String filePath) throws FileNotFoundException
	{	
		File f = new File (filePath);
		FileReader fr = new FileReader(f);
		JSONTokener jt = new JSONTokener(fr);
		JSONArray data = new JSONArray(jt);
		
		Response response = given()
			.contentType("application/json")
			.body(data.toString())
		
		.when()
			.post(Routes.user_post_url1);
		
		return response;
	}
	
	public static Response loginUser(String userName, String password)
	{
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("username", userName);
		data.put("password", password);

		Response response = given()
			.queryParam("api_key", "special-key")
			.contentType("application/json")
			.body(data)

		.when()
			.get(Routes.user_get_url1); 
		
		return response;
	}
	
	public static Response logoutUser()
	{
		Response response = given()
				
		.when()
			.get(Routes.user_get_url2); 

		return response;
	}
	



}
