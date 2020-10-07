import org.apache.http.protocol.HTTP;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ReqresInTest {

    @Test
    public void listUsers(){
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().body()
                .statusCode(200)
                .body("page", equalTo(2))
                .body("per_page", equalTo(6))
                .body("total", equalTo(12))
                .body("total_pages", equalTo(2));
    }

    @Test
    public void singleUser(){
        given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().body()
                .statusCode(200)
                .body("data.id",equalTo(2))
                .body("ad.company",equalTo("StatusCode Weekly"));
    }

    @Test
    public void singleUserNotFound(){
        given()
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .log().body()
                .statusCode(404);
    }
    @Test
    public void listResource(){
        given()
                .when()
                .get("https://reqres.in/api/unknown")
                .then()
                .log().body()
                .statusCode(200)
                .body("data[0].id",equalTo(1))
                .body("data[0].name",equalTo("cerulean"))
                .body("data[0].color",equalTo("#98B2D1"))
                .body("data[1].id",equalTo(2));
    }
    @Test
    public void singleResource(){
        given()
                .when()
                .get("https://reqres.in/api/unknown/2")
                .then()
                .log().body()
                .statusCode(200)
                .body("data.id",equalTo(2))
                .body("data.name",equalTo("fuchsia rose"))
                .body("data.color",equalTo("#C74375"));
    }
    @Test
    public void singleResourcesNotFound(){
        given()
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .log().body()
                .statusCode(404);
    }
    @Test
    public void create(){
        given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .body("{\n" +
                        "    \"name\": \"Andrey\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}")
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().body()
                .statusCode(201);
    }
    @Test
    public void update(){
        given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .body("{\n" +
                        "    \"name\": \"Andrey\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}")
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .log().body()
                .statusCode(200);
    }
    @Test
    public void update2(){
        given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .body("{\n" +
                        "    \"name\": \"Andrey\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}")
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .log().body()
                .statusCode(200);
    }
    @Test
    public void delete(){
        given()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().body()
                .statusCode(204);
    }
}
