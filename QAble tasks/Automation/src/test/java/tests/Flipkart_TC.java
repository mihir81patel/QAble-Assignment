package tests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.HomePage;

public class Flipkart_TC {
	
		WebDriver driver;
	    HomePage homePage;
		
		@BeforeTest
		public void launchFlipkart() {
			System.setProperty("webdriver.chrome.driver", "C:\\New folder\\MyWeb\\QAble tasks\\Automation\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.get("https://www.flipkart.com/");
			driver.manage().window().maximize();
			homePage = new HomePage(driver);
		}
		
		@Test
		public void search_Mobile_Phone_Test() {
			homePage.searchPhones();
			homePage.filterPhones();
			homePage.addPhoneToCart();
		}
		
		@AfterTest
		public void closeSession() {
			driver.quit();
		}
		


}
