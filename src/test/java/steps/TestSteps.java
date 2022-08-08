package steps;

import java.io.File;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import requests.CourierRequest;
import requests.OrderRequest;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_CONFLICT;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class TestSteps {

    CourierRequest courierRequest = new CourierRequest();
    OrderRequest orderRequest = new OrderRequest();

    @Step("Создаем нового курьера")
    public Response createCourierStep(File file) {
        return courierRequest.createCourier(file);
    }

    @Step("Логинимся новым курьером")
    public Response loginCourierStep (File file) {
        return courierRequest.loginCourier(file);
    }

    @Step("Удаляем тестовые данные")
    public void deleteTestDataStep (File file) {
        courierRequest.deleteTestData(file);
    }

    @Step("Создаем новый заказ")
    public Response createOrderStep(File file) {
        return orderRequest.createOrder(file);
    }

    @Step("Отменяем заказ")
    public void cancelOrderStep() {
        orderRequest.cancelOrder();
    }

    @Step("Очищаем кэщ")
    public void clearCash() {
        orderRequest.clearCash();
    }


    @Step("Получаем список заказов")
    public Response getListOfOrdersStep() {
        return orderRequest.getListOfOrders();
    }

    @Step("Проверяем ответ и статус код на bad request")
    public void compareMessageAndStatusCodeForBadRequest(Response response) {
        response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(SC_BAD_REQUEST);
    }

    @Step("Проверяем тело и статус код на created request")
    public void compareBodyAndStatusCodeForCreatedCourierRequest(Response response) {
        response.then().assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(SC_CREATED);
    }

    @Step("Проверяем ответ и статус код на conflict request")
    public void compareMessageAndStatusCodeForConflictRequest(Response response) {
        response.then().assertThat().body("message",
                        equalTo("Этот логин уже используется. Попробуйте другой."))
                .and()
                .statusCode(SC_CONFLICT);
    }

    @Step("Проверяем ответ и статус код на успешный login request")
    public void compareBodyAndStatusCodeForSuccessLoginRequest(Response response) {
        response.then().assertThat().body("id", notNullValue())
                .and()
                .statusCode(SC_OK);
    }

    @Step("Compare body and status code for not found request")
    public void compareBodyAndStatusCodeForNotFoundRequest(Response response) {
        response.then().assertThat().body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(SC_NOT_FOUND);
    }

    @Step("Проверяем тело и статус код на bad request")
    public void compareBodyAndStatusCodeForBadRequest(Response response) {
        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(SC_BAD_REQUEST);
    }

    @Step("Проверяем тело и статус код на created request")
    public void compareBodyAndStatusCodeForCreatedOrderRequest(Response response) {
        response.then().assertThat().body("track", notNullValue())
                .and()
                .statusCode(SC_CREATED);
    }

    @Step("Проверяем тело и статус код на успешное получения списка")
    public void compareBodyAndStatusCodeForGetListRequest(Response response) {
        response.then().assertThat().body("orders[0]", notNullValue())
                .and()
                .statusCode(SC_OK);
    }
}
