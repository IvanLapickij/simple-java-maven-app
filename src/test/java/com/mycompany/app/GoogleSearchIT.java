package com.mycompany.app;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//This is an integration test for Google Search using Selenium WebDriver and JUnit 5.
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
//Selenium
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
//End Selenium



class GoogleSearchIT {


    

    @Test
    void verifyGoogleHomePageLoads() throws IOException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.google.com");

        assertTrue(driver.getTitle().contains("Google"));

        //Selenium
        // Capture screenshot on failure
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("target/screenshots/failure.png"));
        //End Selenium

        driver.quit();
    }

    
}
