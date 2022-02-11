package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropsReader {

    public static String getProperty(String toUse) {
        try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty(toUse);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return toUse;
    }

    public static Integer getPropertyNumber(String prop) {
        return Integer.parseInt(getProperty(prop));
    }
}
