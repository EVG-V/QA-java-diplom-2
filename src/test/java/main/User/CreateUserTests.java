package main.User;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import static main.User.UserData.bodyPostWithEmptyName;
import static main.User.UserData.bodyPostWithEmptyPassword;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreateUserTests {
    private UserSteps userSteps = new UserSteps();

    @Test
    @DisplayName("Создание уникального пользователя - проверка кода и содержимого ответа")
    public void createUser() {
        Response createUser = userSteps.createUser(UserData.bodyPostCreateUser());
        createUser.then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("user.email", equalTo("volkov_2025@ya.ru"))
                .body("user.name", equalTo("Volk"))
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue());
        userSteps.deleteUser(UserData.bodyLoginWithCorrectData());
    }


    @Test
    @DisplayName("Проверка невозможности создания 2 одинаковых пользователей - проверка кода и содержимого ответа")
    public void createUsersWithExistingEmail() {
        userSteps.createUser(UserData.bodyLoginWithCorrectData());
        Response createUser = userSteps.createUser(UserData.bodyLoginWithCorrectData());
        createUser.then()
                .statusCode(403)
                .body("success", equalTo(false))
                .body("message", equalTo("User already exists"));
        userSteps.deleteUser(UserData.bodyLoginWithCorrectData());
    }

    @Test
    @DisplayName("Создание пользователя с пустым e-mail")
    public void shouldNotCreateUserWithEmptyEmail() {
        Response createUser = userSteps.createUserWithEmptyEmail(UserData.bodyPostWithEmptyEmail());
        createUser.then()
                .statusCode(403)
                .body("success", equalTo(false))
                .body("message", equalTo("Email, password and name are required fields"));
    }

    @Test
    @DisplayName("Создание пользователя с пустым паролем")
    public void shouldNotCreateUserWithEmptyPassword() {
        Response createUser = userSteps.createUserWithEmptyPassword(bodyPostWithEmptyPassword());
        createUser.then()
                .statusCode(403)
                .body("success", equalTo(false))
                .body("message", equalTo("Email, password and name are required fields"));
    }

    @Test
    @DisplayName("Создание пользователя с пустым именем")
    public void shouldNotCreateUserWithEmptyName() {
        Response createUser = userSteps.createUserWithEmptyName(bodyPostWithEmptyName());
        createUser.then()
                .statusCode(403)
                .body("success", equalTo(false))
                .body("message", equalTo("Email, password and name are required fields"));
    }
}








