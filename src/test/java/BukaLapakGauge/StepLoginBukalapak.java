package BukaLapakGauge;

import BukaLapakGauge.Element.ElHome;
import BukaLapakGauge.Page.HomePage;
import com.thoughtworks.gauge.Step;

import static org.assertj.core.api.Assertions.assertThat;

import BukaLapakGauge.Element.ElLogin;
import BukaLapakGauge.Services.Engine;
import BukaLapakGauge.Page.LoginPage;

public class StepLoginBukalapak {
    public Engine engine;
    public HomePage homePage;
    public LoginPage loginPage;
    public ElHome elHome;
    public ElLogin elLogin;

    @Step("Everything is Set")
    public void readytoSet() {
        engine = new Engine();
    }

    @Step("Wait Till Login Page Ready")
    public void verifyLoginPageLoad() {
        engine.launchURL();
        elHome = new ElHome();
        elLogin = new ElLogin();

        homePage = new HomePage(engine);
        loginPage = new LoginPage(engine);

        try {
            homePage.waitHomePageLoad();
            homePage.clickLoginLink();
            loginPage.waitLoginFormLoad();
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