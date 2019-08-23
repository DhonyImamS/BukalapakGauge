package BukaLapakGauge.Constant;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class Driver {

    public WebDriver Control;
    private Configuration conf;
    private DesiredCapabilities desiredCapabilities;

    public WebDriver startNavigate(WebDriver control,String URL){
        control.manage().window().maximize();
        control.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        control.navigate().to(URL);
        return control;
    }


    public WebDriver setupBrowser(Configuration config){
        conf = config;
        if (conf.browser.equals("chrome")){
            System.setProperty(conf.WebdriverKeyChrome,conf.WebdriverPathChrome);
            this.Control = new ChromeDriver();
            this.desiredCapabilities = DesiredCapabilities.chrome();
        }
        else if (conf.browser.equals("firefox") && !conf.OS.equals("macOSX")){
            System.setProperty(conf.WebdriverKeyFirefox,conf.WebdriverPathFirefox);
            this.Control = new FirefoxDriver();
        }
        else if (conf.browser.equals("firefox") && conf.OS.equals("windows")){
            System.setProperty(conf.WebdriverKeyFirefox,conf.WebdriverPathFirefoxMac);
            this.Control = new FirefoxDriver();
        }
        return Control;
    }

    public void ClearCache(WebDriver control){
        this.Control = control;
        this.Control.manage().deleteAllCookies();
    }
}
