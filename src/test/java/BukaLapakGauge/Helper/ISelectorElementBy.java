package BukaLapakGauge.Helper;

import org.openqa.selenium.WebElement;

public interface ISelectorElementBy {

    WebElement selectByCSS(String input);

    WebElement selectByID (String input);

    WebElement selectByXPath(String input);

    WebElement selectCSSByIndex(String input, Integer index);

    WebElement selectIDByIndex (String input, Integer index);

    WebElement selectXPathByIndex(String input, Integer index);
}
