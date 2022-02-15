package helpers;

import org.apache.commons.lang3.RandomStringUtils;

public class CustomUtils {
    public static String generateRandomString() {
        return RandomStringUtils.randomAlphabetic(7);
    }
    public static int getRandomId() {return Integer.parseInt(RandomStringUtils.randomNumeric(3));}
}
