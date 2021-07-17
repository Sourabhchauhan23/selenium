package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NegativeTests {

	@Test(priority =1, groups = {"smoke-testing","negative-testing"})
	public void incorrectUsername() {
		
		System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
		
		ProfilesIni profile = new ProfilesIni();
        FirefoxProfile testprofile = profile.getProfile("Selenium");
        FirefoxOptions foptions = new FirefoxOptions();
        foptions.setProfile(testprofile);
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("marionatte", false);
        foptions.merge(dc);
		
		WebDriver driver = new FirefoxDriver(foptions);
		
		String url="https://the-internet.herokuapp.com/login";
		driver.get(url);
		
		
		sleep(3000);
		
		driver.manage().window().maximize();
		
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
		
		
		String actualAlter= actualAlterElement.getText();
		String expectedAlter = "Your username is invalid!";
		
		Assert.assertTrue(actualAlter.contains(expectedAlter), "Test case is fail");
		
		sleep(2000);
		
		driver.close();
		driver.quit();
		
	}
	
	@Test(priority = 2, groups = {"negative-testing"})
	public void incorrectPassword() {
		
		System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		
		String url="https://the-internet.herokuapp.com/login";
		driver.get(url);
		
		
		sleep(3000);
		
		driver.manage().window().maximize();
		
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
		
		
		String actualAlter= actualAlterElement.getText();
		String expectedAlter = "Your password is invalid!";
		
		Assert.assertTrue(actualAlter.contains(expectedAlter), "Test case is fail");
		
		sleep(2000);
		
		driver.close();
		driver.quit();
		
	}
	
	@Parameters({"username","password","expectedmessage"})
	@Test(priority = 3, groups = {"negative-testing"})
	public void negativeLoginTest(String username, String password, String expectedErrorMessage) {
		
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		String url="https://the-internet.herokuapp.com/login";
		driver.get(url);
		
		
		sleep(3000);
		
		driver.manage().window().maximize();
		
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
		
		
		String actualAlter= actualAlterElement.getText();
//		"Your password is invalid!";
		
		Assert.assertTrue(actualAlter.contains(expectedErrorMessage), "Test case is fail");
		
		sleep(2000);
		
		driver.close();
		driver.quit();
		
	}
	

	private void sleep(int m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
