package BizzyPhoenixGauge;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import static org.assertj.core.api.Assertions.assertThat;

import BizzyPhoenixGauge.Element.ElLogin;
import BizzyPhoenixGauge.Services.Engine;
import BizzyPhoenixGauge.Page.LoginPage;
import org.openqa.selenium.WebElement;

public class StepLoginFEBizzy {
    public Engine engine;
    public LoginPage loginPage;
    public ElLogin elLogin;

    @Step("Everything is Set")
    public void readytoSet() {
        engine = new Engine();
    }

    @Step("Wait Till Login Page Ready")
    public void verifyLoginPageLoad() {
        engine.launchURL();

        elLogin = new ElLogin();
        loginPage = new LoginPage(engine);

        try {
            loginPage.waitLoginPageLoad();
        } catch (Exception e) {
            e.printStackTrace();
        };
    }

    @Step("Field Username with <username> and Password with <password> Field")
    public void setUsernamePasswordField(String username, String password) {
        try {
            loginPage.setFieldUsername(username);
            loginPage.setFieldPassword(password);
        } catch (Exception e) {
            e.printStackTrace();
        };
    }

    @Step("Click Button Login")
    public void clickButtonLogin() {
        try {
            loginPage.clickButtonLogin();
        } catch (Exception e) {
            e.printStackTrace();
        };
    }
}
