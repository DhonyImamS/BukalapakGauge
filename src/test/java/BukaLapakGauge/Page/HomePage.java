package BukaLapakGauge.Page;

import BukaLapakGauge.Element.ElHome;
import BukaLapakGauge.Services.Engine;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends Engine {

    public Engine engine;
    private String key;
    private ElHome elementHome;
    private WebElement element;
    private List elementList;

    public HomePage(Engine engines){
        super(engines.driver);
        this.engine = engines;
        elementHome = new ElHome();
    }

    public void waitHomePageLoad() {
        key = elementHome._buttonLoginOnNavbar().getCSSElement();
        element = engine.selectByCSS(key);
    }

    public void clickLoginLink() {
        key = elementHome._buttonLoginOnNavbar().getCSSElement();
        element = engine.selectByCSS(key);
        element.click();
    }
}
