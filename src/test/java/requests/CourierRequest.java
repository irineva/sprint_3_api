package requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static io.restassured.RestAssured.given;

public class CourierRequest {

    private RequestSpecification requestSpecification = RestAssured.given()
            .baseUri("http://qa-scooter.praktikum-services.ru");

    private final static String COURIER_PATH = "/api/v1/courier/";

    public Response createCourier(File file) {
        return
                given()
                        .spec(requestSpecification)
                        .header("Content-type", "application/json")
                        .and()
                        .body(file)
                        .when()
                        .post(COURIER_PATH);
    }


    public Response loginCourier(File file) {
        return given()
                .spec(requestSpecification)
                .header("Content-type", "application/json")
                .and()
                .body(file)
                .when()
                .post(COURIER_PATH + "login");
    }

    public void deleteTestData(File file) {
        int courierId;
        Response response = given()
                .spec(requestSpecification)
                .header("Content-type", "application/json")
                .and()
                .body(file)
                .when()
                .post(COURIER_PATH + "login");
        if (!(response.then().extract().body().path("id") == null)) {
            courierId = response.then().extract().body().path("id");
            given()
                    .spec(requestSpecification)
                    .delete(COURIER_PATH + courierId);
        }
    }
}
