package com.datacom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    WebElement newZealandReg;

    @FindBy(css = "div[region='Australia']")
    WebElement australiaReg;

    @FindBy(css = "div[region='Asia']")
    WebElement asiaReg;

    By locationsLocator = By.cssSelector(".cmp-location__location-container .cmp-location__location__name");

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
                break;
            }
        }
    }

    public List<String> getAllDatacomRegions() {
        List<String> regions = new ArrayList<>();
        for (WebElement region : datacomRegions) {
            regions.add(region.getText());
        }
        return regions;
    }

    public void selectRegion(String regionName) {
        Actions actions = new Actions(driver);
        for (WebElement region : datacomRegions) {
            if (region.getText().trim().equals(regionName)) {
                actions.moveToElement(region);
                actions.perform();
                region.click();
                break;
            }
        }
    }

    public boolean checkIfRegionIsActive(String name) {
        for (WebElement region : datacomRegions) {
            if (Objects.requireNonNull(region.getAttribute("class")).contains("active")) {
                return region.getText().equals(name);
            }
        }
        return false;
    }

    public List<String> getAllDatacomLocations(String regionName) {
        List<String> locations = new ArrayList<>();
        if (regionName.equals("New Zealand")) {
            for (WebElement location : newZealandReg.findElements(locationsLocator)){
                locations.add(location.getText());
            }
        } else if (regionName.equals("Australia")) {
            for (WebElement location : australiaReg.findElements(locationsLocator)){
                locations.add(location.getText());
            }
        } else if (regionName.equals("Asia")) {
            for (WebElement location : asiaReg.findElements(locationsLocator)){
                locations.add(location.getText());
            }
        }
        return locations;
    }

    public String getActiveTab() {
        return activeTabEl.getText();
    }
}
