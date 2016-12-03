package roi4cio.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import roi4cio.add.product.MyOfficePage1;
import roi4cio.login.data.UserModel;

public class LoginPage {
	private WebDriver driver;

	@FindBy(name = "user")
	private WebElement login;

	@FindBy(name = "pass")
	private WebElement password;

	@FindBy(name = "submit")
	private WebElement submitButton;

	@FindBy(className = "four fields")
	private WebElement errorMessage;
	@FindBy(className = "segment")
	private WebElement loginForm;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public MyProfilePage loginAs(String emails, String pass) {
		login.sendKeys(emails);
		password.sendKeys(pass);
		submitButton.click();
		return new MyProfilePage(driver);
	}

	public String error() {
		return errorMessage.getText();

	}

	public boolean isLoginButtonDisplayed() {
		return submitButton.isDisplayed();
	}

	public boolean isLoginFormDisplayed() {
		return loginForm.isDisplayed();
	}

	public MyProfilePage loginAs(UserModel user) {
		return loginAs(user.getEmail(), user.getPassword());
	}
}
