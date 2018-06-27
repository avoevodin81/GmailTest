package utils.helpers;

import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetScreenshot {
    public static String getScreenshot(WebDriver driver) {
        File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss_a_zzz");
        String fileName = formatForDateNow.format(dateNow) + ".png";
        try {
            Files.copy(tempFile, new File("src/screenshots/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }
}
