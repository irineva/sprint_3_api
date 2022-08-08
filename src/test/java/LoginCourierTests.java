import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import steps.TestSteps;
import java.io.File;

public class LoginCourierTests {

    steps.TestSteps testSteps = new TestSteps();

    File courierCredsJson = new File("src/test/resources/login/courierCreds.json");
    File newCourierJson = new File("src/test/resources/courier/newCourier.json");

    @Before
    public void createeTestData() {
        testSteps.createCourierStep(newCourierJson);
    }

    @After
    public void deleteTestData() {
        testSteps.deleteTestDataStep(courierCredsJson);
    }


    @Test
    @DisplayName("Авторизация курьера")
    public void courierSuccessAuthorization() {
        Response response = testSteps.loginCourierStep(courierCredsJson);
        testSteps.compareBodyAndStatusCodeForSuccessLoginRequest(response);
    }

    @Test
    @DisplayName("Авторизация курьера без логина")
    public void courierAuthorizationWithoutLogin() {
        File courierCredsJson = new File("src/test/resources/login/courierCredsWithoutLogin.json");
        Response response = testSteps.loginCourierStep(courierCredsJson);
        testSteps.compareBodyAndStatusCodeForBadRequest(response);
    }

    @Test
    @DisplayName("Авторизация курьера без пароля")
    public void courierAuthorizationWithoutPass() {
        File courierCredsJson = new File("src/test/resources/login/courierCredsWithoutPass.json");
        Response response = testSteps.loginCourierStep(courierCredsJson);
        testSteps.compareBodyAndStatusCodeForBadRequest(response);
    }

    @Test
    @DisplayName("Авторизация курьера с неверным паролем и логином")
    public void courierAuthorizationWithIncorrectLoginAndPass() {
        File courierCredsJson = new File("src/test/resources/login/incorrectCourierCreds.json");
        Response response = testSteps.loginCourierStep(courierCredsJson);
        testSteps.compareBodyAndStatusCodeForNotFoundRequest(response);
    }

    @Test
    @DisplayName("Авторизация курьера с неверным паролем")
    public void courierAuthorizationWithIncorrectPass() {
        File courierCredsJson = new File("src/test/resources/login/сourierCredsWithIncorrectPass.json");
        Response response = testSteps.loginCourierStep(courierCredsJson);
        testSteps.compareBodyAndStatusCodeForNotFoundRequest(response);
    }

    @Test
    @DisplayName("Авторизация курьера с неверным логином")
    public void courierAuthorizationWithIncorrectLogin() {
        File courierCredsJson = new File("src/test/resources/login/сourierCredsWithIncorrectLogin.json");
        Response response = testSteps.loginCourierStep(courierCredsJson);
        testSteps.compareBodyAndStatusCodeForNotFoundRequest(response);
    }

}
