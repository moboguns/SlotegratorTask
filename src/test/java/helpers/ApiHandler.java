package helpers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Player;

import java.io.IOException;
import java.util.Base64;

import static helpers.ParametersProvider.getProperty;
import static io.restassured.RestAssured.given;

public class ApiHandler {
    private static final String API_URN = "/v2/";
    private static final String AUTHORIZATION_URN = "oauth2/token";
    private static final String PLAYERS_URN = "players";
    private static RequestSpecification requestSpecification;

    static {
        try {
            requestSpecification = new RequestSpecBuilder()
                    .setBaseUri(getProperty("URL"))
                    .setBasePath(API_URN)
                    .setContentType(ContentType.JSON)
                    .setAccept(ContentType.JSON)
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ApiHandler() {
    }

    public static String encode(String encodedString) {
        return Base64.getEncoder().encodeToString(encodedString.getBytes());
    }

    public static Response getToken(Player player) throws IOException {
        String username = getProperty("username");
        String password = getProperty("password");
        JsonObject requestParam = new JsonObject();
        String auth = username + ":" + password;

        if (player == null) {
            requestParam.addProperty("grant_type", "client_credentials");
            requestParam.addProperty("scope", "guest:default");
        } else {
            requestParam.addProperty("grant_type", "password");
            requestParam.addProperty("username", player.getUsername());
            requestParam.addProperty("password", player.getPasswordChange());
        }
        return given(requestSpecification)
                .header("Authorization",
                        "Basic " + ApiHandler.encode(auth))
                .body(requestParam)
                .when()
                .post(AUTHORIZATION_URN);
    }

    public static Response createNewPlayer(Player player, String token) {
        return given(requestSpecification)
                .auth()
                .oauth2(token)
                .body(new Gson().toJson(player))
                .when()
                .post(PLAYERS_URN);
    }


    public static Response getPlayerInfo(String token, int id) {
        return given(requestSpecification)
                .auth()
                .oauth2(token)
                .get(PLAYERS_URN + "/" + id);
    }
}
