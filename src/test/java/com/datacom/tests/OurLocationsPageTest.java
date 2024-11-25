package com.datacom.tests;

import com.datacom.pages.LocationsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class OurLocationsPageTest {

    WebDriver driver;

    @BeforeTest
    public void setupChrome() {
        driver = new ChromeDriver();
        driver.get("https://datacom.com/nz/en/contact-us");
        driver.manage().window().maximize();
    }

    @Parameters({"header"})
    @Test
    public void PageHeader(String header) {
        LocationsPage locationsPage = new LocationsPage(driver);
        Assert.assertEquals(locationsPage.isHeaderDisplayed(), true);
        Assert.assertEquals(locationsPage.getHeader(), header);
    }

    @Parameters({"defaultContext"})
    @Test
    public void DefaultContext(String defaultContext) {
        LocationsPage locationsPage = new LocationsPage(driver);
        String context = locationsPage.getContentTextByText(defaultContext);
        Assert.assertEquals(context, defaultContext);
    }

    @Parameters({"tabNames"})
    @Test
    public void ContentTabs(String tabNames) throws InterruptedException {
        LocationsPage locationsPage = new LocationsPage(driver);
        List<String> tabs = List.of(tabNames.split(","));
        Assert.assertEquals(locationsPage.getLocationsTab(), tabs);

        // check if all tabs are clickable and active
        for (String tabName : tabs) {
            locationsPage.clickLocationsTabByName(tabName);

        }
        Thread.sleep(Duration.ofSeconds(3));
        Assert.assertEquals(locationsPage.getActiveTab(), "Media pack");

    }

    @Parameters({"regions"})
    @Test
    public void DatacomRegions(String regions) {
        LocationsPage locationsPage = new LocationsPage(driver);
        List<String> dcRegions = List.of(regions.split(","));
        Assert.assertEquals(locationsPage.getAllDatacomRegions(), dcRegions);

        // Check Region Default
        Assert.assertTrue(locationsPage.checkIfRegionIsActive("New Zealand"));

        // Select Australia
        locationsPage.selectRegion("Australia");
        Assert.assertTrue(locationsPage.checkIfRegionIsActive("Australia"));

        // Select Asia
        locationsPage.selectRegion("Asia");
        Assert.assertTrue(locationsPage.checkIfRegionIsActive("Asia"));
    }

    @Parameters({"location"})
    @Test
    public void DatacomLocations(String location) {
        LocationsPage locationsPage = new LocationsPage(driver);

        locationsPage.selectRegion("New Zealand");
        List<String> nzLocations = locationsPage.getAllDatacomLocations("New Zealand");

        boolean hastings = nzLocations.contains(location);
        Assert.assertTrue(hastings);
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }

}
