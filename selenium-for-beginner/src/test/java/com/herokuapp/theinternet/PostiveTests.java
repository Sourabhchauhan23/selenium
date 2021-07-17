package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PostiveTests {
	@Test(priority = 1, groups = {"smoke-testing"})
	public void loginTest() {
		
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		String url="https://the-internet.herokuapp.com/login";
		driver.get(url);
		
		
		sleep(3000);
		
		driver.manage().window().maximize();
		
		WebElement username = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement logInButton = driver.findElement(By.tagName("button"));

		
		username.sendKeys("tomsmith");
		password.sendKeys("SuperSecretPassword!");
		logInButton.click();

		
		sleep(3000);
//		WebElement alert = driver.findElement(By.className("flash success"));
//		WebElement pageHeader = driver.findElement(By.tagName("h2"));
//		WebElement pageSubHeader = driver.findElement(By.className("subheader"));
		WebElement logoutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
		
		
		String actualURL= driver.getCurrentUrl();
		String expectedURL = "https://the-internet.herokuapp.com/secure";
		
		Assert.assertEquals(actualURL, expectedURL, "Test case is PASS");
		Assert.assertTrue(logoutButton.isDisplayed(), "Logout button is not visible");
		
		logoutButton.click();
		
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
