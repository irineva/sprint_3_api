import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import requests.testsData.OrderData;
import steps.TestSteps;

@RunWith(Parameterized.class)
public class CreateOrderTests {

    private final OrderData scooterOrder;
    private final TestSteps testSteps;
    static String firstName = "Naruto";
    static String lastName = "Uchiha";
    static String address = "Konoha, 142 apt.";
    static int metroStation = 4;
    static String phone = "+7 800 355 35 35";
    static int rentTime = 5;
    static String deliveryDate = "2020-06-06";
    static String comment = "Saske, come back to Konoha";
    static String[] blackColor = {"BLACK"};
    static String[] allColor = {"BLACK", "GREY"};
    static String[] greyColor = {"GREY"};
    static String[] noColor = {};


    public CreateOrderTests(OrderData scooterOrder) {
        this.scooterOrder = scooterOrder;
        this.testSteps = new TestSteps();
    }

    @Parameterized.Parameters
    public static Object[] getScooterOrderData() {
        return new Object[]{
                (new OrderData(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, blackColor )),
                (new OrderData(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, greyColor )),
                (new OrderData(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, allColor )),
                (new OrderData(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, noColor )),
        };
    }

    @After
    public void deleteTestDataAnClearCash() {
        testSteps.cancelOrderStep();
        testSteps.clearCash();
    }

    @Test
    public void scooterOrderCreate() {
        Response response = testSteps.createOrderStep(scooterOrder);
        testSteps.compareBodyAndStatusCodeForCreatedOrderRequest(response);
    }

}
