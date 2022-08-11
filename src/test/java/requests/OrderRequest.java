package requests;

import io.restassured.response.Response;
import requests.testsData.OrderData;

import static io.restassured.RestAssured.given;
public class OrderRequest extends BaseRequest {

        private final static String ORDER_PATH = "/api/v1/orders";

        Integer cashId = null;

        public Response createOrder(OrderData orderData) {
            Response response =
                    given()
                            .spec(requestSpecification)
                            .header("Content-type", "application/json")
                            .and()
                            .body(orderData)
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
