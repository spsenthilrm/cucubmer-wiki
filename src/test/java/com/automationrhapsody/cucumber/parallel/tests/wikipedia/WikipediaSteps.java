package com.automationrhapsody.cucumber.parallel.tests.wikipedia;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
//import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class WikipediaSteps extends BaseSteps {

	//private SoftAssertions softAssertions;
	
    @Before
    public void before() {
        startWebDriver();
        //softAssertions = new SoftAssertions();
    }
    

    @After
    public void after() {
    	stopWebDriver();
    	//softAssertions.assertAll();
    }

    @Given("^Enter search term '(.*?)'$")
    public void searchFor(String searchTerm)  throws Throwable {
    	System.out.println("Search term recived :::"+searchTerm);
    	List<String> nameList = new ArrayList<>();
        WebElement searchField = driver.findElement(By.id("searchInput"));
        searchField.sendKeys(searchTerm);
    }

    @When("^Do search$")
    public void clickSearchButton() throws Throwable {
        WebElement searchButton = driver.findElement(By.id("searchButton"));
        searchButton.click();
        //wait(2);
    }

    @Then("^Multiple results are shown for '(.*?)'$")
    public void assertMultipleResults(String searchResults) throws Throwable {
    	System.out.println("Multiple results are shown for "+searchResults);
        WebElement firstSearchResult = driver.findElement(By.cssSelector("div#mw-content-text.mw-content-ltr p"));
        assertEquals(searchResults, firstSearchResult.getText());
    }
    
    @Then("^Multiple Look up for '(.*?)'$")
    public void assertMultipleLookupResults(String searchResults) throws Throwable {
    	System.out.println("Multiple Look up "+searchResults);
        WebElement firstSearchResult = driver.findElement(By.cssSelector("td.mbox-text.plainlist .extiw"));
        assertEquals(searchResults, firstSearchResult.getText());
    }

    @Then("^Single result is shown for '(.*?)'$")
    public void assertSingleResult(String searchResults) throws Throwable {
        WebElement articleName = driver.findElement(By.id("firstHeading"));
        assertEquals(articleName.getText(), searchResults);
    }
    
    @Then("^Single result is shown for '(.*?)' wrongly$")
    public void assertSingleResultWrongly(String searchResults) throws Throwable {
    	String actualResult = "";
        try {
    	WebElement articleName = driver.findElement(By.id("firstHeadingasdfddfd"));
        actualResult = articleName.getText();
        } catch (Exception ex) {
        	System.out.println("error is ooo"+ex.getMessage());
        }
        //assertEquals(articleName.getText(), searchResults);
        assertEquals(searchResults, actualResult);
        //softAssertions.assertThat(searchResults).isEqualTo(actualResult);
    }

    @Then("^This is (.*?)good article$")
    public void assertGoodArticle(String isNot) throws Throwable {
        boolean isGood = isNot != null && "".equals(isNot);
        try {
            driver.findElement(By.cssSelector("div#mw-indicator-good-star.mw-indicator a img"));
            assertTrue(isGood);
        } catch (NoSuchElementException enfe) {
            assertTrue(!isGood);
        }
    }

    @Then("^Current date is shown$")
    public void checkCurrentDate() throws Throwable {
        WebElement element = driver.findElement(By.cssSelector("div#mp-otd p b a"));

        String fullFormat = "%s";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMMM d");
        String expected = String.format(fullFormat, LocalDate.now().format(dateFormat));
        System.out.println("expected"+expected);
        System.out.println("actual"+element.getText());
        assertEquals(expected, element.getText());
    }
}