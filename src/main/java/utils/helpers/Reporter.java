package utils.helpers;

public class Reporter {
    public static void log(String message) {
        org.testng.Reporter.log(message + "<br>");
        System.out.println(message);
    }
}
