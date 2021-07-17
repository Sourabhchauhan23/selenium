package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTest {

	private WebDriver driver;

	@Parameters("browser")
	@BeforeMethod(alwaysRun = true)
	private void setUp(@Optional("chrome") String browser) {

		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver=new ChromeDriver();
			break;

		case "firefox":
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			driver=new FirefoxDriver();
			break;

		default:
			System.out.println("Can not identify" + browser + "broser, start chrome browser instead");
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver=new ChromeDriver();
			break;
		}

		sleep(3000);

		driver.manage().window().maximize();
	}

	@Test(priority = 1, groups = { "smoke-testing" })
	public void loginTest1() {

		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);

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

		String actualURL = driver.getCurrentUrl();
		String expectedURL = "https://the-internet.herokuapp.com/secure";

		Assert.assertEquals(actualURL, expectedURL, "Test case is PASS");
		Assert.assertTrue(logoutButton.isDisplayed(), "Logout button is not visible");

		logoutButton.click();

		sleep(2000);

	}

	@Parameters({ "username", "password", "expectedmessage" })
	@Test(priority = 2, groups = { "negative-testing" })
	public void negativeLoginTest(String username, String password, String expectedErrorMessage) {

		String url = "https://the-internet.herokuapp.com/login";
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

		String actualAlter = actualAlterElement.getText();
//		"Your password is invalid!";

		Assert.assertTrue(actualAlter.contains(expectedErrorMessage), "Test case is fail");

		sleep(2000);

	}

	@AfterMethod(alwaysRun = true)
	private void tearDown() {
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
