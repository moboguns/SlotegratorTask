package models;

import lombok.Data;

@Data
public class PlayerInfo {
    int id;
    String countryId;
    String timezoneId;
    String username;
    String email;
    String name;
    String surname;
    String gender;
    String phoneNumber;
    String birthdate;
    boolean bonuses_allowed;
    boolean isVerified;
}
