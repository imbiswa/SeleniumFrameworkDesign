package Ekart.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Ekart.pageobjects.CartPage;
import Ekart.pageobjects.CheckoutPage;
import Ekart.pageobjects.ConfirmationPage;
import Ekart.pageobjects.OrderPage;
import Ekart.pageobjects.ProductCatalogue;
import TestComponenets.BaseTest;
public class SubmitOrderTest extends BaseTest{
	String productName = "ZARA COAT 3";
	
	   @Test(dataProvider = "getData", groups= {"Purchase"})
	   public void submitOrder( HashMap <String,String> input) throws IOException, InterruptedException
			{
			    
			   
			   // landingpage = launchApplication();
			    ProductCatalogue productCatalogue=landingpage.loginApplication(input.get("email"), input.get("password"));
				List<WebElement> products =productCatalogue.getProductList();
				productCatalogue.addProductToCart(input.get("product"));
				CartPage cartPage =productCatalogue.goToCartPage();
				Boolean match  = cartPage.VerifyproductDisplay(input.get("product"));
				Assert.assertTrue(match);
				CheckoutPage checkoutPage =cartPage.goToCheckout();
				checkoutPage.selectCountry("India");
				ConfirmationPage confirmationPage =checkoutPage.submitOrder();
				String confirmMessage  = confirmationPage.getConfirmationMessage();
				Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
				System.out.println(confirmMessage);
			
		}
	   
	   
	   @Test(dependsOnMethods = {"submitOrder"})
	   public void orderHistoryTest()
	   {
		   ProductCatalogue productCatalogue=landingpage.loginApplication("biswam@gmail.com", "B@123456b");
		   OrderPage orderPage= productCatalogue.goToOrderPage();
		   Assert.assertTrue(orderPage.VerifyOrdertDisplay(productName));
	   }
	   
	   @DataProvider
	   public Object[][] getData()
	   {
		   HashMap <String,String > map = new HashMap <String,String>();
		   map.put("email", "biswam@gmail.com");
		   map.put("password", "B@123456b");
		   map.put("product", "ZARA COAT 3");
		   
		   HashMap <String,String > map1 = new HashMap <String,String>();
		   map1.put("email", "shetty@gmail.com");
		   map1.put("password", "Iamking@000");
		   map1.put("product", "ADIDAS ORIGINAL");
		   
		   return new Object [][] {{map},{map1}};
		   
		   //object here we can send generic data may be string /int/char anything
	
	   }

	
		
		
}

