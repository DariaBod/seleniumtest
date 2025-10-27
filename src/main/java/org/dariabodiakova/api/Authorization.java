package org.dariabodiakova.api;

import io.restassured.response.Response;
import lombok.Data;
import org.dariabodiakova.models.User;

@Data
public class Authorization extends BaseApiClient {

    public Response login(User user) {
        return post("/api/auth/login", user)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }
}
