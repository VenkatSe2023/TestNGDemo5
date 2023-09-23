package mercury;

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
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MercuryTours {
	
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
		driver.get("http://www.mercury-tours.com/");
		//declaring implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		//declaring explicit wait
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	@AfterMethod
	public void navigateToHomePage() {
		driver.findElement(By.xpath("//div[@id='Navagation']/strong/a[1]")).click();
	}
	
	@Test(priority = 1)
	public void verifyOurCoachesPage() {
		WebElement link_Coaches = driver.findElement(By.xpath("//div[@id='Navagation']/strong/a[2]"));
		link_Coaches.click();
		
		wait.until(ExpectedConditions.stalenessOf(link_Coaches));
		
		String actaul = driver.getTitle();
		String expected = "Mercury Tours Our Coaches Page";
		
		Assert.assertEquals(actaul, expected);
		
	}
	
	@Test(priority = 2)
	public void verifyContactUsPage() {
		WebElement link_ContactUs = driver.findElement(By.xpath("//div[@id='Navagation']/strong/a[3]"));
		link_ContactUs.click();

		wait.until(ExpectedConditions.stalenessOf(link_ContactUs));
		
		String actaul = driver.getTitle();
		String expected = "Mercury Tours Contact Us Page";
		
		Assert.assertEquals(actaul, expected);
	}
	
	@Test(priority = 3)
	public void verifySFToursPage() {
		WebElement link_SFTours = driver.findElement(By.xpath("//div[@id='Navagation']/strong/a[4]"));
		link_SFTours.click();
		
		wait.until(ExpectedConditions.stalenessOf(link_SFTours));
		
		String actaul = driver.getTitle();
		String expected = "Mercury Tours San Francisco Tours Page";
		
		Assert.assertEquals(actaul, expected);
	}

	@Test(priority = 4)
	public void verifySepcilizedToursPage() {
		WebElement link_SpecializedTours = driver.findElement(By.xpath("//div[@id='Navagation']/strong/a[5]"));
		link_SpecializedTours.click();
		
		wait.until(ExpectedConditions.stalenessOf(link_SpecializedTours));
		
		String actaul = driver.getTitle();
		String expected = "Mercury Tours Specialized Tours Page";
		
		Assert.assertEquals(actaul, expected);
	}
	
	@AfterClass
	public void tearDown() {
		System.out.println("closing operations");
		
		driver.close();
	}
}
