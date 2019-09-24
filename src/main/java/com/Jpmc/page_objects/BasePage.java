package com.Jpmc.page_objects;

import com.Jpmc.utils.DriverManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Musti on 19/09/2019.
 */
public abstract class BasePage<T> {

    protected WebDriver driver;

    private long LOAD_TIMEOUT = 30;
    private int AJAX_ELEMENT_TIMEOUT = 10;

    public BasePage() {
        this.driver = DriverManager.getDriver();
    }


    public T openPage(Class<T> clazz) {
        T page = null;
        try {
            driver = DriverManager.getDriver();
            AjaxElementLocatorFactory ajaxElemFactory = new AjaxElementLocatorFactory(driver, AJAX_ELEMENT_TIMEOUT);
            page = PageFactory.initElements(driver, clazz);
            PageFactory.initElements(ajaxElemFactory, page);
            ExpectedCondition pageLoadCondition = ((BasePage) page).getPageLoadCondition();
            waitForPageToLoad(pageLoadCondition);
        } catch (NoSuchElementException e) {
            throw new IllegalStateException(String.format("This is not the %s page", clazz.getSimpleName()));
        }
        return page;
    }

    private void waitForPageToLoad(ExpectedCondition pageLoadCondition) {
        WebDriverWait wait = new WebDriverWait(driver,LOAD_TIMEOUT);
        wait.until(pageLoadCondition);
    }

    protected abstract ExpectedCondition getPageLoadCondition();

}