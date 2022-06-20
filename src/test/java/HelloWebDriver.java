import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;


public class HelloWebDriver {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver =new ChromeDriver();
        driver.get("https://www.selenium.dev/");
        WebElement searchBtm= driver.findElement(By.xpath("//*[@id='docsearch']/button"));
        searchBtm.click();
        WebElement input= driver.findElement(By.id("docsearch-input"));
        input.sendKeys("selenium java");
        //input.submit();
        //driver.findElement(By.xpath( "//*[@id='docsearch-item-0']")).sendKeys(Keys.ENTER);
        Thread.sleep(20000);
        driver.quit();
    }
}
