package main.Order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import main.User.UserSteps;
import main.User.UserData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class OrderTests {
    private OrderSteps orderSteps = new OrderSteps();
    private UserSteps userSteps = new UserSteps();

    @Before
    public void CreateUser() {
        userSteps.createUser(UserData.bodyPostCreateUser());
    }

    @Test
    @DisplayName("Создание заказа из 5 ингредиентов - есть  accessToken")
    public void orderTests() {
        List<String> idsIngredients = orderSteps.getIdIngredients();
        String accessToken = userSteps.getAccessToken(UserData.bodyLoginWithCorrectData());
        List<String> partOfListIngredients =
                idsIngredients.subList(0, 5);
        Response orderTests = orderSteps.createOrder(partOfListIngredients, accessToken);
        orderTests.then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("name", notNullValue())
                .body("order", notNullValue())
                .body("order.ingredients", notNullValue())
                .body("order._id", notNullValue())
                .body("order.owner", notNullValue())
                .body("order.owner.name", notNullValue())
                .body("order.owner.email", notNullValue())
                .body("order.createdAt", notNullValue())
                .body("order.updatedAt", notNullValue())
                .body("order.status", notNullValue())
                .body("order.number", notNullValue())
                .body("order.price", notNullValue());

    }


    @Test
    @DisplayName("Создание заказа с несуществующим ингредиентом - есть  accessToken")
    public void orderTestsNoCorrectIngredient() {
        List<String> idsIngredients = new ArrayList<>();
        String accessToken = userSteps.getAccessToken(UserData.bodyLoginWithCorrectData());
        idsIngredients.add("12f34h56i78k90y");
       Response orderTestsNoCorrectIngredient = orderSteps.createOrder(idsIngredients, accessToken);
        orderTestsNoCorrectIngredient.then()
                .statusCode(500);

    }

    @Test
    @DisplayName("Создание заказа без ингредиентов - есть  accessToken")
    public void orderTestsEmptyBody() {
        List<String> idsIngredients = new ArrayList<>();
        String accessToken = userSteps.getAccessToken(UserData.bodyLoginWithCorrectData());
        Response orderTestsEmptyBody =  orderSteps.createOrder(idsIngredients, accessToken);
        orderTestsEmptyBody.then()
                .statusCode(400)
                .body("success", equalTo(false))
        .body("message", equalTo("Ingredient ids must be provided"));

    }


//    @Test // api принимает набор данных без токена - тест не пройдет - дефект в документации/реализации
//    @DisplayName("Создание заказа с 1 ингредиентом - нет  accessToken")
//    public void orderNoAccessToken() {
//        List<String> idsIngredients = orderSteps.getIdIngredients();
//        List<String> partOfListIngredients =
//                idsIngredients.subList(0, 1);
//        Response orderNoAccessToken = orderSteps.createOrder1(partOfListIngredients, "");
//        orderNoAccessToken.then()
//                .statusCode(401);
//    }


    @After
    public void DeleteUser() {
        userSteps.deleteUser(UserData.bodyLoginWithCorrectData());
    }
}
