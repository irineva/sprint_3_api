package requests;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class BaseRequest {
    RequestSpecification requestSpecification = RestAssured.given()
            .baseUri("http://qa-scooter.praktikum-services.ru");
}
