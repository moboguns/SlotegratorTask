package models;

import com.google.gson.annotations.SerializedName;
import io.cucumber.messages.internal.com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class Player {
    @NonNull
    String username;
    @NonNull
    @SerializedName(value = "password_change")
    String passwordChange;
    @NonNull
    @SerializedName(value = "password_repeat")
    String repeatPassword;
    @NonNull
    String email;
    String name;
    String surname;
    @JsonProperty("currency_code")
    String currencyCode;
}
