package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class Banner {

    //set up driver
    WebDriver driver;


    //Locators
    By loginbtn = By.xpath("//*[@id=\"platform_modernisation_header\"]/header/div[1]/nav/div[4]/div[2]/div/a/span");
    By homeMenu = By.xpath("//*[@id=\"meganav-link-5\"]/div");
    By homeBtn= By.xpath("//*[@id=\"sec-nav-content\"]/div/div/div/section[2]/div/a/span");
    By bannerLink =By.xpath("//*[@id=\"catalogue\"]/div[2]/div[1]/ul/li[5]/a");
    By countryIcon=By.xpath("//*[@id=\"platform_modernisation_header\"]/header/div[1]/nav/div[9]/button/img");
    By hebrewBtn= By.xpath("//*[@id=\"header-country-selector-wrapper\"]/div/div[3]/div/div[4]/div/button[1]");
    By shopNowBtn = By.xpath("//*[@id=\"header-country-selector-wrapper\"]/div/div[3]/div/div[5]/button");
    By searchBox = By.id("header-big-screen-search-box");
    By searchBtn = By.xpath("//*[@id=\"header-search-form\"]/button/img");
    By countOfItem=By.xpath("/html/body/div[2]/div/section/header/div[1]/nav/div[7]/div[2]/a/div");
    By checkoutBtn=By.xpath("/html/body/div[2]/div/section/header/div[1]/nav/div[8]/div/a");
    By editBag=By.xpath("/html/body/div[2]/div/section/header/div[1]/nav/div[7]/div[2]/div/div/div[2]/div/div/div[3]/div[1]/a");
    By removeItem=By.xpath("/html/body/section[1]/section[1]/div[1]/section/div[2]/div[4]/div[2]/table/tbody/tr/td[6]/div/a");

    //constructor

    public Banner(WebDriver driver) {
        this.driver = driver;
    }


    //functions


    //Click login link
    public void clickLoginLink(){
        driver.findElement(loginbtn).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    // click on Home button
    public void clickOnHomeBtn(){
        driver.findElement(homeMenu).click();
        driver.findElement(homeBtn).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }


    // click on Banner link
    public void clickOnBannerLink(){
        driver.findElement(homeMenu).click();
        driver.findElement(bannerLink).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    // change to hebrew
    public void changeToHebrew() throws InterruptedException {
        driver.findElement(countryIcon).click();
        Thread.sleep(4000);
        driver.findElement(hebrewBtn).click();
        driver.findElement(shopNowBtn).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    //get item and search it in searchBox
    public void searchItem(String item){
        driver.findElement(searchBox).sendKeys(item);
        driver.findElement(searchBtn).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    //return the count of item in the bag
    public String verifyCountOfItems(){
        return driver.findElement(countOfItem).getText();
    }

    // click on checkout button
    public void clickOnCheckout(){
        driver.findElement(checkoutBtn).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    // clear bag from previous runs
    public void clearBag(){
        driver.findElement(countOfItem).click();
        driver.findElement(editBag).click();
        driver.findElement(removeItem).click();
    }
}
