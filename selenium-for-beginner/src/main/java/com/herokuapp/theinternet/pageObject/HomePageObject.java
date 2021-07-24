package com.herokuapp.theinternet.pageObject;

import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePageObject extends BaseObject {

	private String pageURL = "https://the-internet.herokuapp.com/";
	private By formAuthenticationLinkLocator = By.linkText("Form Authentication");
	private By basicAuthLinkLocator = By.linkText("basic auth");

	public HomePageObject(WebDriver driver, Logger log) {
		super(driver, log);
	}
	
	protected void openPage() {
		openURL(pageURL);
		log.info("Opening Website");
	}
	
	public LoginPageObject formAuthLink() {
		click(formAuthenticationLinkLocator);
		log.info("clicking on form authentication link");
		return new LoginPageObject(driver,log);
		
	}
	
	public BasicAuthObject basicAuthLink() {
		click(basicAuthLinkLocator);
		log.info("clicking on form authentication link");
		return new BasicAuthObject(driver,log);
		
	}

}
