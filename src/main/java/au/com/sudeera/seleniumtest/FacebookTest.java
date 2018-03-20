package au.com.sudeera.seleniumtest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FacebookTest {
	private WebDriver driver;

	@BeforeClass
	public void setup() {
		// Can use phantomJs as a headless browser
		WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
		driver = new ChromeDriver();
	}

	@BeforeMethod
	public void beforeTest() {
		driver.get("http://facebook.com.au");
		driver.manage().window().maximize();
	}

	// TestNG supports negative priorities
	@Test(priority = 1, description = "Test login to Facebook")
	@Parameters(value= {"email", "password"})
	public void test_valid_test(String email, String password) {
		//email and password is provided in the testing.xml "<parameter>" tag
		WebElement emailElement = driver.findElement(By.id("email"));
		emailElement.sendKeys(email);
		WebElement passwordElement = driver.findElement(By.id("pass"));
		passwordElement.sendKeys(password);
		WebElement loginButton = driver.findElement(By.id("loginbutton"));
		loginButton.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println(driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "Facebook");
	}

	@Test(priority = 2, dependsOnMethods = "test_valid_test", description = "Test log out from Facebook")
	public void test_logout() {
		driver.findElement(By.id("userNavigationLabel")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.findElement(By.partialLinkText("Log Out")).click();
		Assert.assertEquals(driver.getTitle(), "Facebook – log in or sign up");
	}

	@Test(priority = 3, dependsOnMethods = "test_logout", description = "Test invalid login to Facebook")
	public void test_invalid_test() {
		try {
			WebElement email = driver.findElement(By.id("email"));
			email.sendKeys("mumbo_s5@yahoo.com");
			WebElement password = driver.findElement(By.id("pass"));
			password.sendKeys("");
			WebElement loginButton = driver.findElement(By.id("loginbutton"));
			loginButton.click();
			System.out.println(driver.getTitle());
			Assert.assertEquals(driver.getTitle(), "Log in to Facebook | Facebook");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}
}
