package requests;
import java.io.File;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
public class OrderRequest {
        private RequestSpecification requestSpecification = RestAssured.given()
                .baseUri("http://qa-scooter.praktikum-services.ru");

        private final static String ORDER_PATH = "/api/v1/orders";

        Integer cashId = null;

        public Response createOrder(File file) {
            Response response =
                    given()
                            .spec(requestSpecification)
                            .header("Content-type", "application/json")
                            .and()
                            .body(file)
                            .when()
                            .post(ORDER_PATH);
            saveOrderIdToCash(response);
            return response;
        }

        public void cancelOrder() {
            if (!(cashId == null)) {
                given()
                        .spec(requestSpecification)
                        .header("Content-type", "application/json")
                        .when()
                        .put(ORDER_PATH + "/cancel?track={track}", cashId);
            }
        }

        public Response getListOfOrders() {
            return
                    given()
                            .spec(requestSpecification)
                            .get(ORDER_PATH);
        }

        public void saveOrderIdToCash(Response response) {
            cashId = response.then().extract().body().path("track");
        }

        public void clearCash() {
            cashId = null;
        }
    }
