package roi4cio.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyProfilePage {

	private WebDriver driver;

	// @FindBy(xpath = "//*[@href='moi-kabinet/moi-produkty/']")
	
	@FindBy(css = "[href='moi-kabinet/moi-produkty/']")
	private WebElement myPage;

	@FindBy(xpath = "(//*[@href='login/?logintype=logout'])[2]")
	private WebElement logout;

	@FindBy(xpath = "//*[@class='right menu']/a")
	private WebElement myData;

	public MyProfilePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);

	}

	public MyProductsPage goToMyProductsPage() {
		WebDriverWait waiter = new WebDriverWait(driver, 10);
		waiter.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath("//*[@href='moi-kabinet/moi-produkty/']")));
		doNothingFor(3000);
		this.myPage.click();
		return new MyProductsPage(driver);
	}

	public LoginPage clickOnLogout() {
		logout.click();
		return new LoginPage(driver);
	}

	public boolean isMyDataIconDisplayed() {
		return myData.isDisplayed();
	}
	
	private void doNothingFor(int seconds) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
