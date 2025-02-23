package main.Order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import main.User.UserSteps;
import main.User.UserData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class GetOrderTests {

    private OrderSteps orderSteps = new OrderSteps();
    private UserSteps userSteps = new UserSteps();

    @Before

    public void PrepareData() {
        userSteps.createUser(UserData.bodyPostCreateUser());
        String accessToken = userSteps.getAccessToken(UserData.bodyLoginWithCorrectData());
        List<String> idsIngredients = orderSteps.getIdIngredients();
        List<String> partOfListIngredients = idsIngredients.subList(0, 5);
        orderSteps.createOrder(partOfListIngredients, accessToken);
    }

    @Test
    @DisplayName("Получение заказов пользователя - есть  accessToken")
    public void GetOrders() {
        String accessToken = userSteps.getAccessToken(UserData.bodyLoginWithCorrectData());
        Response GetOrders = orderSteps.GetOrders(accessToken);
        GetOrders.then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("orders", notNullValue())
                .body("orders[0].ingredients", notNullValue())
                .body("orders[0].status", notNullValue())
                .body("orders[0].name", notNullValue())
                .body("orders[0].createdAt", notNullValue())
                .body("orders[0].updatedAt", notNullValue())
                .body("orders[0].number", notNullValue())
                .body("total", notNullValue())
                .body("totalToday", notNullValue());
    }

    @Test
    @DisplayName("Получение заказов пользователя - нет accessToken")
    public void GetOrdersNoAut() {
        Response GetOrdersNoAut = orderSteps.GetOrders("");
        GetOrdersNoAut.then()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"));
    }

    @After
    public void DeleteUser() {
        userSteps.deleteUser(UserData.bodyLoginWithCorrectData());
    }
}
