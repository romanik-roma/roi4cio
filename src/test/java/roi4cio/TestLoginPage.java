package roi4cio;

import static roi4cio.login.data.Users.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import roi4cio.login.AddProductPage;
import roi4cio.login.HomePage;
import roi4cio.login.LoginPage;
import roi4cio.login.MyProductsPage;
import roi4cio.login.MyProfilePage;
import roi4cio.login.ROI4CIO;

public class TestLoginPage {
	private ROI4CIO roi = new ROI4CIO();
	private HomePage homePage;
	private LoginPage loginPage;
	private MyProfilePage myProfilePage;
	private MyProductsPage addProductToMyList;
	private AddProductPage addProduct;

	@BeforeMethod
	private void setUp() {
		homePage = roi.openHomePage();
	}

	@Test
	public void ValidInputDataForlogin() {
		myProfilePage = homePage.clickOnLoginIcon().loginAs(VALID_USER);
		Assert.assertTrue(myProfilePage.isMyDataIconDisplayed());
	}

	@Test
	public void InvalidInputDataForLogin() {
		myProfilePage = homePage.clickOnLoginIcon().loginAs(INVALID_USER);
		Assert.assertTrue(loginPage.isLoginButtonDisplayed());
	}

	@Test
	public void LogoutAfterLogin() {
		loginPage = homePage.clickOnLoginIcon().loginAs(VALID_USER).clickOnLogout();
		Assert.assertTrue(loginPage.isLoginFormDisplayed());
	}

	@Test
	public void EmailAndPasswordFieldsAreEmpty() {
		loginPage = homePage.clickOnLoginIcon();
		myProfilePage = loginPage.loginAs("", "");
		Assert.assertTrue(loginPage.isLoginButtonDisplayed());
	}

	@Test
	public void EmailFieldIsEmpty() {
		loginPage = homePage.clickOnLoginIcon();
		myProfilePage = loginPage.loginAs("", "12345678");
		Assert.assertTrue(loginPage.isLoginButtonDisplayed());
	}

	@Test
	public void PasswordFieldIsEmpty() {
		loginPage = homePage.clickOnLoginIcon();
		myProfilePage = loginPage.loginAs("opitsyc@gmail.com", "");
		Assert.assertTrue(loginPage.isLoginButtonDisplayed());
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		roi.close();
	}
}
