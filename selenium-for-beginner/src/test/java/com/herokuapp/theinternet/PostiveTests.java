package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class PostiveTests {
	@Test
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
