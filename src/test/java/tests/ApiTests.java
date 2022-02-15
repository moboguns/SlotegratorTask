package tests;

import helpers.ApiHandler;
import helpers.CustomUtils;
import models.Player;
import models.PlayerInfo;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.Currency;
import java.util.Locale;

import static org.hamcrest.Matchers.notNullValue;

public class ApiTests {
    private Player owner;
    private final Player guest = null;

    @BeforeMethod
    public void newPlayerGenerate() {
        owner = getNewPlayer();
    }

    @Test(description = "Test for guest token")
    public final void guestTokenTest() throws IOException {
        ApiHandler
                .getToken(guest)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("access_token", notNullValue());
    }

    @Test(description = "Player registration test")
    public final void playerRegistrationTest() throws IOException {
        String token =
                ApiHandler.getToken(guest)
                        .jsonPath()
                        .get("access_token");

        PlayerInfo playerInfo =
                ApiHandler
                        .createNewPlayer(owner, token)
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.SC_CREATED)
                        .extract()
                        .as(PlayerInfo.class);

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(owner.getUsername(), playerInfo.getUsername());
        softAssert.assertEquals(owner.getEmail(), playerInfo.getEmail());
        softAssert.assertEquals(owner.getName(), playerInfo.getName());
        softAssert.assertEquals(owner.getSurname(), playerInfo.getSurname());

        softAssert.assertAll();
    }

    @Test(description = "Get User Token Test")
    public void userTokenTest() throws IOException {
        String token =
                ApiHandler.getToken(guest)
                        .jsonPath()
                        .get("access_token");

        ApiHandler.createNewPlayer(owner, token);

        ApiHandler
                .getToken(owner)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("access_token", notNullValue());
    }

    @Test(description = "Player Info Test", dataProvider = "forPlayerInfoTest")
    public void playerInfoTest(boolean isOwnerId) throws IOException {
        String guestToken =
                ApiHandler.getToken(guest)
                        .jsonPath()
                        .get("access_token");

        PlayerInfo expectedInfo =
                ApiHandler
                        .createNewPlayer(owner, guestToken)
                        .as(PlayerInfo.class);

        String userToken =
                ApiHandler.getToken(owner)
                        .jsonPath()
                        .get("access_token");

        int id = isOwnerId ? expectedInfo.getId() : CustomUtils.getRandomId();
        int expectedStatus = isOwnerId ? HttpStatus.SC_OK : HttpStatus.SC_NOT_FOUND;

        PlayerInfo actualInfo =
                ApiHandler
                        .getPlayerInfo(userToken, id)
                        .then()
                        .assertThat()
                        .statusCode(expectedStatus)
                        .extract()
                        .as(PlayerInfo.class);

        if (isOwnerId) {
            Assert.assertEquals(actualInfo, expectedInfo);
        } else
            Assert.assertNotEquals(actualInfo, expectedInfo);
    }

    @DataProvider(name = "forPlayerInfoTest")
    private Object[][] forPlayerInfoTest() {
        return new Object[][]{{true}, {false}};
    }

    private Player getNewPlayer() {
        String password = CustomUtils.generateRandomString();
        return Player
                .builder()
                .username(CustomUtils.generateRandomString())
                .passwordChange(ApiHandler.encode(password))
                .repeatPassword(ApiHandler.encode(password))
                .email(CustomUtils.generateRandomString() + "@gmail.com")
                .name("Petr")
                .surname("Petrov")
                .currencyCode(Currency.getInstance(Locale.ITALY).getCurrencyCode())
                .build();
    }
}
