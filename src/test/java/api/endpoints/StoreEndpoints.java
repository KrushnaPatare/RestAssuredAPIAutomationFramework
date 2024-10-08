package api.endpoints;

import static io.restassured.RestAssured.given;

import api.path.Routes;
import api.payload.Store;
import io.restassured.response.Response;

public class StoreEndpoints {
	

	
	public static Response placeOrderForPet(Store stpayload) 
	{
		Response response = given()
				.contentType("application/json")
				.accept("application/json")
				.body(stpayload)
				
				.when()
				.post(Routes.store_post_url);
		return response;
	}
	
	public static Response findPurchaseOrder(int orderId)
	{
		Response response = given()
				.pathParam("orderId", orderId)
				
				.when()
				.get(Routes.store_get_url);
		return response;
	}
	
	public static Response returnPetInventories()
	{
		Response response = given()
				.queryParam("api_key", "special-key")
				
				.when()
				.get(Routes.store_get_url1);
		return response;
	}
	
	public static Response deletePutchaseOrder(int orderId)
	{
		Response response = given()
				.pathParam("orderId", orderId)
				
				.when()
				.delete(Routes.store_delete_url);
		return response;
	}
	

}
