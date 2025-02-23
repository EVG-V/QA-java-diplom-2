package main.User;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import praktikum.StellarBurgersAPI;
import praktikum.User;

public class UserSteps {

    private final StellarBurgersAPI stellarBurgersAPI = new StellarBurgersAPI();


    @Step("Создаем пользователя")
    public Response createUser(User user) {
        return stellarBurgersAPI.PostCreateUser(UserData.bodyPostCreateUser());
    }

    @Step("Создаем пользователя без указания e-mail")
    public Response createUserWithEmptyEmail(User user) {
        return stellarBurgersAPI.PostCreateUser(UserData.bodyPostWithEmptyEmail());
    }

    @Step("Создаем пользователя без указания пароля")
    public Response createUserWithEmptyPassword(User user) {
        return stellarBurgersAPI.PostCreateUser(UserData.bodyPostWithEmptyPassword());
    }

    @Step("Создаем пользователя без указания имени")
    public Response createUserWithEmptyName(User user) {
        return stellarBurgersAPI.PostCreateUser(UserData.bodyPostWithEmptyName());
    }

    @Step("Входим с корректными данными")
    public Response loginCorrectData() {
        return stellarBurgersAPI.PostAuth(UserData.bodyLoginWithCorrectData());
    }

    @Step("Входим с некорректными данными - логин и пароль")
    public Response loginUnCorrectData() {
        return stellarBurgersAPI.PostAuth(UserData.bodyLoginWithUnCorrectData());
    }

    @Step("Получаем токен авторизации")
    public String getAccessToken(User user) {
        return stellarBurgersAPI.PostAuth(user).jsonPath().getString("accessToken");
    }

    @Step("Удаляем пользователя")
    public Response deleteUser(User user) {
        String accessToken = getAccessToken(user);
        return stellarBurgersAPI.DeleteUser(accessToken);
    }

    @Step("Изменяем данные пользователя c accessToken")
    public Response ChangeUserData(User userOld, User userNew) {
        String accessToken = getAccessToken(userOld);
        return stellarBurgersAPI.ChangeAutentUser(userNew, accessToken);
    }

    @Step("Изменяем данные пользователя c accessToken")
    public Response ChangeUserDataNoToken(User userOld, User userNew) {
        String accessToken = getAccessToken(userOld);
        return stellarBurgersAPI.ChangeAutentUser(userNew, "");
    }
}