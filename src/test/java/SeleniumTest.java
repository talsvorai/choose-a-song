import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.*;

public class SeleniumTest {

    @Test
    public void testSongTitleClick() {
        // Set the path to your WebDriver (e.g., ChromeDriver)
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        // Set Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run Chrome in headless mode (no UI)
        options.addArguments("--no-sandbox"); // Disable sandbox (necessary for some environments like Docker)
        options.addArguments("--disable-dev-shm-usage"); // Disable /dev/shm usage, which can help with low memory environments
        options.addArguments("--remote-debugging-port=9222"); // Optional: Debugging (can be useful in CI environments)

        // Initialize WebDriver with Chrome options
        WebDriver driver = new ChromeDriver(options);

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