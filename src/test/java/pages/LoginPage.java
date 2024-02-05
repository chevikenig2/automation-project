package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;
    By emailAddress= By.id("EmailOrAccountNumber");
    By password=By.id("Password");
    By signInBtn = By.id("SignInNow");
    public void enterEmailAddress(String email){
        driver.findElement(emailAddress).sendKeys(email);
    }
    public void enterPassword(String pass){
        driver.findElement(password).sendKeys(pass);
    }
    public void clickSignInBtn(){
        driver.findElement(signInBtn).click();
    }


}
