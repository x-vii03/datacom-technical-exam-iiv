package com.datacom.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;

public class OurLocationsPageInterAction {

    WebDriver driver;

    @BeforeTest
    public void setupChrome() {
        driver = new ChromeDriver();
        driver.get("https://datacom.com/nz/en/contact-us");
        driver.manage().window().maximize();
    }

    @Test
    public void ourLocationsLandingPage() {
        boolean isOurLocationheaderDisplayed = driver.findElement(By.cssSelector(".cmp-title h2.cmp-title__text")).isDisplayed();
        String headerValue = driver.findElement(By.cssSelector(".cmp-title h2.cmp-title__text")).getText();
        Assert.assertTrue(isOurLocationheaderDisplayed);
        Assert.assertEquals(headerValue, "Our locations");

        List<WebElement> bodyTexts = driver.findElements(By.cssSelector(".body-text .cmp-text  p"));
        String text = "";
        for (WebElement bodyText : bodyTexts) {
            if (bodyText.getText().contains("Contact one of our global offices")) {
                text = bodyText.getText();
            }
            ;
        }

        Assert.assertEquals(text, "Contact one of our global offices or one of our teams to find out more about how we can help you, or to answer any query you may have.");
    }

    @Test
    public void ourLocationsContentTab() {
        List<WebElement> contentTabs = driver.findElements(By.cssSelector(".cmp-tabs__tablist--anchors li[role='anchor'].cmp-tabs__tab--anchors"));

        Assert.assertEquals(contentTabs.size(), 3);
        for (WebElement contentTab : contentTabs) {
            if (contentTab.getText().equals("Get in touch")) {
                contentTab.click();
            } else if (contentTab.getText().equals("Media pack")) {
                contentTab.click();
            } else if (contentTab.getText().equals("Our locations")) {
                contentTab.click();
            }
        }
    }


    @Test
    public void regions() {
        List<WebElement> locations = driver.findElements(By.cssSelector("ul.cmp-location__nav__items li"));

        // Check default location - should be New Zealand
        for (WebElement location : locations) {

            if (Objects.requireNonNull(location.getAttribute("class")).contains("active")) {
                Assert.assertEquals(location.getText().trim(), "New Zealand");
            }

            if (location.getText().trim().equals("New Zealand")) {
                location.click();
            } else if (location.getText().trim().equals("Australia")) {
                location.click();
            } else if (location.getText().trim().equals("Asia")) {
                location.click();
            }

            System.out.println(location.getText());
        }
    }

    @Test
    public void locations() {
        WebElement newZealandRegion = driver.findElement(By.cssSelector("div[region='New Zealand']"));
        List<WebElement> newZeaLandLocations = newZealandRegion.findElements(By.cssSelector(".cmp-location__location-container"));

        Assert.assertEquals(newZeaLandLocations.size(), 10);

        WebElement australiaRegion = driver.findElement(By.cssSelector("div[region='Australia']"));
        List<WebElement> australiaLocaitons = australiaRegion.findElements(By.cssSelector(".cmp-location__location-container"));

        Assert.assertEquals(australiaLocaitons.size(), 8);

        WebElement asiaRegion = driver.findElement(By.cssSelector("div[region='Asia']"));
        List<WebElement> asiaLocations = asiaRegion.findElements(By.cssSelector(".cmp-location__location-container"));

        Assert.assertEquals(asiaLocations.size(), 3);
    }

    @AfterTest
    public void teardown(){
        driver.quit();
    }

}
