package praktikum;
import io.restassured.response.Response;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;


public abstract class BaseHTTPS {

        private final RequestSpecification baseRequestSpec = new RequestSpecBuilder()
                .setBaseUri("https://stellarburgers.nomoreparties.site")
                .addHeader("Content-type", "application/json")
                .setRelaxedHTTPSValidation()
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new ErrorLoggingFilter())
                .build();

// POST без токена
        protected Response doPostRequest(String path, Object body) {
            return given()
                    .spec(baseRequestSpec)
                    .body(body)
                    .post(path)
                    .thenReturn();
        }

//POST c токеном
        protected Response doPostRequestWithToken(String path, Object body, String accessToken) {
            return given()
                    .spec(baseRequestSpec)
                    .header("Authorization",  accessToken)
                    .body(body)
                    .post(path)
                    .thenReturn();
        }


       protected Response doDeleteRequest(String path, String accessToken) {
           return given()
                   .spec(baseRequestSpec)
                   .header("Authorization",  accessToken)
                   .delete(path)
                   .thenReturn();
       }
// GET без токена
    protected Response doGetRequest(String path) {
        return given()
                .spec(baseRequestSpec)
                .get(path)
                .thenReturn();
    }

//Get c токеном
protected Response doGetRequest(String path,String accessToken) {
    return given()
            .spec(baseRequestSpec)
            .header("Authorization",  accessToken)
            .get(path)
            .thenReturn();
}

    protected Response doPutRequest(String path) {
        return given()
                .spec(baseRequestSpec)
                .put(path)
                .thenReturn();
    }

    protected Response doPatchRequest(String path, Object body, String accessToken) {
        return given()
                .spec(baseRequestSpec)
                .body(body)
                .header("Authorization", accessToken)
                .patch(path)
                .thenReturn();


    }

    }










