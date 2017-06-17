import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jetma on 6/10/2017.
 */
public class Client {
    WebDriver driver;
    By txtInterests, txtChatBox, btnStartChatting, btnDisconnect, btnSend;
    boolean firstConnect;

    public Client(){
        driver = new ChromeDriver();
        driver.get("http://www.omegle.com/");
        txtInterests = By.className("newtopicinput");
        txtChatBox = By.className("chatmsg");
        btnStartChatting = By.id("textbtn");
        btnDisconnect = By.className("disconnectbtn");
        btnSend = By.className("sendbtn");
        firstConnect = true;
    }

    public void connect(String tag){
        if(firstConnect){
            driver.findElement(txtInterests).sendKeys(tag);
            driver.findElement(btnStartChatting).click();
            firstConnect = false;
        }else{
            driver.findElement(btnDisconnect).click();
        }
    }

    public void disconnect(){
        driver.findElement(btnDisconnect).click();
        driver.findElement(btnDisconnect).click();
    }

    public void sendMessage(String message){
        driver.findElement(txtChatBox).sendKeys(message);
        driver.findElement(btnSend).click();
    }

    public ArrayList<Message> getMessages(){
        ArrayList<Message> messages = new ArrayList<>();
        List<WebElement> items = driver.findElements(By.className("logitem"));
        for(int i = 0; i < items.size(); i++){
            try{
                WebElement messageElement = items.get(i).findElement(By.tagName("p"));
                System.out.println("Client: P FOUND at index - " + i);
                int type = -1;
                String message = "";
                if(messageElement.getAttribute("class").equals("statuslog")){
                    type = Message.SYSTEM;
                }else if(messageElement.getAttribute("class").equals("youmsg")){
                    type = Message.LOCAL;
                }else if(messageElement.getAttribute("class").equals("strangermsg")){
                    type = Message.STRANGER;
                }

                if(type == Message.SYSTEM){
                    message = messageElement.getText();
                }else if(type == Message.STRANGER || type == Message.LOCAL){
                    message = messageElement.findElement(By.tagName("span")).getText();
                }
                System.out.println(message);
                messages.add(new Message(message, type));
            }catch(NoSuchElementException | StaleElementReferenceException e){
                e.printStackTrace();
            }
        }
        return messages;
    }

    public WebDriver getDriver(){
        return driver;
    }
}
