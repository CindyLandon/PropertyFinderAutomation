package com.prf.pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import com.prf.common.CommonFeatures;
import org.openqa.selenium.WebDriver;
import junit.framework.Assert;

public class FindAgentsPage {
	
	WebDriver driver;
	int totalAgentsCount = 0;
	int countryAgentsCount = 0;
	CommonFeatures cf;
	Properties p;
	
	/** Find button on the 'Find Agents' page. */
	public static final String FIND_AGENT_BUTTON = "button.button.button-fullheight.button-connectedright.button-uppercase";
	
	/** Text showing the count of maching agents for a search */
	public static final String NUM_AGENTS_TEXT = "div.heading > h1.title";
	
	/** Nationality dropdown on the Agent Search results page. */
	public static final String CLICK_NATIONALITY_DROPDOWN = "div:nth-child(5) > div > div.dropdown_text.dropdown_text-serp";
	
	/** Element locator for selecting India from the Nationality dropdown. */
	public static final String SELECT_NATIONALITY_INDIA = "div.dropdown_popup.dropdown_popup-opened > div:nth-child(33)";
	
	/**
	 * @param driver
	 */
	public FindAgentsPage(WebDriver driver){
		this.driver = driver;	
	}
	
	/**
	 * @param lang
	 */
	public void selectLanguageDropdown(String lang) {
		p = new Properties();
		try {
	    	FileInputStream fi = new FileInputStream("C:\\PropertyFinder\\global.properties");
			p.load(fi);
	    } catch (IOException ioe){
	    		System.err.println(ioe.getMessage());
	    }
		
		if(!(p.getProperty("browser").contains("phantom"))){
			driver.findElement(By.xpath("//div[4]/div/div[2]//*[contains(text(), '" + lang + "')]")).click();
		}
	}

	/**
	 * @param lang
	 */
	public void selectLanguage(String lang){
		chooseLanguage(lang);
	}
	
	public void clickFindAgentButton() {
		driver.findElement(By.cssSelector(FIND_AGENT_BUTTON)).click();
	}
	
	public void getAgentsCount(){	
		String  numAgentsText = driver.findElement(By.cssSelector(NUM_AGENTS_TEXT)).getText();
		String[] parts = numAgentsText.split(" Matching");
		totalAgentsCount = Integer.parseInt(parts[0]);
	}

	public void selectNationality(){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0)");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector(CLICK_NATIONALITY_DROPDOWN)).click();
		driver.findElement(By.cssSelector(SELECT_NATIONALITY_INDIA)).click();
	}
	
	public void getAgentsCountByNationality(){
		//clears browser cache
		driver.manage().deleteAllCookies();
		String countryAgentsText = driver.findElement(By.cssSelector(NUM_AGENTS_TEXT)).getText();
		String[] parts = countryAgentsText.split(" Matching");
		countryAgentsCount = Integer.parseInt(parts[0]);	
	}
	
	public void compareAgentsCounts(){
			Assert.assertTrue(countryAgentsCount < totalAgentsCount);
	}
	
	/**
	 * @param lang
	 */
	private void chooseLanguage(String lang) {
		String language = "//div[4]/div/div[2]//*[contains(text(), '" + lang + "')]";
		driver.findElement(By.xpath(language)).click();
	}
}
