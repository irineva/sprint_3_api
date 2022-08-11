import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import requests.testsData.CourierCredsData;
import requests.testsData.NewCourierData;
import steps.TestSteps;

public class LoginCourierTests {

    static String login = "irineva";
    static String password = "1234";
    static String firstName = "kate";
    static String incorrectLogin = "чсм";
    static String incorrectPassword = "чсм";

    steps.TestSteps testSteps = new TestSteps();
    CourierCredsData courierLoginData = new CourierCredsData(login, password);


    @Before
    public void createTestData() {
        testSteps.createCourierStep(new NewCourierData(login, password, firstName));
    }

    @After
    public void deleteTestData() {
        testSteps.deleteTestDataStep(courierLoginData);
    }


    @Test
    @DisplayName("Авторизация курьера")
    public void courierSuccessAuthorization() {
        CourierCredsData courierLoginData = new CourierCredsData(login, password);
        Response response = testSteps.loginCourierStep(courierLoginData);
        testSteps.compareBodyAndStatusCodeForSuccessLoginRequest(response);
    }

    @Test
    @DisplayName("Авторизация курьера без логина")
    public void courierAuthorizationWithoutLogin() {
        CourierCredsData courierWithoutLogin = new CourierCredsData("",password);
        Response response = testSteps.loginCourierStep(courierWithoutLogin);
        testSteps.compareBodyAndStatusCodeForBadRequest(response);
    }

    @Test
    @DisplayName("Авторизация курьера без пароля")
    public void courierAuthorizationWithoutPass() {
        CourierCredsData courierWithoutPass = new CourierCredsData(login, "");
        Response response = testSteps.loginCourierStep(courierWithoutPass);
        testSteps.compareBodyAndStatusCodeForBadRequest(response);
    }

    @Test
    @DisplayName("Авторизация курьера с неверным паролем и логином")
    public void courierAuthorizationWithIncorrectLoginAndPass() {
        CourierCredsData courierLoginData = new CourierCredsData(incorrectLogin, incorrectPassword);
        Response response = testSteps.loginCourierStep(courierLoginData);
        testSteps.compareBodyAndStatusCodeForNotFoundRequest(response);
    }

    @Test
    @DisplayName("Авторизация курьера с неверным паролем")
    public void courierAuthorizationWithIncorrectPass() {
        CourierCredsData courierLoginData = new CourierCredsData(login, incorrectPassword);
        Response response = testSteps.loginCourierStep(courierLoginData);
        testSteps.compareBodyAndStatusCodeForNotFoundRequest(response);
    }

    @Test
    @DisplayName("Авторизация курьера с неверным логином")
    public void courierAuthorizationWithIncorrectLogin() {
        CourierCredsData courierLoginData = new CourierCredsData(incorrectLogin, password);
        Response response = testSteps.loginCourierStep(courierLoginData);
        testSteps.compareBodyAndStatusCodeForNotFoundRequest(response);
    }

}
