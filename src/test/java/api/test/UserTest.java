package api.test;

import java.io.FileNotFoundException;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

@Listeners(api.utilities.ExtentReportManager.class)
public class UserTest {
	
	Faker faker;
	User uspayload;
	
	@BeforeClass
	public void setUpData()
	{
		faker = new Faker();
		uspayload = new User();
		
		uspayload.setId(faker.idNumber().hashCode());
		uspayload.setUsername(faker.name().username());
		uspayload.setPassword(faker.internet().password());
		uspayload.setFirstName(faker.name().firstName());
		uspayload.setLastName(faker.name().lastName());
		uspayload.setEmail(faker.internet().safeEmailAddress());
		uspayload.setPhone(faker.phoneNumber().cellPhone());
	}
	
	
	@Test(priority = 1)
	public void testPostUser() 
	{
		Response response = UserEndpoints.createUser(uspayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);//201
	}
	
	@Test(priority = 2)
	public void testGetUser() 
	{
		Response response = UserEndpoints.readUser(uspayload.getUsername());
		String name = response.then().log().status().and().log().body()
			.extract().path("username"); //if we use path method then restassured itself will figure out if it is xml or json path.
		System.out.println(name);
		Assert.assertEquals(response.getStatusCode(), 200);//200
		Assert.assertEquals(response.getStatusCode(), 200);//200

	}
	
	@Test(priority = 3)
	public void testPutUser() 
	{
		uspayload.setFirstName(faker.name().firstName());
		uspayload.setLastName(faker.name().lastName());
		uspayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndpoints.readUser(uspayload.getUsername());
		response.then().log().status().and().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);//200
	}

	@Test(priority = 4)
	public void testDeleteUser() 
	{
		Response response = UserEndpoints.readUser(uspayload.getUsername());
		response.then().log().status().and().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);//204
	}
	
	@Test(priority = 5)
	public void testLoginUser() 
	{
		Response response = UserEndpoints.loginUser("laboris labore officia nulla non","reprehenderit deserunt anim");
		response.then().log().status().and().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);//204
	}
	
	@Test(priority = 6)
	public void testLogoutUser() 
	{
		Response response = UserEndpoints.logoutUser();
		response.then().log().status().and().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);//204
	}
	
	@Test(priority = 7)
	public void testLoginUserTime() 
	{
		Response response = UserEndpoints.loginUser(uspayload.getUsername(),uspayload.getPassword());
		response.then().log().status().and().log().body()
			.time(Matchers.lessThan(3000L)).and().time(Matchers.greaterThan(500L));
		//Assert.assertEquals(response.getStatusCode(), 200);//204
	}
	
	@Test(priority = 8)
	public void testLogoutUserTime() 
	{
		Response response = UserEndpoints.logoutUser();
		response.then().log().status().and().log().body()
			.time(Matchers.greaterThan(500L)).and().time(Matchers.lessThan(3000L));
		//Assert.assertEquals(response.getStatusCode(), 200);//204
	}
	
	@Test(priority = 9)
	public void testCreateUsers() throws FileNotFoundException 
	{
		Response response = UserEndpoints.createUsers(".\\resources\\body.json");
		response.then().log().status().and().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);//204
	}

}
