package com.herokuapp.theinternet.pageObject;

import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseObject {

	protected WebDriver driver;
	protected Logger log;

	protected BaseObject(WebDriver driver, Logger log) {
		this.driver = driver;
		this.log = log;
	}

	protected void openURL(String URL) {
		driver.get(URL);
		log.info("Opening website- " + URL);
	}

	protected WebElement find(By webElement) {
		return driver.findElement(webElement);
	}

	protected void click(By webElement) {
		waitForVisibility(webElement, 5);
		find(webElement).click();
	}

	protected void enterText(String text, By webElement) {
		waitForVisibility(webElement);
		find(webElement).sendKeys(text);
	}

	protected void waitFor(ExpectedCondition<WebElement> condition, Integer timeInSeconds) {
		timeInSeconds = (timeInSeconds != null ? timeInSeconds : 30);
		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		wait.until(condition);
	}

	protected void waitForVisibility(By locator, Integer... timeInSeconds) {
		int attempt = 0;
		while (attempt < 2) {
			try {
				waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
						(timeInSeconds.length > 0 ? timeInSeconds[0] : null));

			} catch (StaleElementReferenceException e) {
			}
			attempt++;

		}
	}

}
