package api.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserClient {
    @Step("Зарегистрировать пользователя")
    public Response newUserRegistration(String bodyReg) {
        return given()
                .header("Content-type", "application/json")
                .body(bodyReg)
                .post("https://stellarburgers.nomoreparties.site/api/auth/register");
    }

    @Step("Авторизоваться под пользователем")
    public Response userAuthorization(String bodyAuth) {
        return given()
                .header("Content-type", "application/json")
                .body(bodyAuth)
                .post("https://stellarburgers.nomoreparties.site/api/auth/login");
    }

    @Step("Удалить созданного пользователя")
    public  void deleteCreatedUser(String accessToken) {
        given()
                .header("Authorization", accessToken)
                .header("Content-type", "application/json")
                .delete("https://stellarburgers.nomoreparties.site/api/auth/user");
    }

}
