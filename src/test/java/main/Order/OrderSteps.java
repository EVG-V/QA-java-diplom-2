package main.Order;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import praktikum.StellarBurgersAPI;

import java.util.*;

public class OrderSteps {

    private final StellarBurgersAPI stellarBurgersAPI = new StellarBurgersAPI();

    @Step("Получаем id ингредиентов")
    public List<String> getIdIngredients() {
        Response getIngredients = stellarBurgersAPI.getIngredients();
        return getIngredients.jsonPath().get("data._id");
    }

    @Step("Добавляем ингредиенты в заказ c accessToken")
    public Response createOrder(Object ingredients, String accessToken) {
        Map<String, Object> orderDetails = new HashMap<>();
        orderDetails.put("ingredients", ingredients);
        orderDetails.put("accessToken", accessToken);
        return stellarBurgersAPI.createOrder(orderDetails, accessToken);
    }


    @Step("Добавляем ингредиенты в заказ без accessToken")
    public Response createOrder1(Object ingredients, String accessToken) {
        Map<String, Object> orderDetails = new HashMap<>();
        orderDetails.put("ingredients", ingredients);
        orderDetails.put("accessToken", accessToken);
        return stellarBurgersAPI.createOrder(orderDetails, "");
    }

    @Step("Получаем список заказов c accessToken")
    public Response GetOrders(String accessToken) {
        return stellarBurgersAPI.getOrders(accessToken);
    }

    @Step("Получаем список заказов без accessToken")
    public Response GetOrdersNoToken(String accessToken) {
        return stellarBurgersAPI.getOrders("");
    }
}

