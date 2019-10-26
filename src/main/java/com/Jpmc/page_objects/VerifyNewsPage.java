package com.Jpmc.page_objects;

import com.Jpmc.utils.DriverManager;
import com.Jpmc.utils.FileWriter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static java.nio.file.Files.readAllBytes;


public class VerifyNewsPage extends BasePage {

    // Web Elements

    @FindBy(css = "#top > div.site-message.js-site-message.js-double-site-message.site-message--banner.site-message--double-banner > div.js-engagement-banner-site-message.site-message--moment-banner.site-message--engagement-banner > div > div > div.site-message__copy.js-site-message-copy.u-cf > div.engagement-banner__close > button > span.inline-close-central.inline-icon > svg") //*[@id='top']/div[6]/div/div/div[2]/div[3]/button/span[2]
    private WebElement closeCookie;

    @FindBy(linkText = "News")
    private WebElement newsButton;

    @FindBy(xpath = "//a[@data-link-name='article']")
    private WebElement newsHeadline;

    @FindBy(xpath = "div[@class='engagement-banner__close']")
    private WebElement closeSupportTheGuardian;


    // Headline news message
    @FindBy(xpath = "//h1")
    private WebElement headLineMessage;

    @FindBy(xpath = "//INPUT[@class='gLFyf gsfi']/self::INPUT")
    private WebElement searchField;

    @FindBy(xpath = "//INPUT[@name='btnK']")
    private WebElement clickSearchBtn;

    // To get the list of all the headers with the headlines
    @FindAll(@FindBy(xpath = "//h3"))   //id = "resultStats"
    private List <WebElement> result;

    @FindBy(xpath = "//body")
    private WebElement pageReady;

    public VerifyNewsPage open(String url) {

        DriverManager.getDriver().navigate().to(url);
        return (VerifyNewsPage) openPage(VerifyNewsPage.class);
    }

    public void waitUntilElementIsClickable(WebElement element) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(StaleElementReferenceException.class, NoSuchElementException.class);
        try {
            wait.until(ExpectedConditions.elementToBeClickable((element)));
        } catch (TimeoutException e) {
            System.out.println("Expected element not available: " + element);
        }
    }

    public void clickCookie() {
        closeCookie.click();

    }

    public void clickNews() {
        newsButton.click();

    }

    public void clickHeadlineNews() {
        waitUntilElementIsClickable(newsHeadline);
        Actions action = new Actions(driver);
        action.moveToElement(newsHeadline).click().perform();
    }

    public void closeGuardianSupport() {
        closeSupportTheGuardian.click();
    }

    public String getHeadline() {
        String headline = headLineMessage.getText();
        return headline;
    }

    public WebElement getSearchField() {
        return searchField;
    }

    public void enterGoogleText() {

        getSearchField().sendKeys(retrieveHeadline());
    }

    public void clickGoogleSearchBtn() {
        waitUntilElementIsClickable(clickSearchBtn);
//        Actions action = new Actions(driver);
//        action.moveToElement(clickSearchBtn).click().perform();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", clickSearchBtn); // this is to click on the Search button

    }

    public void saveTextToFile() {

        // This code will call the FileWriter class and save the retrieved text from 'copyText.getText' method & save the text to file specified.
        String textToSave = headLineMessage.getText();
        FileWriter myFileWriter = new FileWriter("NewsHeadline");
        myFileWriter.writeToFile(textToSave);
        myFileWriter.endFile();
    }
    // This code will retrieve text from the file and pass into the google text field on the web app.

    public String retrieveHeadline() {

        try {
            new Scanner(Paths.get("src/test/resources/NewsHeadline"));
        } catch (IOException e) {
            throw new RuntimeException("Could not find text file");
        }
        String retrieveText;
        try {
            retrieveText = new String(readAllBytes(Paths.get("src/test/resources/NewsHeadline")));
        } catch (IOException e) {
            throw new RuntimeException("Could not retrieve a Text");
        }

        return retrieveText;
    }

    public boolean isResultCountAtLeast(int count) {

        return result.size() >= count ? true : false;

    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(pageReady);
    }
}