import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * Created by jetma on 6/10/2017.
 */
public class Client {
    WebDriver driver;
    WebElement txtInterests, btnStartChatting, btnDisconnect;

    public Client(){
        this.driver = new HtmlUnitDriver();
        driver.get("http://www.omegle.com/");
    }

    public void connectClient(String tag){

    }

    public WebDriver getDriver(){
        return driver;
    }
}
