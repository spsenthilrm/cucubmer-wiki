package com.automationrhapsody.cucumber.parallel.tests.wikipedia;

import java.io.File;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseSteps {

    private static final String WEB_DRIVER_FOLDER = "webdrivers";

    protected WebDriver driver;

    protected void startWebDriver() {
        Properties props = System.getProperties();
        System.out.println("DRIVER PATH ::: "+driversFolder(new File("").getAbsolutePath()) 
        + "chromedriver-80-win.exe");
        props.setProperty("webdriver.chrome.driver",
            driversFolder(new File("").getAbsolutePath()) 
            + "chromedriver-80-win.exe");
        //driver = new FirefoxDriver();
        driver = new ChromeDriver();
        //driver.navigate().to("https://en.wikipedia.org");
        driver.navigate().to("https://en.wikipedia.org/wiki/Main_Page");
        
    }

    protected void stopWebDriver() {
       if (driver != null) {
    	   driver.quit();
       }
    }

    protected void wait(int timeOutInSeconds) {
        try {
            Thread.sleep(timeOutInSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String driversFolder(String path) {
        File file = new File(path);
        for (String item : file.list()) {
            if (WEB_DRIVER_FOLDER.equals(item)) {
                return file.getAbsolutePath() + "/" + WEB_DRIVER_FOLDER + "/";
            }
        }
        return driversFolder(file.getParent());
    }
}