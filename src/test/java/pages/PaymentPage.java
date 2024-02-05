package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentPage {
    //set up driver
    WebDriver driver;

    //Locators
    By visa= By.xpath("/html/body/section[1]/section[1]/div/div[1]/div/div[4]/form/div[3]/ul/li[3]/div/input");
    By cardNumber =By.name("cardNumber");
    By expiryMonth=By.name("expiryDate.expiryMonth");
    By expiryYear=By.name("expiryDate.expiryYear");
    By securityCode = By.name("securityCode");
    By submitBtn=By.id("submitButton");

    //Constructor
    public PaymentPage(WebDriver driver) {
        this.driver = driver;
    }



    //Function

    // get card name , Expiration month and year , and security Code
    // the function insert them to correct fields and click on submit
    public void payWithVisa(String cardNum,String expiryMnth,String expiryYr,String securityCd) throws InterruptedException {
        driver.findElement(visa).click();
        Thread.sleep(8000);
        driver.findElement(cardNumber).sendKeys(cardNum);
        driver.findElement(expiryMonth).sendKeys(expiryMnth);
        driver.findElement(expiryYear).sendKeys(expiryYr);
        driver.findElement(securityCode).sendKeys(securityCd);
        driver.findElement(submitBtn).click();
    }

}
