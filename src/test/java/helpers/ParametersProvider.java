package helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public final class ParametersProvider {

    private final static Properties properties = new Properties();

    public static String getProperty(final String key) throws IOException {
        if (properties.isEmpty()) loadProperty();
        return properties.getProperty(key);
    }

    private static void loadProperty() throws IOException {
        String propertiesPath = "src/test/resources/conf.properties";
        InputStreamReader reader = new InputStreamReader(new FileInputStream(propertiesPath), StandardCharsets.UTF_8);
        properties.load(reader);
    }
}
