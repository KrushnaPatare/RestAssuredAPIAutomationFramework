package api.endpoints;

import static io.restassured.RestAssured.given;

import java.io.File;

import api.path.Routes;
import api.payload.Pet;
import io.restassured.response.Response;

public class PetEndpoints {
	
	public static Response addNewPet(Pet petload)
	{
		Response response = given().accept("application/json").contentType("application/json")
				.body(petload)
				.when().post(Routes.pet_post_url);
		return response;
	}

	public static Response updateExistingPet(Pet petload)
	{
		Response response = given().accept("application/json").contentType("application/json")
				.body(petload)
				.when().put(Routes.pet_put_url);
		return response;
	}
	
	public static Response findPetByStatus(String status)
	{
		Response response = given()
				.queryParam("status", status)
			.when()
				.get(Routes.pet_get_url);
		
		return response;
	}
	
	
	
	public static Response findPetByTags(String tag)
	{
		Response response = given()
				.queryParam("tags", tag)
	
			.when()
				.get(Routes.pet_get_url2);
		
		return response;
	}
	

	public static Response findPet(String petId)
	{
		Response response = given()
		        .pathParam("petId", petId)
				
			.when()
				.get(Routes.pet_get_url1);
		
		return response;
	}
	
	public static Response updatePet(String petId, String petName, String status)
	{
		Response response = given()
		        .pathParam("petId", petId)
		        .formParam("name", petName)
		        .formParam("status", status)
				
			.when()
				.post(Routes.pet_post_url1);
		
		return response;
	}
	
	public static Response uploadPetImage(String petId, String additionalMetadata, File file)
	{
		Response response = given()
				.pathParam("petId", petId)
			    .formParam("additionalMetadata", additionalMetadata)
				.multiPart("file", file) //this is because in postman in body , form-data radio butoon is selected
				.contentType("multipart/form-data") //this content type of any file.
			
				
			.when()
				.post(Routes.pet_post_url2);
		
		return response;
	}
	
	
	
	public static Response deletePet(String petId, String api_key)
	{
		Response response = given()
				.header("api_key", api_key)
		        .pathParam("petId", petId)
				
			.when()
				.delete(Routes.pet_delete_url);
		
		return response;
	}

}
