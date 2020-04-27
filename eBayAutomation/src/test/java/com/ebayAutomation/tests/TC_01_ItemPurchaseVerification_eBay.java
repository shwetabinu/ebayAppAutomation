package com.ebayAutomation.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.ebayAutomation.base.BaseClass;
import com.ebayAutomation.pages.CheckoutPage;
import com.ebayAutomation.pages.HomePage;
import com.ebayAutomation.pages.ProductDetailPage;
import com.ebayAutomation.pages.SearchResultsPage;
import com.ebayAutomation.pages.ShoppingCartPage;
import com.ebayAutomation.pages.SignInPage;
import com.ebayAutomation.util.ExcelUtil;
import com.ebayAutomation.util.Log;


public class TC_01_ItemPurchaseVerification_eBay extends BaseClass {

	public TC_01_ItemPurchaseVerification_eBay() throws Exception {
		super();
		
	}
	
	/**
	 * TestCase:
	 * Loads the environmental configuration and Item details from the TestData file and initializes the app.
	 * Searches for the specified item - Validates that the search result page is displayed
	 * Scrolls down until the element is in view and selects the item
	 * Adds the item to cart.
	 * Navigate to the cart and proceed to checkout
	 * Validates that the item description in the product detail page matches with the checkout page.
	 */
	@Test()
	public void Verify_eBay_Order_Placement() throws Exception
	{
		Log.startTestCase("Verify_eBay_Order_Placement");
		int rno;
		ExcelUtil.setExcelFileSheet("Testcases");
		rno=ExcelUtil.readexcel("TC_01_ItemPurchaseVerificationeBay");
		initApp(rno);
		
		HomePage hp1=new HomePage();
		hp1.searchForItem(rno);
		
		SearchResultsPage srp=new SearchResultsPage();
		
		boolean searchpage_heading=srp.verifyShopByBrandHeading(rno);
		Assert.assertTrue(searchpage_heading, "Assertion Error: Error while searching for the product, Search result page not displayed");
		
		boolean selecitem=srp.selectItem(rno);
		Assert.assertTrue(selecitem, "Assertion Error: Error while selecting the product; Product not displayed in list");
		
		ProductDetailPage pd=new ProductDetailPage();
		String product_desc=pd.getProductDescription();
		
		boolean addtocart=pd.addToCart();
		Assert.assertTrue(addtocart, "Assertion Error: Error finding Add to Cart option");
		
		SignInPage sp=new SignInPage();
		sp.signInThroughFacebook();
		
		ProductDetailPage pd1=new ProductDetailPage();
		boolean addedtocart=pd1.verifyItemAddedToCart();
		Assert.assertTrue(addedtocart, "Assertion Error: Error adding item to the cart");
		
		boolean gotocart=pd1.clickOnGoToCart();
		Assert.assertTrue(gotocart, "Assertion Error: Error in navigating to the cart");
		
		ShoppingCartPage scp=new ShoppingCartPage();
		boolean checkoutpage=scp.continueToCheckout();
		Assert.assertTrue(checkoutpage, "Assertion Error: Error in navigating to the checkout page");
		
		CheckoutPage cp=new CheckoutPage();
		String checkout_item=cp.getItemDescription();
		
		Assert.assertEquals(product_desc, checkout_item, "Assertion Error: Error validating product name");
	}
}
