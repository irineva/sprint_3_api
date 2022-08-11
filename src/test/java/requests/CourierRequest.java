package requests;

import io.restassured.response.Response;
import requests.testsData.NewCourierData;

import java.io.File;

import static io.restassured.RestAssured.given;

public class CourierRequest extends BaseRequest {

    private final static String COURIER_PATH = "/api/v1/courier/";

    public Response createCourier(NewCourierData newCourier) {
        return
                given()
                        .spec(requestSpecification)
                        .header("Content-type", "application/json")
                        .and()
                        .body(newCourier)
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

    public Response loginCourier1(Object courierLoginData) {
        return given()
                .spec(requestSpecification)
                .header("Content-type", "application/json")
                .and()
                .body(courierLoginData)
                .when()
                .post(COURIER_PATH + "login");
    }

    public void deleteTestData(Object courierLoginData) {
        int courierId;
        Response response = given()
                .spec(requestSpecification)
                .header("Content-type", "application/json")
                .and()
                .body(courierLoginData)
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
