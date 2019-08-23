package BukaLapakGauge.Services;

import com.google.common.base.Function;
import BukaLapakGauge.Constant.Configuration;
import BukaLapakGauge.Helper.ISelectorElementBy;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SmartWaitElement implements ISelectorElementBy {

    public WebDriver driver;
    public By by;
    private WebElement element;
    private List elementlist;
    private WebDriverWait wait;
    private FluentWait fluentWait;
    private FluentWait<WebDriver> webDriverFluentWait;
    private Configuration config;
    private String target;
    private String option;
    private Integer idx;

    public SmartWaitElement(WebDriver drivers, Configuration configs) {
        this.driver = drivers;
        this.config = configs;
        this.setWaitingTimeAll(config);
    }

    @Override
    public WebElement selectByCSS(String input) {

        return queryElementProcess("CSS",input,0);
    }

    @Override
    public WebElement selectByID(String input) {

        return queryElementProcess("ID",input,0);
    }

    @Override
    public WebElement selectByXPath(String input) {

        return queryElementProcess("XPATH",input,0);
    }

    @Override
    public WebElement selectCSSByIndex(String input, Integer index) {

        return queryElementProcess("CSS", input, index);
    }

    @Override
    public WebElement selectIDByIndex(String input, Integer index) {

        return queryElementProcess("ID", input, index);
    }

    @Override
    public WebElement selectXPathByIndex(String input, Integer index) {

        return queryElementProcess("XPATH", input, index);
    }

    Function<WebDriver,Boolean> isElementReady = new Function<WebDriver,Boolean>() {
        @Override
        public Boolean apply(WebDriver driver) {
            element = searchElementBy(option,target,idx);
            if (element.equals(null)){
                return false;
            }
            else {
                try {
                    fluentWaitElementBy(option,target);
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }
        }
    };


    public void setWaitingTimeAll(Configuration config){
        this.wait = delay(config.timeout);
        this.fluentWait = delayFluentWait(config.timeout,config.pollingTime);
        this.webDriverFluentWait = delayFluentWaitWebDriver(config.timeout,config.pollingTime);
    }

    public WebDriverWait delay(long time){
        wait = new WebDriverWait(driver,time);
        return wait;
    }


    public FluentWait delayFluentWait(long time, long pollingTime){
        fluentWait = new FluentWait(driver);
        //Specify the timout of the wait
        fluentWait.withTimeout(time, TimeUnit.SECONDS);
        //Sepcify polling time
        fluentWait.pollingEvery(pollingTime, TimeUnit.MILLISECONDS);
        //Specify what exceptions to ignore
        fluentWait.ignoring(NoSuchElementException.class);
        return fluentWait;
    }

    public FluentWait<WebDriver> delayFluentWaitWebDriver(long time, long pollingTime){
        this.webDriverFluentWait = new FluentWait<WebDriver>(driver);
        //Specify the timout of the wait
        this.webDriverFluentWait.withTimeout(time, TimeUnit.SECONDS);
        //Sepcify polling time
        this.webDriverFluentWait.pollingEvery(pollingTime, TimeUnit.MILLISECONDS);
        //Specify what exceptions to ignore
        this.webDriverFluentWait.ignoring(NoSuchElementException.class);
        return webDriverFluentWait;
    }

    public WebElement queryElementProcess(String selector, String Element, Integer index){
        try{
            this.target = Element;
            this.option = selector;
            this.idx = index;

            if (webDriverFluentWait.until(isElementReady)){
                WaitElementBy(selector,wait,target);
                element = searchElementBy(selector,target,idx);
            }
            else {
                fluentWaitElementBy(selector, Element);
                WaitElementBy(selector,wait, Element);
                element = searchElementBy(selector,target,idx);
            }
        } catch (Exception m){
            m.printStackTrace();
        };
        return element;
    }

    public WebElement searchElementBy(String selector, String Element, Integer index){
        if (index==0) {
            switch (selector){
                case "CSS" :
                    element = driver.findElement(By.cssSelector(Element));
                    break;
                case "ID" :
                    element = driver.findElement(By.id(Element));
                    break;
                case "XPATH" :
                    element = driver.findElement(By.xpath(Element));
                    break;
                default:
                    element = driver.findElement(By.cssSelector(Element));
                    break;
            };

        } else {
            switch (selector){
                case "CSS" :
                    elementlist = driver.findElements(By.cssSelector(Element));
                    break;
                case "ID" :
                    elementlist = driver.findElements(By.id(Element));
                    break;
                case "XPATH" :
                    elementlist = driver.findElements(By.xpath(Element));
                    break;
                default:
                    elementlist = driver.findElements(By.cssSelector(Element));
                    break;
            };
            element = (WebElement)(elementlist.get(index));
        }
        return element;
    }

    public FluentWait fluentwaitVisibilityOfElementLocatedSelector(String selector, String Element){
        switch (selector){
            case "CSS" :
                fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(Element)));
                break;
            case "ID" :
                fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Element)));
                break;
            case "XPATH" :
                fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Element)));
                break;
            default:
                fluentWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(Element)));
                break;
        };
        return fluentWait;
    }

    public WebDriverWait waitVisibilityOfElementLocatedSelector(WebDriverWait wait, String selector, String Element){
        switch (selector){
            case "CSS" :
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(Element)));
                break;
            case "ID" :
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Element)));
                break;
            case "XPATH" :
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Element)));
                break;
            default:
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(Element)));
                break;
        };
        return wait;
    }

    public FluentWait fluentwaitPresenceOfElementLocatedSelector(String selector, String Element){
        switch (selector){
            case "CSS" :
                fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(Element)));
                break;
            case "ID" :
                fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.id(Element)));
                break;
            case "XPATH" :
                fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Element)));
                break;
            default:
                fluentWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(Element)));
                break;
        };
        return fluentWait;
    }

    public WebDriverWait waitPresenceOfElementLocatedSelector(WebDriverWait wait, String selector, String Element){
        switch (selector){
            case "CSS" :
                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(Element)));
                break;
            case "ID" :
                wait.until(ExpectedConditions.presenceOfElementLocated(By.id(Element)));
                break;
            case "XPATH" :
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Element)));
                break;
            default:
                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(Element)));
                break;
        };
        return wait;
    }

    public FluentWait fluentwaitInvisibilityOfElementLocatedSelector(String selector, String Element){
        switch (selector){
            case "CSS" :
                fluentWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(Element)));
                break;
            case "ID" :
                fluentWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(Element)));
                break;
            case "XPATH" :
                fluentWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(Element)));
                break;
            default:
                fluentWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(Element)));
                break;
        };
        return fluentWait;
    }

    public WebDriverWait waitInvisibilityOfElementLocatedSelector(WebDriverWait wait, String selector, String Element){
        switch (selector){
            case "CSS" :
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(Element)));
                break;
            case "ID" :
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(Element)));
                break;
            case "XPATH" :
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(Element)));
                break;
            default:
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(Element)));
                break;
        };
        return wait;
    }

    /** Upgrade Function in Selenium Version 3 **/
    public void fluentWaitElementBy(String selector,String Element) throws Exception {
        try{
            fluentwaitVisibilityOfElementLocatedSelector(selector, Element);
        }
        catch(Exception e){
            try{
                fluentwaitPresenceOfElementLocatedSelector(selector, Element);
                fluentWaitElementBy(selector,Element);
            }
            catch (Exception k){
                k.printStackTrace();
            }
        }
    }

    /** Deprecated Function in Selenium Version 2 **/
    public void WaitElementBy(String Selector,WebDriverWait wait,String Element) {
        try{
            waitVisibilityOfElementLocatedSelector(wait,Selector,Element);
        }
        catch(Exception e){
            try{
                waitPresenceOfElementLocatedSelector(wait,Selector,Element);
                WaitElementBy("CSS",wait,Element);
            }
            catch (Exception k){
                k.printStackTrace();
            }
        }
    }

    public boolean fluentWaitElementDisappearBy(String selector, WebElement elements, String Element) throws Exception {
        fluentWait = delayFluentWait(config.waitingTimeElementNotVisible,config.pollingTime);
        try{
            fluentwaitInvisibilityOfElementLocatedSelector(selector, Element);
            fluentWait = delayFluentWait(config.timeout,config.pollingTime);
            return true;
        }
        catch(Exception e){
            try{
                fluentWait.until(ExpectedConditions.invisibilityOf(elements));
                fluentWait = delayFluentWait(config.timeout,config.pollingTime);
                return true;
            }
            catch (Exception k){
                k.printStackTrace();
                fluentWait = delayFluentWait(config.timeout,config.pollingTime);
                return false;
            }
        }
    }

    public void WaitElementDisappearBy(String Selector,WebDriverWait wait,String Element) {
        try{
            waitInvisibilityOfElementLocatedSelector(wait,Selector,Element);
        }
        catch(Exception e){
            WaitElementDisappearBy(Selector,wait,Element);
        }

    }

    public void WaitPresenceElementBy(String Selector,WebDriverWait wait,String Element) {
        try{
            waitPresenceOfElementLocatedSelector(wait,Selector,Element);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}

