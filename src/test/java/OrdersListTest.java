import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import steps.TestSteps;


public class OrdersListTest {
    TestSteps testSteps = new TestSteps();

    @After
    public void deleteTestDataAndclearCash() {
        testSteps.cancelOrderStep();
        testSteps.clearCash();
    }

    @Test
    @DisplayName("Получение списка заказов")
    public void getOrdersList() {
        Response responseList = testSteps.getListOfOrdersStep();
        testSteps.compareBodyAndStatusCodeForGetListRequest(responseList);

    }

}
