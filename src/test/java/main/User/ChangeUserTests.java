package main.User;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.equalTo;

public class ChangeUserTests {
    private UserSteps userSteps = new UserSteps();


    @Before
    public void CreateUser() {
        userSteps.createUser(UserData.bodyPostCreateUser());
    }

    @Test
    @DisplayName("Изменение данных пользователя - почта - есть accessToken")
    public void ChangeAutentUserDataEmailTest() {
        Response ChangeAutentUserDataEmailTest = userSteps.ChangeUserData(UserData.bodyLoginWithCorrectData(), UserData.bodyChangeUserDataEmail());
        ChangeAutentUserDataEmailTest.then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("user.email", equalTo("volk_volkov_2025@gmail.ru"))
                .body("user.name", equalTo("Volk"));
        userSteps.deleteUser(UserData.bodyChangeUserDataEmail());
    }


    @Test
    @DisplayName("Изменение данных пользователя - имя пользователя - есть accessToken")
    public void ChangeAutentUserDataNameTest() {
        Response ChangeAutentUserDataNameTest = userSteps.ChangeUserData(UserData.bodyLoginWithCorrectData(), UserData.bodyChangeUserDataName());
        ChangeAutentUserDataNameTest.then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("user.email", equalTo("volkov_2025@ya.ru"))
                .body("user.name", equalTo("Seryi"));
        userSteps.deleteUser(UserData.bodyChangeUserDataName());
    }


    @Test
    @DisplayName("Изменение данных пользователя - пароль пользователя - есть  accessToken")
    public void ChangeAutentUserDataPasswordTest() {
        Response ChangeAutentUserDataPasswordTest = userSteps.ChangeUserData(UserData.bodyLoginWithCorrectData(), UserData.bodyChangeUserDataPassword());
        ChangeAutentUserDataPasswordTest.then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("user.email", equalTo("volkov_2025@ya.ru"))
                .body("user.name", equalTo("Volk"));
        userSteps.deleteUser(UserData.bodyChangeUserDataPassword());
    }

    @Test
    @DisplayName("Изменение данных пользователя - все данные - нет accessToken")
    public void ChangeNoAutentUserAllDataTest() {
        Response ChangeNoAutentUserAllDataTest = userSteps.ChangeUserDataNoToken(UserData.bodyLoginWithCorrectData(),UserData.bodyChangeUserAllData());
        ChangeNoAutentUserAllDataTest.then()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"));
        userSteps.deleteUser(UserData.bodyLoginWithCorrectData());
    }

}

