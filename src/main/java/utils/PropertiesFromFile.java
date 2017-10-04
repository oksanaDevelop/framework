package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFromFile {

    private PropertiesFromFile() {
    }

    private static Properties properties;
    private static String propertiesFile = "src/main/resources/config.properties";

    public static Properties getProperties() {
        if (properties == null) {
            try {
                FileInputStream fis = new FileInputStream(propertiesFile);
                properties = new Properties();
                properties.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties;
    }
}