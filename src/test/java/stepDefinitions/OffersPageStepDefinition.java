package stepDefinitions;

import org.testng.Assert;

import Utils.TestContextSetup;
import io.cucumber.java.en.Then;
import pageObjects.LandingPage;
import pageObjects.OffersPage;

//Single Responsibility Principle(SRP)-It means to have separated with the functionalities like here we have separate classes for all objects,xpaths etc.
//Factory Designed Pattern-
public class OffersPageStepDefinition {
	public String offerPageProductName;
	TestContextSetup testcontextsetup;
//	PageObjectManager pageObjectManager;
	
	// constructor(to implement from another class)
	public OffersPageStepDefinition(TestContextSetup testcontextsetup) {
		this.testcontextsetup = testcontextsetup;
	}

	
	@Then("^User searched with same short name (.+) in offers page to check if product exists$")
	public void user_searched_with_same_short_name_in_offers_page_to_check_if_product_exists(String shortName)
			throws InterruptedException {
		
		switchToOffersPage();
		//invoked from OffersPage class
		//OffersPage offersPage=new OffersPage(testcontextsetup.driver);
		
		OffersPage offersPage=testcontextsetup.pageObjectManager.getOffersPage();
		offersPage.searchItem(shortName);
		//testcontextsetup.driver.findElement(By.id("search-field")).sendKeys(shortName);
		Thread.sleep(3000);
		offerPageProductName = offersPage.getProductName();
		System.out.println(offerPageProductName + " is extracted from offer page.");
	}

	
	public void switchToOffersPage() {

		//LandingPage landingPage=new LandingPage(testcontextsetup.driver);
		LandingPage landingPage=testcontextsetup.pageObjectManager.getLandingPage();
		landingPage.selectTopDeals();
		// browser opens on other window to get other window
		// s1 handle 2 windows as 1 so need to use iterator
//		Set<String> s1 = testcontextsetup.driver.getWindowHandles();
//		Iterator<String> i1 = s1.iterator();
//		// to get 2nd window(child window)
//		String ParentWindow = i1.next();
//		String ChildWindow = i1.next();
//		testcontextsetup.driver.switchTo().window(ChildWindow);
		
		
		testcontextsetup.genericUtils.switchWindowToChild();
	}

	@Then("Verify both the names that we get same or not")
	public void verify_both_the_names_that_we_get_same_or_not() throws InterruptedException {
		Assert.assertEquals(testcontextsetup.landingPageProductName, offerPageProductName, "Both are not same");
		Thread.sleep(3000);
		
		
	}
}
