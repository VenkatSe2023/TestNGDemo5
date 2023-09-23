package search;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import testdata.SearchData;

public class YahooSearch {
	public WebDriver driver;
	public WebDriverWait wait;
	
	@Parameters("browserType")
	@BeforeClass
	public void setUp(String testBrowser) {
		System.out.println("initial setup code");
		
		if(testBrowser.equalsIgnoreCase("Chrome"))
			driver = new ChromeDriver();
		else if(testBrowser.equalsIgnoreCase("Firefox"))
			driver = new FirefoxDriver();
		else if(testBrowser.equalsIgnoreCase("Edge"))
			driver = new EdgeDriver();
	
		driver.manage().window().maximize();
		driver.get("https://www.yahoo.com/");
		//declaring implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		//declaring explicit wait
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	@AfterMethod
	public void backToMainPage() {
		driver.navigate().back();
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@id='ybar-search']"))));
	}
	
	@Test(dataProviderClass = SearchData.class, dataProvider = "searchdata")
	public void doSearch(String searchTerm) {
		driver.findElement(By.xpath("//input[@name='p']")).sendKeys(searchTerm);
		WebElement btnSearch = driver.findElement(By.xpath("//button[@id='ybar-search']"));
		btnSearch.click();
		
		wait.until(ExpectedConditions.stalenessOf(btnSearch));
		
		boolean actual = driver.getTitle().contains(searchTerm);
		boolean expected = true;
		
		Assert.assertEquals(actual, expected);
	}
	
	
	@AfterClass
	public void tearDown() {
		System.out.println("closing operations");
		
		driver.close();
	}
}
