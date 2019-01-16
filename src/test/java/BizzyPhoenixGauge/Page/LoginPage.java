package BizzyPhoenixGauge.Page;

import org.openqa.selenium.WebElement;

import java.util.List;
import BizzyPhoenixGauge.Services.Engine;
import BizzyPhoenixGauge.Element.ElLogin;

public class LoginPage extends Engine {

    public Engine engine;
    private String key;
    private ElLogin elementLogin;
    private WebElement element;
    private List elementList;

    public LoginPage(Engine engines){
        super(engines.driver);
        this.engine = engines;
        elementLogin = new ElLogin();
    }

    public void waitLoginPageLoad() {
        key = elementLogin._fieldUsername().getCSSElement();
        element = engine.selectByCSS(key);
        key = elementLogin._fieldPassword().getIdElement();
        element = engine.selectByID(key);
        key = elementLogin._buttonLogin().getCSSElement();
        element = engine.selectByCSS(key);
    }

    public void setFieldUsername(String input) {
        key = elementLogin._fieldUsername().getCSSElement();
        element = engine.selectByCSS(key);
        element.sendKeys(input);
    }

    public void setFieldPassword(String input) {
        key = elementLogin._fieldPassword().getCSSElement();
        element = engine.selectByCSS(key);
        element.sendKeys(input);
    }

    public void clickButtonLogin() {
        key = elementLogin._buttonLogin().getCSSElement();
        element = engine.selectByCSS(key);
        element.click();
    }
}
