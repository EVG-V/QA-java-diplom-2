package main.User;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class AutentUserTests {
    private UserSteps userSteps = new UserSteps();

    @Before
    public void CreateUser() {
        userSteps.createUser(UserData.bodyPostCreateUser());
    }

    @Test
    @DisplayName("Вход с корректными данными")
    public void autentUserTests() {

        Response loginCorrectData = userSteps.loginCorrectData();
        loginCorrectData.then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue())
                .body("user.email", equalTo("volkov_2025@ya.ru"))
                .body("user.name", equalTo("Volk"));
    }



@Test
@DisplayName("Вход с некорректными данными")
public void badAutentUserTests() {
    Response loginCorrectData = userSteps.loginUnCorrectData();
    loginCorrectData.then()
            .statusCode(401)
            .body("success", equalTo(false))
            .body("message", equalTo("email or password are incorrect"));
}

    @After
    public void DeleteUser() {
        userSteps.deleteUser(UserData.bodyLoginWithCorrectData());
    }
}