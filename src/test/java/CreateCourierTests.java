import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import requests.testsData.NewCourierData;
import steps.TestSteps;


public class CreateCourierTests {

    static String login = "irineva";
    static String password = "1234";
    static String firstName = "kate";
    static String dublicateLogin = "meow";

    TestSteps testSteps = new TestSteps();
    NewCourierData newCourierData = new NewCourierData(login, password, firstName);


    @After
    public void deleteTestData() {
        testSteps.deleteTestDataStep(newCourierData);
    }

    @Test
    @DisplayName("Создание курьера")
    public void createNewCourierTest() {
        Response response = testSteps.createCourierStep(new NewCourierData(login, password, firstName));
        testSteps.compareBodyAndStatusCodeForCreatedCourierRequest(response);
    }

    @Test
    @DisplayName("Нельзя создать двух одинаковых курьеров")
    public void createDublicateCourier() {
        testSteps.createCourierStep(new NewCourierData(dublicateLogin, password, firstName));
        Response response = testSteps.createCourierStep(new NewCourierData(dublicateLogin, password, firstName));
        testSteps.compareMessageAndStatusCodeForConflictRequest(response);
    }

    @Test
    @DisplayName("Создание курьера без логина")
    public void createCourierWithoutLogin() {
        Response response = testSteps.createCourierStep(new NewCourierData("", password, firstName));
        testSteps.compareMessageAndStatusCodeForBadRequest(response);
    }

    @Test
    @DisplayName("Создание курьера без пароля")
    public void createCourierWithoutPass() {
        Response response = testSteps.createCourierStep(new NewCourierData(login, "", firstName));
        testSteps.compareMessageAndStatusCodeForBadRequest(response);
    }

    @Test
    @DisplayName("Создание курьера без имени")
    public void createCourierWithoutFirstName() {
        Response response = testSteps.createCourierStep(new NewCourierData(login, password, ""));
        testSteps.compareBodyAndStatusCodeForCreatedCourierRequest(response);
    }
}
