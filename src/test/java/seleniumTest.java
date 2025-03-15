import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

public class SeleniumTest {
    public static void main(String[] args) {
        // Set the path to your WebDriver (e.g., ChromeDriver)
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/");

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Open the website
        driver.get("http://localhost:8080");

        // Interact with elements
        driver.findElement(By.id("songTitle")).click();

        // Close the browser
        driver.quit();
    }
}