package Day1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

//https://mvnrepository.com/artifact/com.google.code.gson/gson/2.11.0
//https://github.com/rest-assured/rest-assured/wiki/usage#static-imports
//https://reqres.in/
public class HTTPRequests {
	int id;
	@Test(priority =1)
	void getUsers() {
		given()

		.when()
		.get("https://reqres.in/api/users?page=2")
		
		.then()
		.statusCode(200)
		.body("page", equalTo(2))
		.log().all();

	}
	@Test(priority =2)
	void creatUsers() {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("name", "Mohammad");
		data.put("job", "teck");
		
		id = given()
		.contentType("application/json")
		.body(data)
		

		.when()
		.post("https://reqres.in/api/users")
		.jsonPath().getInt("id");
		
	}
	@Test(priority =3,dependsOnMethods = {"creatUsers"})
	void updateUser() {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("name", "Mohammad");
		data.put("job", "Hossain");
		
		given()
		.contentType("application/json")
		.body(data)
		

		.when()
		.put("https://reqres.in/api/users/"+id)
		
		.then()
		.statusCode(200)
		.log().all();
	}
	@Test(priority =4)
	void deleteUser() {
		
		given()		

		.when()
		.delete("https://reqres.in/api/users/"+id)
		
		.then()
		.statusCode(204)
		.log().all();
		
		
	}
}
