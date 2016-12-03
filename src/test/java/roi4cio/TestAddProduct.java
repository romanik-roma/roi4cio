package roi4cio;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static roi4cio.login.data.Users.*;
import roi4cio.login.AddProductPage;
import roi4cio.login.HomePage;
import roi4cio.login.LoginPage;
import roi4cio.login.MyProfilePage;
import roi4cio.login.MyProductsPage;
import roi4cio.login.ROI4CIO;

public class TestAddProduct {
	private ROI4CIO roi = new ROI4CIO();
	
	private MyProfilePage myProfilePage;
	private MyProductsPage myProductsPage;
	private AddProductPage addProductPage;
	private ProductModel testProductData;
	@BeforeMethod
	private void setUp() {
		testProductData = generateProduct();
		myProfilePage = roi.openHomePage().clickOnLoginIcon().loginAs(VALID_USER);
	}


	@Test(priority = 1)
	public void testAddProduct() {
		addProductPage = myProfilePage.goToMyProductsPage().addProduct();

		addProductPage.addTitleForProduct("svf", "The Best product in whole");
		addProductPage.clickOneToggle();
		addProductPage.clickOnCategore();
		myProductsPage = addProductPage.save();
		
		//addProductPage.createProduct(bestProduct);
		Assert.assertTrue(myProductsPage.productNameList().contains("svf"));
		//Assert.assertTrue(addProductPage.myProductsList());
	}
	
	@Test(priority = 0)
	public void testAddProduct_oopVersion() {
		addProductPage = myProfilePage.goToMyProductsPage().addProduct();

		addProductPage.addProduct(testProductData);
		
		Assert.assertTrue(myProductsPage.productTable().contains(testProductData));
	}

	@Test(priority = 2)
	public void testAddProductWithSameName() {
		myProductsPage = myProfilePage.goToMyProductsPage();
		addProductPage = myProductsPage.addProduct();
		addProductPage.addTitleForProduct(" svf ", "The Best product in whole");
		addProductPage.clickOneToggle();
		addProductPage.clickOnCategore();
		addProductPage.clickSaveButton();
		Assert.assertFalse(addProductPage.myProductsList(), "Product with the same title couldn't be created");
	}

	@Test
	public void testAddProductWithEmptyCategory() {
		myProductsPage = myProfilePage.goToMyProductsPage();
		addProductPage = myProductsPage.addProduct();
		addProductPage.addTitleForProduct(" svf ", "The Best product in whole");
		addProductPage.clickOneToggle();
		addProductPage.clickSaveButton();
		Assert.assertEquals(addProductPage.textErrorMessage(),
				"��������� ������" + "\n��������� ��������: ������ ������ ��������.");

	}

	@Test
	public void testAddPdocutWithEmptyDeliveryAndCategory() {
		myProductsPage = myProfilePage.goToMyProductsPage();
		addProductPage = myProductsPage.addProduct();
		addProductPage.addTitleForProduct(" svf ", "The Best product in whole");
		addProductPage.clickSaveButton();
		Assert.assertEquals(addProductPage.textErrorMessage(), "��������� ������"
				+ "\n��� ��������: ������ ������ ��������." + "\n��������� ��������: ������ ������ ��������.");
	}

	@Test
	public void testAddProductWithEmptyMandatoryFields() {
		myProductsPage = myProfilePage.goToMyProductsPage();
		addProductPage = myProductsPage.addProduct();
		addProductPage.addTitleForProduct(" ", " ");
		addProductPage.clickSaveButton();
		Assert.assertEquals(addProductPage.textErrorMessage(),
				"��������� ������" + "\n��������: ������ ������ ��������." + "\n��� ��������: ������ ������ ��������."
						+ "\n��������� ��������: ������ ������ ��������.");
	}

	@Test
	public void testAddProductWithoutCountry() {
		myProductsPage = myProfilePage.goToMyProductsPage();
		addProductPage = myProductsPage.addProduct();
		addProductPage.addTitleForProduct(" svf14 ", "The Best product in whole");
		addProductPage.clickOneToggle();
		addProductPage.clickOnCategore();
		addProductPage.selectCountry();
		addProductPage.clickSaveButton();
		Assert.assertEquals(addProductPage.textErrorMessage(),
				"��������� ������" + "\n������: ������ ������ ��������.");
	}

	@Test
	public void testAddProductWithEmptyAllFields() {
		myProductsPage = myProfilePage.goToMyProductsPage();
		addProductPage = myProductsPage.addProduct();
		addProductPage.addTitleForProduct(" ", " ");
		addProductPage.selectCountry();
		addProductPage.clickSaveButton();
		Assert.assertEquals(addProductPage.textErrorMessage(),
				"��������� ������" + "\n��������: ������ ������ ��������." + "\n������: ������ ������ ��������."
						+ "\n��� ��������: ������ ������ ��������." + "\n��������� ��������: ������ ������ ��������.");
	}

	@Test
	public void testAddProductConfirmationPopup() {
		myProductsPage = myProfilePage.goToMyProductsPage();
		addProductPage = myProductsPage.addProduct();
		addProductPage.addTitleForProduct("Test ", " ");
		addProductPage.selectCountry();
		addProductPage.clickOnFlag();
		Assert.assertTrue(addProductPage.Popup());
	}

	@Test
	public void testAddProductCickOnYesInConfirmationPopup() {
		myProductsPage = myProfilePage.goToMyProductsPage();
		addProductPage = myProductsPage.addProduct();
		addProductPage.addTitleForProduct("Test ", " ");
		addProductPage.selectCountry();
		addProductPage.clickOnFlag();
		addProductPage.clickOnYesButton();
		Assert.assertTrue(addProductPage.englishPageAddProduct());
	}

	@Test
	public void testAddProductCickOnYesInConfirmationPopupAfterErrors() {
		myProductsPage = myProfilePage.goToMyProductsPage();
		addProductPage = myProductsPage.addProduct();
		addProductPage.addTitleForProduct("Test ", " ");
		addProductPage.clickSaveButton();
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addProductPage.clickOneToggle();
		addProductPage.clickOnFlag();
		addProductPage.clickOnYesButton();
		Assert.assertTrue(addProductPage.englishPageAddProduct());
	}

	@Test
	public void testAddProductWithFilledAllFields() {
		myProductsPage = myProfilePage.goToMyProductsPage();
		addProductPage = myProductsPage.addProduct();
		addProductPage.addTitleForProduct(" ", " ");
		addProductPage.clickSaveButton();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addProductPage.addTitleForProduct(" TEST1 ", "The Best product in whole");
		addProductPage.clickOneToggle();
		addProductPage.clickOnCategore();
		addProductPage.clickSaveButton();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(addProductPage.myProductsList());
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		roi.close();
	}
	

	private ProductModel generateProduct() {
		return new ProductModel("Soap", "Software", "Appliance");
	}
}
