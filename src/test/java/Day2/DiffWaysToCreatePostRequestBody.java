package Day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import io.restassured.internal.support.FileReader;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.util.HashMap;

public class DiffWaysToCreatePostRequestBody {
	int id;

	@Test(priority = 1)
	void testPostUsingHashMap() {
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("name", "Scott");
		data.put("location", "France");
		data.put("phone", "123456");
		String courseArr[] = { "C", "C++" };
		data.put("courses", courseArr);

		given().contentType("application/json").body(data)

				.when().post("https://reqres.in/api/users")

				.then().statusCode(201).body("name", equalTo("Scott")).body("location", equalTo("France"))
				.body("phone", equalTo("123456")).body("courses[0]", equalTo("C")).body("courses[1]", equalTo("C++"))
				.header("Content-Type", "application/json; charset=utf-8").log().all();
	}
	@Test(priority = 1)
	void testPostUsingOrgJson() {
		JSONObject data = new JSONObject();
		data.put("name", "Scott");
		data.put("location", "France");
		data.put("phone", "123456");
		String courseArr[] = { "C", "C++" };
		data.put("courses", courseArr);

		given().contentType("application/json").body(data.toString())

				.when().post("https://reqres.in/api/users")

				.then().statusCode(201).body("name", equalTo("Scott")).body("location", equalTo("France"))
				.body("phone", equalTo("123456")).body("courses[0]", equalTo("C")).body("courses[1]", equalTo("C++"))
				.header("Content-Type", "application/json; charset=utf-8").log().all();
	}
	@Test(priority = 1)
	void testPostUsing() {
		Pojo_PostRequest data = new Pojo_PostRequest();
		
		data.setName("Scott");
		data.setLocation("France");
		data.setPhone("123456");
		String courseArr[] = { "C", "C++" };
		data.setCourses( courseArr);

		given().contentType("application/json").body(data)

				.when().post("https://reqres.in/api/users")

				.then().statusCode(201).body("name", equalTo("Scott")).body("location", equalTo("France"))
				.body("phone", equalTo("123456")).body("courses[0]", equalTo("C")).body("courses[1]", equalTo("C++"))
				.header("Content-Type", "application/json; charset=utf-8").log().all();
	}
//	@Test(priority = 1)
//	void testPostusingExternalJsonFile() throws FileNotFoundException{
//		File f = new File(".\\body.json");
		
//		FileReader fr = new FileReader(f);
//		
//		JSONTokener jt = new JSONTokener(fr);
//		
//		JSONObject data = new JSONObject(jt);
		

//		given().contentType("application/json").body(data.toString())

//				.when().post("https://reqres.in/api/users")
//
//				.then().statusCode(201).body("name", equalTo("Scott")).body("location", equalTo("France"))
//				.body("phone", equalTo("123456")).body("courses[0]", equalTo("C")).body("courses[1]", equalTo("C++"))
//				.header("Content-Type", "application/json; charset=utf-8").log().all();
//	}
//	@Test(priority=2)
//	void testDelete() {
//		
//		given()		
//
//		.when()
//		.delete("https://reqres.in/api/users/"+id)
//		
//		.then()
//		.statusCode(204)
//		.log().all();
//	}
}
