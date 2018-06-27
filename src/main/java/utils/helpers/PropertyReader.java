package utils.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static utils.helpers.Reporter.log;

public class PropertyReader {
    private static String fileName = "config.properties";
    private static String prop = System.getProperty("user.dir") + File.separator
            + "src" + File.separator
            + "main" + File.separator + "resources" + File.separator
            + fileName;

    public static String getPropertyValue(String key) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(new File(prop))) {
            props.load(fis);
        } catch (IOException e) {
            log("Can't load the file: " + fileName);
            e.printStackTrace();
        }
        return props.getProperty(key);
    }
}
