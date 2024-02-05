package test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import pages.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCases {
    public static ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("index.html");
    public static ExtentReports extentReports=new ExtentReports() ;
    String currentTime = "";
    private static WebDriver driver ;
    @BeforeClass
    public static void beforeClass() throws ParserConfigurationException, IOException, SAXException {
        // System.setProperty("webdriver.chrome.driver", "C:\\Users\\cluria\\Desktop\\automation\\Driver\\chromedriver119.exe");

        // Set up the driver and ExtentReports
        setWebDriver(getData("BROWSER"));
        driver.get(getData("URL"));
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        extentReports.attachReporter(extentSparkReporter);
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentSparkReporter.config().setEncoding("utf-8");
        extentSparkReporter.config().setDocumentTitle("my report");
    }
    @Test
    public  void aSignNextSite() throws ParserConfigurationException, IOException, SAXException, InterruptedException {
        ExtentTest signInNext=extentReports.createTest("Sign In Next site");
        signInNext.info("Open Next site");
        Banner banner =new Banner(driver);
        banner.clickLoginLink();//click sign in icon
        LoginPage loginPage =new LoginPage(driver);
        loginPage.enterEmailAddress(getData("EMAIL"));// enter email
        loginPage.enterPassword(getData("PASS"));//enter password
        loginPage.clickSignInBtn();//click login
        Thread.sleep(4000);
        assertTest(Constants.ORDER_TRACKING_URL,driver.getCurrentUrl(),signInNext,"Sign in Next site");//verify if sign in  passed
        driver.navigate().to(getData("URL"));

    }

    @Test
    public void bHomeCategory() throws ParserConfigurationException, IOException, SAXException, InterruptedException {
        ExtentTest homeCategory=extentReports.createTest("Home category sanity test");
        homeCategory.info("sanity test on home category:");
        Banner banner=new Banner(driver);

        banner.clickOnHomeBtn();//Enter to home category
        assertTest(Constants.HOME_URL,driver.getCurrentUrl(),homeCategory,"Open Home Category");//verify link was working

        HomePage homePage=new HomePage(driver);

        homePage.clickOnGardenSideLink();//Click on side link
        assertTest(Constants.GARDEN_URL,driver.getCurrentUrl(),homeCategory,"Open Side link (Garden)");//verify link was working
        banner.clickOnHomeBtn();//return to home

        homePage.clickOnLivingRoomMainBtn();//Click on Main Link
        assertTest(Constants.LIVING_ROOM_URL,driver.getCurrentUrl(),homeCategory,"Open Category link (Living Room)");//verify link was working
        banner.clickOnHomeBtn();// return to home

        banner.clickOnBannerLink();//Click on Banner Link
        assertTest(Constants.MIRRORS_URL,driver.getCurrentUrl(),homeCategory,"Open Banner link (Mirrors)");//verify link was working
        banner.clickOnHomeBtn();//return to home

        banner.changeToHebrew(); //Change to hebrew
        assertTest(Constants.HEBREW_URL,driver.getCurrentUrl(),homeCategory,"Change to Hebrew");//verify change to hebrew was working
        if(!banner.verifyCountOfItems().equals("0"))//clear bag from previous runs
            banner.clearBag();

        banner.searchItem(getData("ITEM")); //Search for specific item
        assertTest(Constants.ITEM_URL,driver.getCurrentUrl(),homeCategory,"Search for item");//verify search is working


    }
    @Test
    public void cProductSanity() throws ParserConfigurationException, IOException, SAXException, InterruptedException {
        ExtentTest productSanity=extentReports.createTest("Product page sanity test");
        productSanity.info("Sanity test on product page");
        Banner banner=new Banner(driver);
        ProductPage productPage=new ProductPage(driver);
        productPage.selectColorOption(); // Select Color
        productPage.selectSizeOption();  //Select Size
        productPage.clickOnAddToBag();//Add to Chart
        productPage.clickOnAddToBag();//Add again to Chart
        Thread.sleep(4000);
        assertTest(banner.verifyCountOfItems(),"2",productSanity,"Add 2 items to the bag");//Verify add to bag is working
        banner.clickOnCheckout();//Click on checkout
        assertTest(Constants.CHECKOUT_URL,driver.getCurrentUrl(),productSanity,"Click on Checkout");//Verify checkout link
    }
    @Test
    public void dPaymentPage() throws ParserConfigurationException, IOException, SAXException, InterruptedException {
        ExtentTest paymentSanity=extentReports.createTest("Payment page sanity");
        paymentSanity.info("Sanity test on Payment page");
        if(driver.getCurrentUrl().equals(Constants.CHECKOUT_URL)) //if payment page is opened
        {
            PaymentPage paymentPage = new PaymentPage(driver);
            paymentPage.payWithVisa(getData("CARD_NUMBER"), getData("EX_MONTH"), getData("EX_YEAR"), getData("SEC_CODE"));// pay by Visa with these values
            assertTest("הזן מספר כרטיס תקין", driver.findElement(By.id("cardNumber-hint")).getText(), paymentSanity, "Show validation card number message");//verify the validation message
        }
        else
            paymentSanity.fail("Payment page is not loaded", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\spark\\pictures\\" + "screenshot paymentPage"+currentTime)).build());

    }

    @AfterClass
    public static void afterClass() throws ParserConfigurationException, IOException, SAXException {
        driver.quit();
        extentReports.flush();
    }



    //this function get key name and read it from the config file
    private static String getData (String keyName) throws ParserConfigurationException, IOException, SAXException {
        File configXmlFile = new File("target\\Config.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        dBuilder = dbFactory.newDocumentBuilder();
        Document doc = null;
        assert dBuilder != null;
        doc = dBuilder.parse(configXmlFile);
        if (doc != null) {
            doc.getDocumentElement().normalize();
        }
        assert doc != null;
        return doc.getElementsByTagName(keyName).item(0).getTextContent();
    }

    //This function get Image Path , take a screenshot and save there
    private static String takeScreenShot(String ImagesPath) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(ImagesPath+".png");
        try {
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ImagesPath+".png";
    }
    // This function get Browser type and set the web driver
    private static  void setWebDriver(String browser) throws ParserConfigurationException, IOException, SAXException {
        switch (browser){
            case "Chrome": {
                ChromeOptions o = new ChromeOptions();
                o.addArguments("-incognito");
                o.addArguments("start-maximized");
                driver = new ChromeDriver(o);
                break;
            }
            case "FireFox":{
                FirefoxOptions firefoxOptions=new FirefoxOptions();
                firefoxOptions.addArguments("-private");
                firefoxOptions.addArguments("--disable-popup-blocking");
                driver=new FirefoxDriver(firefoxOptions);
                driver.manage().window().maximize();
                break;
            }
        }
    }
   // This function get expected result , actual result ExtentTest object and operation name that we try to do .
   // this function assert the expected and actual results and report the result to the  ExtentTest
    private void assertTest(String expectedResul,String currntResult,ExtentTest extentTest,String operationName) throws IOException {
        currentTime=String.valueOf(System.currentTimeMillis());
        try{
            Assert.assertEquals(expectedResul,currntResult);
            extentTest.pass("Test passed: "+operationName+" is working. ",  MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\spark\\pictures\\" + "screenshot "+operationName+currentTime)).build());}
        catch (AssertionError e)
        {
            extentTest.fail("Test failed: Can't "+operationName+". ",  MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("target\\spark\\pictures\\" + "screenshot "+operationName+currentTime)).build());
        }
    }

}
