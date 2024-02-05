package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    //Set up driver
    WebDriver driver ;
    //Constructor
    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    //Locators
    By colorSelect= By.xpath("//*[@id=\"dk_container_Colour-1296978\"]");
    By colorOption3=By.xpath("//*[@id=\"dk_container_Colour-1296978\"]/div/ul/li[3]/a");
    By sizeSelect= By.id("dk_container_Size-T01-683");
    By sizeOption=By.xpath("//*[@id=\"dk_container_Size-T01-683\"]/div/ul/li[8]/a");
    By addToBagBtn=By.xpath("//*[@id=\"Style1296978\"]/section/div[4]/div[6]/div[4]/div/a[1]");

    // Functions

    // Select color of item
    public void selectColorOption(){
        driver.findElement(colorSelect).click();
        driver.findElement(colorOption3).click();
    }
    // Select size of item
    public void selectSizeOption() {
        driver.findElement(sizeSelect).click();
        driver.findElement(sizeOption).click();

    }

    // Click on add to bag
    public void clickOnAddToBag(){
        driver.findElement(addToBagBtn).click();
    }



}
