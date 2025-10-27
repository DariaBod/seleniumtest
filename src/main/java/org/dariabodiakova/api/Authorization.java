package org.dariabodiakova.api;

import io.restassured.response.Response;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dariabodiakova.models.User;

@Data
@EqualsAndHashCode(callSuper = true)
public class Authorization extends BaseApiClient {

    public Response login(User user) {
        return post("/api/auth/login", user)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }
}
