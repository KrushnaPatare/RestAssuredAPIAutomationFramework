package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.StoreEndpoints;
import api.payload.Store;
import io.restassured.response.Response;



@Listeners(api.utilities.ExtentReportManager.class)
public class StoreTest {
	
	Faker faker;
	Store stpayload;
	
	@BeforeClass
	public void setUpData()
	{
		faker = new Faker();
		stpayload = new Store();
		
		stpayload.setId(faker.number().numberBetween(1, 10));
		stpayload.setPetId(faker.number().numberBetween(2000, 2010));
		stpayload.setQuantity(faker.number().numberBetween(20, 50));
		stpayload.setShipDate("2023-12-12T16:35:59.906Z");
		stpayload.setStatus("placed");
		stpayload.setComplete(true);
	}
	
	
	
	
	@Test(priority = 1)
	public void testPostStore() 
	{
		Response response = StoreEndpoints.placeOrderForPet(stpayload);
		response.then().log().status().and().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);//should be 201
	}
	
	@Test(priority = 2)
	public void testGetStore() 
	{
		Response response = StoreEndpoints.findPurchaseOrder(stpayload.getId());
		response.then().log().status().and().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);//200
		Assert.assertEquals(response.getHeader("content-type"),"application/json");
		Assert.assertEquals(response.jsonPath().get("status").toString(), "placed");
	}
	
	
	//authorization needed for this request = API key authentication
	@Test(priority = 3)
	public void testGetStoreInventories() 
	{	
		Response response = StoreEndpoints.returnPetInventories();
		response.then().log().status().and().log().body();
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
	
	@Test(priority = 4)
	public void testDeleteStore() 
	{
		Response response = StoreEndpoints.deletePutchaseOrder(stpayload.getId());
		response.then().log().status().and().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);//204
	}
	

}
