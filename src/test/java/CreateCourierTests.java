import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName; // импорт DisplayName
import steps.TestSteps;

import java.io.File;


public class CreateCourierTests {

    TestSteps testSteps = new TestSteps();
    File newCourierJson = new File("src/test/resources/courier/newCourier.json");
    File сourierDublicateJson = new File("src/test/resources/сourierDublicate.json");
    File newCourierWithoutLoginJson = new File("src/test/resources/courier/newCourierWithoutLogin.json");
    File newCourierWithoutPassJson = new File("src/test/resources/courier/newCourierWithoutPass.json");
    File newCourierWithoutFirstNameJson = new File("src/test/resources/courier/newCourierWithoutFirstName.json");

    @After
    public void deleteTestData() {
        testSteps.deleteTestDataStep(newCourierJson);
    }

    @Test
    @DisplayName("Создание курьера")
    public void createNewCourierTest() {
        Response response = testSteps.createCourierStep(newCourierJson);
        testSteps.compareBodyAndStatusCodeForCreatedCourierRequest(response);
    }

    @Test
    @DisplayName("Нельзя создать двух одинаковых курьеров")
    public void createDublicateCourier() {
        testSteps.createCourierStep(сourierDublicateJson);
        Response response = testSteps.createCourierStep(сourierDublicateJson);
        testSteps.compareMessageAndStatusCodeForConflictRequest(response);
    }

    @Test
    @DisplayName("Создание курьера без логина")
    public void CreateCourierWithoutLogin() {
        Response response = testSteps.createCourierStep(newCourierWithoutLoginJson);
        testSteps.compareMessageAndStatusCodeForBadRequest(response);
    }

    @Test
    @DisplayName("Создание курьера без пароля")
    public void CreateCourierWithoutPass() {
        Response response = testSteps.createCourierStep(newCourierWithoutPassJson);
        testSteps.compareMessageAndStatusCodeForBadRequest(response);
    }

    @Test
    @DisplayName("Создание курьера без имени")
    public void CreateCourierWithoutFirstName() {
        Response response = testSteps.createCourierStep(newCourierWithoutFirstNameJson);
        testSteps.compareBodyAndStatusCodeForCreatedCourierRequest(response);
    }
}
