package com.prf.pages;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.prf.common.CommonFeatures;

public class AgentDetailsPage {
	
	WebDriver driver;
	CommonFeatures cf;
	StringBuffer buffer;
	
	/** Brief info card of the very first agent on the Search results page for the agent. */
	public static final String FIRST_AGENT_IMAGE = "a:nth-child(1) > div > div.tile_images > img";
	
	/** Agent Name from the Agent Details page.*/
	public static final String AGENT_NAME = "h1.title.title-size1.bioinfo_name";
	
	/** Agent Nationality from the Agent Details page.*/
	public static final String AGENT_NATIONALITY = "div.bioinfo_personal > div > div > p:nth-child(1) > span:nth-child(2)";
	
	/** Agent Languages from the Agent Details page.*/
	public static final String AGENT_LANGUAGES = "div.bioinfo_personal > div > div > p:nth-child(2) > span:nth-child(2)";
	
	/** Agent License Number from the Agent Details page.*/
	public static final String AGENT_LICENSE_NUM = "div.pane_section.pane_section-style1 > div > div:nth-child(1) > p:nth-child(2) > span:nth-child(2)";
	
	/** About me info from the Agent Details page.*/
	public static final String AGENT_ABOUT_ME_INFO = "div > div.tab_content.tab_content-size1.tab_content-active";
	
	/** Call Agent button on the Agent Details page.*/
	public static final String CALL_AGENT_BUTTON = "a.button.pane_button > span.button_text.button_text-label";
	
	/** Contact phone number from the Agent Details page.*/
	public static final String AGENT_PHONE_NUM = "a.button.pane_button > span.button_text.button_text-value.button_phone-ltr";
	
	/** Company name from the Agent Details page.*/
	public static final String AGENT_COMPANY_NAME = "div.brokerthumbnail_text > p.text.text-size1";
	
	/** Agent experience from the Agent Details page.*/
	public static final String AGENT_EXPERIENCE = "div.pane_section.pane_section-style1 > div > div:nth-child(1) > p:nth-child(3) > span:nth-child(2)";
	
	/** Agent listings from the Agent Details page.*/
	public static final String AGENT_LISTINGS = "p:nth-child(1) > span:nth-child(2) > a.link";
	
	/** Agent LinkendIn URL from the Agent Details page.*/
	public static final String AGENT_LINKEDIN_URL = "p:nth-child(4) > span:nth-child(2) > a.link";
	
	/**
	 * 
	 * @param driver
	 */
	public AgentDetailsPage(WebDriver driver){
		this.driver = driver;
	}
	
	public void clickFirstAgent(){
		driver.findElement(By.cssSelector(FIRST_AGENT_IMAGE)).click();;
	}

	public void saveAgentName(){
		String agentName = driver.findElement(By.cssSelector(AGENT_NAME)).getText();
		buffer = new StringBuffer ();
		buffer.append("Agent Name: " + agentName);
		buffer.append(System.getProperty("line.separator"));
	}

	public void saveAgentNationality(){
		String nationality = driver.findElement(By.cssSelector(AGENT_NATIONALITY)).getText();
		buffer.append("Nationality: " + nationality);
		buffer.append(System.getProperty("line.separator"));
	}

	public void saveAgentLanguages(){
		String languages = driver.findElement(By.cssSelector(AGENT_LANGUAGES)).getText();
		buffer.append("Languages: " + languages);
		buffer.append(System.getProperty("line.separator"));
	}

	public void saveAgentLicenseNum(){
		String license = driver.findElement(By.cssSelector(AGENT_LICENSE_NUM)).getText();
		buffer.append("License Number: " + license);
		buffer.append(System.getProperty("line.separator"));
	}
	
	public void selectAboutMeTab(){
		driver.findElement(By.linkText("About me")).click();
	}

	public void saveAboutMeInfo(){
		String aboutMe = driver.findElement(By.cssSelector(AGENT_ABOUT_ME_INFO)).getText();
		buffer.append("About me info: "); 
		buffer.append(System.getProperty("line.separator"));
		buffer.append("==============");
		buffer.append(System.getProperty("line.separator"));
		buffer.append(aboutMe);
		buffer.append(System.getProperty("line.separator"));
	}

	public void clickCallAgentButton(){
		driver.findElement(By.cssSelector(CALL_AGENT_BUTTON)).click();
	}

	public void saveAgentPhoneNum(){
		String phone = driver.findElement(By.cssSelector(AGENT_PHONE_NUM)).getText();
		buffer.append("Phone Number: " + phone);
		buffer.append(System.getProperty("line.separator"));
	}

	public void saveAgentCompanyName(){
		String company = driver.findElement(By.cssSelector(AGENT_COMPANY_NAME)).getText();
		buffer.append("Company Name: " + company);
		buffer.append(System.getProperty("line.separator"));
	}

	public void saveAgentExperience(){
		String experience = driver.findElement(By.cssSelector(AGENT_EXPERIENCE)).getText();
		buffer.append("Experience: " + experience);
		buffer.append(System.getProperty("line.separator"));
	}

	public void saveAgentNumOfListings(){
		String listings = driver.findElement(By.cssSelector(AGENT_LISTINGS)).getText();
		buffer.append("Number of listings: " + listings);
		buffer.append(System.getProperty("line.separator"));
	}

	public void saveAgentLinkedInUrl(){
		if(AGENT_LINKEDIN_URL != null && !AGENT_LINKEDIN_URL.isEmpty())
		{
			String linkedIn = driver.findElement(By.cssSelector(AGENT_LINKEDIN_URL)).getAttribute("href");
			buffer.append("LinkedIn Url: " + linkedIn);
			buffer.append(System.getProperty("line.separator"));
		}
	}

	public void saveAgentInfo() throws IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File("C:\\PropertyFinder\\AgentDetails.txt")));
		writer.write(buffer.toString());
		writer.flush();
		writer.close();
	}

	public void toggleLanguage(){
		driver.findElement(By.cssSelector(CommonFeatures.TOGGLE_LANGUAGE_TO_ARABIC)).click();
	}
}
