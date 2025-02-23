package praktikum;
import io.restassured.response.Response;


public class StellarBurgersAPI extends
        BaseHTTPS {


    public static final String PATH_REG = "/api/auth/register";
    public static final String PATH_AUTH = "/api/auth/login";
    public static final String PATH_USER_DATA = "/api/auth/user";
    public static final String PATH_INGREDIENTS = "api/ingredients";
    public static final String PATH_ORDER = "/api/orders";



    public Response PostCreateUser(Object object) {
        return doPostRequest(PATH_REG, object);
    }

    public Response PostAuth(Object object) {
        return doPostRequest(PATH_AUTH, object);
    }

    public Response DeleteUser(String accessToken) {
        return doDeleteRequest(PATH_USER_DATA, accessToken);
    }

    public Response ChangeAutentUser (Object object, String accessToken) {
        return doPatchRequest(PATH_USER_DATA, object, accessToken);
    }


    public  Response getIngredients() {
        return doGetRequest(PATH_INGREDIENTS);
    }

    public  Response createOrder(Object object, String accessToken) {
        return doPostRequestWithToken(PATH_ORDER, object, accessToken);
    }

 public Response getOrders(String accessToken) {
        return doGetRequest(PATH_ORDER, accessToken);
 }
    }



