import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static java.lang.Thread.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class MyJunit {
    WebDriver driver;
    @BeforeAll
    public void setuo()
    {
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }
    @Test
    public void getTitle(){
        driver.get("https://www.digitalunite.com/practice-webform-learners");
        String titleActual=driver.getTitle();
        String titleExpected="Practice webform for learners | Digital Unite";
        Assertions.assertEquals(titleExpected,titleActual);
        //driver.findElement(By.id("onetrust-accept-btn-handler")).click();

    }
    @DisplayName("Check The form is submitted Autometically")
    @Test
    public void automateForm() throws InterruptedException {
        driver.get("https://www.digitalunite.com/practice-webform-learners");
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        driver.findElement(By.id("edit-name")).sendKeys("Fahmida");
        driver.findElement(By.id("edit-number")).sendKeys("01642573316");
        driver.findElements(By.className("ui-corner-all")).get(0).click();
        scroll();
        WebElement txtCal= driver.findElement(By.id("edit-date"));
        txtCal.click();
        txtCal.sendKeys("10");
        txtCal.sendKeys("03");
        txtCal.sendKeys("2023");
        driver.findElement(By.id("edit-email")).sendKeys("fahmida@test.com");
        scroll();
        driver.findElement(By.id("edit-tell-us-a-bit-about-yourself-")).sendKeys("I am a Student");
        driver.findElement(By.id("edit-uploadocument-upload")).sendKeys(System.getProperty("user.dir")+"/src/test/resources/pic.jpg");
        sleep(3000);
        scroll();
        driver.findElement(By.id("edit-age")).click();
        driver.findElement(By.id("edit-submit")).click();


        String message=driver.findElement(By.id("block-pagetitle-2")).getText();
        Assertions.assertTrue(message.contains("Thank you for your submission"));



    }

    public void scroll(){
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)");

    }
   @AfterAll
    public void exit(){
        driver.quit();
    }
}
