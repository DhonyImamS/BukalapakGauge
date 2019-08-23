package BukaLapakGauge.Services;

import BukaLapakGauge.Constant.Configuration;
import BukaLapakGauge.Constant.Driver;
import BukaLapakGauge.Helper.ISelectorElementBy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Engine implements ISelectorElementBy {
    public WebDriver driver;
    public By by;
    private SmartWaitElement smartwait;
    private Configuration config;
    private Driver driverconfig;


    public Engine() {
        driver = setConfiguration(new Driver(),new Configuration());
        smartwait = new SmartWaitElement(driver,config);
    }

    public Engine(WebDriver driver){
        this.driver = driver;
        smartwait = new SmartWaitElement(driver,config);
    }

    public WebDriver setConfiguration(Driver driverSet,Configuration configSet){
        driverconfig = driverSet;
        config = configSet;
        driver = driverconfig.setupBrowser(config);
        driverconfig.ClearCache(driver);
        return driver;
    }

    @Override
    public WebElement selectByCSS(String input){
        return smartwait.selectByCSS(input);
    }

    @Override
    public WebElement selectByID(String input){

        return smartwait.selectByID(input);
    }

    @Override
    public WebElement selectByXPath(String input){

        return smartwait.selectByXPath(input);
    }

    @Override
    public WebElement selectCSSByIndex(String input, Integer index){

        return smartwait.selectCSSByIndex(input,index);
    }

    @Override
    public WebElement selectIDByIndex(String input, Integer index){

        return smartwait.selectIDByIndex(input,index);
    }

    @Override
    public WebElement selectXPathByIndex(String input, Integer index){

        return smartwait.selectXPathByIndex(input,index);
    }

    public WebDriverWait delay(long time){

        return smartwait.delay(time);
    }

    public FluentWait delayFluentWait(long time, long pollingTime){

        return smartwait.delayFluentWait(time, pollingTime);
    }

    public FluentWait<WebDriver> delayFluentWaitWebDriver(long time, long pollingTime){

        return smartwait.delayFluentWaitWebDriver(time, pollingTime);
    }

    public void WaitPresenceElementByCss(WebDriverWait wait,String Element) {
        try{
            smartwait.waitPresenceOfElementLocatedSelector(wait,"CSS", Element);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void WaitPresenceElementByID(WebDriverWait wait,String Element) {
        try{
            smartwait.waitPresenceOfElementLocatedSelector(wait,"ID", Element);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void WaitPresenceElementByXPath(WebDriverWait wait,String Element) {
        try{
            smartwait.waitPresenceOfElementLocatedSelector(wait,"XPATH", Element);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /** Upgrade Function in Selenium Version 3 **/
    public boolean fluentWaitElementDisappearByCss(WebElement elements,String Element) throws Exception {

        return smartwait.fluentWaitElementDisappearBy("CSS", elements, Element);

    }

    public boolean fluentWaitElementDisappearByID(WebElement elements,String Element) throws Exception {

        return smartwait.fluentWaitElementDisappearBy("ID", elements, Element);

    }

    public boolean fluentWaitElementDisappearByXPath(WebElement elements,String Element) throws Exception {

        return smartwait.fluentWaitElementDisappearBy("XPATH", elements, Element);

    }

    public void launchURL(){

        driver = driverconfig.startNavigate(driver,config.WebsiteURL);
    }

    public void browerClose(){

        driver.close();
    }

    public void mouse_wheel_Ny_JavaScript () throws Exception
    {
        //this.driver = Driver;
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollTo(0,50);");
    }

    public boolean scroll_down(WebElement element, int scrollPoints) throws Exception
    {
        try
        {
            Actions dragger = new Actions(driver);
            // drag downwards
            int incrementvalue = 50;
            for (int i = 50; i < scrollPoints; i = i + incrementvalue)
            {
                dragger.moveToElement(element).clickAndHold().moveByOffset(0,incrementvalue).release(element).build().perform();
            }
            Thread.sleep(50);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public void scroll_down_Ny_JavaScript() throws Exception
    {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,250)", "");
    }

    public void scroll_up_Ny_JavaScript() throws Exception
    {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,-250)", "");
    }
}

