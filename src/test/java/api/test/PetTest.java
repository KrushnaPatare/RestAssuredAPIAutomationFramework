package api.test;


import java.io.File;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import api.endpoints.PetEndpoints;
import api.payload.Pet;
import api.utilities.DataProviders;
import api.utilities.PojoSetter;
import io.restassured.response.Response;

@Listeners(api.utilities.ExtentReportManager.class)
public class PetTest {
	
	
	
	@BeforeClass
	public void setUpData( ) throws IOException
	{
		
	}

	
	@Test(priority = 1,dataProvider = "PetData", dataProviderClass = DataProviders.class)
	public void testAddNewPet(String petId, String categoryId, String categoryName, String petName, String petPhotoUrl1, String petPhotoUrl2, String tagId, String tagName, String petStatus) throws JsonProcessingException 
	{
		Pet pet = PojoSetter.newPetData(petId, categoryId, categoryName, petName, petPhotoUrl1, petPhotoUrl2, tagId, tagName, petStatus);

		Response response = PetEndpoints.addNewPet(pet);
		response.then().log().status().and().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	@Test(priority = 2, dataProvider = "UpdatedPetData", dataProviderClass = DataProviders.class)
	public void testUpdateExistingPet(String petId, String categoryId, String categoryName, String petName, String petPhotoUrl1, String petPhotoUrl2, String tagId, String tagName, String petStatus) 
	{
		Pet pet = PojoSetter.updatePetData(petId, categoryId, categoryName, petName, petPhotoUrl1, petPhotoUrl2, tagId, tagName, petStatus );
		Response response = PetEndpoints.updateExistingPet(pet);
		response.then().log().status().and().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
	
	}
	
	@Test(priority = 3)
	public void testFindPetByStatus() 
	{
		String status = "pending";
		Response response = PetEndpoints.findPetByStatus(status);
		response.then().log().status().and().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 4)
	public void testFindPetByTags() 
	{
		String tagName = "Red";
		Response response = PetEndpoints.findPetByTags(tagName);
		response.then().log().status().and().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 5, dataProvider = "PetData", dataProviderClass = DataProviders.class)
	public void testFindPet(String petId, String categoryId, String categoryName, String petName, String petPhotoUrl1, String petPhotoUrl2, String tagId, String tagName, String petStatus) 
	{
		Pet pet = PojoSetter.newPetData(petId, categoryId, categoryName, petName, petPhotoUrl1, petPhotoUrl2, tagId, tagName, petStatus);
		PetEndpoints.addNewPet(pet);
		Response response = PetEndpoints.findPet(petId);
		response.then().log().status().and().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 6)
	public void testUpdatePet() 
	{ 
		String petId = "3";
		String petName = "Luna";
		String status = "pending";
		Response res = PetEndpoints.updatePet(petId, petName, status);
		res.then().log().status().and().log().body();
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	@Test(priority = 7)
	public void testUploadPetImage() 
	{ 
		String petId = "3";
		String additionalMetaData = "Google Image";
		File myFile = new File("C:\\Users\\Admin\\Documents\\store.json");
		Response res = PetEndpoints.uploadPetImage(petId, additionalMetaData, myFile);
		res.then().log().status().and().log().body();
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	
	@Test(priority = 8)
	public void testDeletePet() 
	{
		String petId = "2";
		String api_key = "special-key";
		Response response = PetEndpoints.deletePet(petId, api_key);
		response.then().log().status().and().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
