package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.herokuapp.theinternet.base.TestUtility;

public class NegativeTests extends TestUtility {

	@Test(priority = 1, groups = { "smoke-testing", "negative-testing" })
	public void incorrectUsername() {

		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);
		
		log.info("test start");

		WebElement username = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement logInButton = driver.findElement(By.tagName("button"));

		username.sendKeys("incorrectusername");
		password.sendKeys("SuperSecretPassword!");
		logInButton.click();

		sleep(3000);
//		WebElement alert = driver.findElement(By.className("flash success"));
//		WebElement pageHeader = driver.findElement(By.tagName("h2"));
//		WebElement pageSubHeader = driver.findElement(By.className("subheader"));
		WebElement actualAlterElement = driver.findElement(By.xpath("//div[@id='flash']"));

		String actualAlter = actualAlterElement.getText();
		String expectedAlter = "Your username is invalid!";

		Assert.assertTrue(actualAlter.contains(expectedAlter), "Test case is fail");
		
		log.info("test complete");

	}

	@Test(priority = 2, groups = { "negative-testing" })
	public void incorrectPassword() {

		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);
		
		log.info("test start");

		WebElement username = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement logInButton = driver.findElement(By.tagName("button"));

		username.sendKeys("tomsmith");
		password.sendKeys("incorrectPassword");
		logInButton.click();

		sleep(3000);
//		WebElement alert = driver.findElement(By.className("flash success"));
//		WebElement pageHeader = driver.findElement(By.tagName("h2"));
//		WebElement pageSubHeader = driver.findElement(By.className("subheader"));
		WebElement actualAlterElement = driver.findElement(By.xpath("//div[@id='flash']"));

		String actualAlter = actualAlterElement.getText();
		String expectedAlter = "Your password is invalid!";

		Assert.assertTrue(actualAlter.contains(expectedAlter), "Test case is fail");
		
		log.info("test complete");

	}

	@Parameters({ "username", "password", "expectedmessage" })
	@Test(priority = 3, groups = { "negative-testing" })
	public void negativeLoginTest(@Optional("tomsmith")String username,@Optional("incorrectpassword") String password, @Optional("Your password is invalid!") String expectedErrorMessage) {

		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);

		WebElement usernameElement = driver.findElement(By.id("username"));
		WebElement passwordElement = driver.findElement(By.id("password"));
		WebElement logInButton = driver.findElement(By.tagName("button"));

		usernameElement.sendKeys(username);
		passwordElement.sendKeys(password);
		logInButton.click();

		sleep(3000);
//		WebElement alert = driver.findElement(By.className("flash success"));
//		WebElement pageHeader = driver.findElement(By.tagName("h2"));
//		WebElement pageSubHeader = driver.findElement(By.className("subheader"));
		WebElement actualAlterElement = driver.findElement(By.xpath("//div[@id='flash']"));

		String actualAlter = actualAlterElement.getText();
//		"Your password is invalid!";

		Assert.assertTrue(actualAlter.contains(expectedErrorMessage), "Test case is fail");

	}

}
