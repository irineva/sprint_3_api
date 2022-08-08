import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import steps.TestSteps;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsNull.notNullValue;

public class CreateOrderTests{

    TestSteps testSteps = new TestSteps();
    File blackScooterOrderJson = new File("src/test/resources/order/blackScooterOrder.json");
    File greyScooterOrderJson = new File("src/test/resources/order/greyScooterOrder.json");
    File allColorScooterOrderJson = new File("src/test/resources/order/allColorScooterOrder.json");
    File noColorScooterOrderJson = new File("src/test/resources/order/noColorScooterOrder.json");

    @After
    public void deleteTestDataAnClearCash() {
        testSteps.cancelOrderStep();
        testSteps.clearCash();
    }

    @Test
    @DisplayName("Создание заказа на черный скутер")
    public void backScooterOrderCreate() {
        Response response = testSteps.createOrderStep(blackScooterOrderJson);
        testSteps.compareBodyAndStatusCodeForCreatedOrderRequest(response);
    }

    @Test
    @DisplayName("Создание заказа на серый скутер")
    public void greyScooterOrderCreate() {
        Response response = testSteps.createOrderStep(greyScooterOrderJson);
        testSteps.compareBodyAndStatusCodeForCreatedOrderRequest(response);
    }

    @Test
    @DisplayName("Создание заказа на скутер любого цвета")
    public void anyColorScooterOrderCreate() {
        Response response = testSteps.createOrderStep(allColorScooterOrderJson);
        testSteps.compareBodyAndStatusCodeForCreatedOrderRequest(response);
    }

    @Test
    @DisplayName("Создание заказа на скутер без указания цвета")
    public void noColorScooterOrderCreate() {
        Response response = testSteps.createOrderStep(noColorScooterOrderJson);
        testSteps.compareBodyAndStatusCodeForCreatedOrderRequest(response);
    }

}
