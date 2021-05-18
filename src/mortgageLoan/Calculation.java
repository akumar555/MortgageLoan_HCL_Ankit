package mortgageLoan;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Calculation {

	public static void main(String[] args) {
		//LAUNCH BROWSER AND WEBSITE
		//-------------------------------------------
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\akumar555.EAD\\OneDrive - DXC Production\\Selenium Training 20th April 2020\\Workspace\\MortgageLoan\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.get("https://www.mortgageloan.com/calculator");
		//-------------------------------------------
		
		//GET YOUR FREE QUOTE MESSAGE CLOSE
		//-------------------------------------------
		WebDriverWait wait = new WebDriverWait(driver, 12 /*timeout in seconds*/);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Get Your FREE Quote!')]")));
		driver.findElement(By.xpath("//span[contains(text(),'×')]")).click();
		//-------------------------------------------
		
		
		//ENTER MORTGAGE AMOUNT
		driver.findElement(By.xpath("//input[@id='KJE-LOAN_AMOUNT']")).clear();
		driver.findElement(By.xpath("//input[@id='KJE-LOAN_AMOUNT']")).sendKeys("$200,000");

		//SELECT TERMS IN YEARS
		Select termsInYears = new Select(driver.findElement(By.xpath("//select[@id='KJE-TERM']")));
		termsInYears.selectByVisibleText("30 years");
		
		//ENTER INTEREST RATE
		driver.findElement(By.xpath("//input[@id='KJE-INTEREST_RATE']")).clear();
		driver.findElement(By.xpath("//input[@id='KJE-INTEREST_RATE']")).sendKeys("5%");
		
		
		//CLICK ANNUALLY RADIO BUTTON
		driver.findElement(By.xpath("//input[@id='KJE-BY_YEAR1']")).click();
		
		
		//VERIFY MONTHLY PAYMENT
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("KJE-MONTHLY_PAYMENT")));
		String monthlyPayment = driver.findElement(By.id("KJE-MONTHLY_PAYMENT")).getText();
		Assert.assertEquals("$1,073.64", monthlyPayment);
		System.out.println("MONTHLY PAYMENT: " + monthlyPayment);
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("KJEGraphTitle")));
		String totalPayment = driver.findElement(By.className("KJEGraphTitle")).getText();

		
		//VERIFY TOTAL PAYMENT
		if (totalPayment.contains("Total Payments $386,513"))
		{
			System.out.println("TOTAL PAYMENT: $386,513");
		}
		else
		{
			System.out.println("INCORRECT TOTAL PAYMENT");
		}
		
		//VERIFY TOTAL INTEREST
		if (totalPayment.contains("Total Interest $186,513"))
		{
			System.out.println("TOTAL INTEREST: $186,513");
		}
		else
		{
			System.out.println("INCORRECT TOTAL INTEREST");
		}
	}

}
