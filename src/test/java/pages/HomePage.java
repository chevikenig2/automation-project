package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class HomePage {
    //Set up driver
    WebDriver driver;

    //Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //Locators
    By gardenSideLink= By.xpath("/html/body/section[1]/section[1]/div/div[1]/div[3]/div/div/div[2]/div[2]/div/div/div/div[1]/div[2]/div[6]/a");
    By livingRoomMainbBtn = By.xpath("//*[@id=\"buttonlist1\"]/div/div[2]/div/div/div[4]/a");

    //Functions

    //Click on garden side link
    public void clickOnGardenSideLink(){
        driver.findElement(gardenSideLink).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    //Click on Living room main button
    public void clickOnLivingRoomMainBtn(){
        driver.findElement(livingRoomMainbBtn).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
}
