package com.datacom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class LocationsPage {

    WebDriver driver;

    public LocationsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".cmp-title h2.cmp-title__text")
    WebElement pageHeaderEl;

    @FindBy(css = ".body-text .cmp-text  p")
    List<WebElement> ourLocationContentTextsEl;

    @FindBy(css = ".cmp-tabs__tablist--anchors li[role='anchor']")
    List<WebElement> ourLocationsTabEl;

    @FindBy(css = "li[class='cmp-tabs__tab--anchors cmp-tabs__tab--anchors--active'] a")
    WebElement activeTabEl;

    @FindBy(css = "ul.cmp-location__nav__items li")
    List<WebElement> datacomRegions;

    @FindBy(css = "div[region='New Zealand']")
    List<WebElement> newZealandReg;

    @FindBy(css = "div[region='Australia']")
    List<WebElement> australiaReg;

    @FindBy(css = "div[region='Asia']")
    List<WebElement> asiaReg;

    By locationsLocator = By.cssSelector(".cmp-location__location-container");

    public Boolean isHeaderDisplayed() {
        return pageHeaderEl.isDisplayed();
    }

    public String getHeader() {
        return pageHeaderEl.getText();
    }

    public String getContentTextByText(String context) {
        for (WebElement contentText : ourLocationContentTextsEl) {
            if (contentText.getText().equals(context)) {
                return contentText.getText();
            }
        }
        return "Content text did not found!";
    }

    public List<String> getLocationsTab() {
        List<String> tabs = new ArrayList<>();
        for (WebElement tab : ourLocationsTabEl) {
            tabs.add(tab.getText());
        }
        return tabs;
    }

    public void clickLocationsTabByName(String tabName) {
        for (WebElement tab : ourLocationsTabEl) {
            if (tab.getText().equals(tabName)) {
                tab.click();
            } else if (tab.getText().equals(tabName)) {
                tab.click();
            } else if (tab.getText().equals(tabName)) {
                tab.click();
            }
        }
    }

    public  List<String> getAllDatacomRegions(){
        List<String> regions = new ArrayList<>();
        for(WebElement region : datacomRegions){
            regions.add(region.getText());
        }
        return regions;
    }

    public String getActiveTab() {
        return activeTabEl.getText();
    }
}
