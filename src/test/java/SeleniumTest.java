import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.*;

public class SeleniumTest {

    @Test
    public void testSongTitleClick() {
        // Set the path to your WebDriver (e.g., ChromeDriver)
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        try {
            // Open the website
            driver.get("http://localhost:8080");

            // Interact with elements
            driver.findElement(By.id("songTitle")).click();

            // Assert that the title exists
            assertNotNull(driver.findElement(By.id("songTitle")));
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}