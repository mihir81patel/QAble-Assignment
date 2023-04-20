package pages;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage{
	
	protected WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;

	public HomePage(WebDriver driver){
		this.driver = driver;
		wait = new WebDriverWait(driver, 60);
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;
	}
	
	
	 @FindBy(xpath = "//div[@class='_2QfC02']/child::button[text()='✕']") 
	 private WebElement byBtnClosePopUp;
	 
	 @FindBy(name = "q")
	 private WebElement byTxtSearchBox;
	 
	 @FindBy(xpath = "//button[@class='L0Z3Pu']")
	 private WebElement searchBtn;
	 
	 @FindBy(xpath = "//span[text()='Filters']")
	 private WebElement filterTitle;
	 
	 @FindBy(xpath = "//div[@class='_1YAKP4']/select[@class='_2YxCDZ']")
	 private WebElement mindrpdwn;
	 
	 @FindBy(xpath = "//div[@class='_3uDYxP']/select[@class='_2YxCDZ']")
	 private WebElement maxdrpdwn;
	 
	 @FindBy(xpath = "//input[@class='_34uFYj' and @placeholder='Search Brand']")
	 private WebElement searchBrandBox;
	 
	 @FindBy (xpath = "//div[@title='REDMI']")
	 private WebElement brandSelectorChkBox;
	 
	 @FindBy (xpath = "//div[text()='Price -- Low to High']")
	 private WebElement sortByLwstPrice;
	 
	 @FindBy (xpath = "(//div[@class='_30jeq3 _1_WHN1'])[1]")
	 private WebElement cheapestPricedPhone;
	 
	 @FindBy (xpath = "(//div[@class='_4rR01T'])[1]")
	 private WebElement cheapestPricedPhoneName;
	 
	 @FindBy (xpath = "//button[text()='Add to cart']")
	 private WebElement addCrtBtn;
	 
	 @FindBy (xpath = "//div[text()='Remove']")
	 private WebElement byBtnRemoveItem;
	 
	 @FindBy (xpath = "//div[@class='td-FUv WDiNrH']/descendant::div[text()='Remove']")
	 private WebElement byBtnRemoveFromPopUp;
	 
	 @FindBy(xpath = "//div[@class='_2sKwjB']")
	 private WebElement byMessageItemRemoved;
	 
	 @FindBy(xpath = "//div[@class='_2kHMtA']")
	 private List<WebElement> byLstOfProducts;
	 
	 
	 /**
		 * searching the phones by entering appropriate text into search field
		 */
	 public void searchPhones() {
		 byBtnClosePopUp.click();
		 byTxtSearchBox.sendKeys("phones");
		 searchBtn.click();
	 }
	 
	 /**
		 * applying various filters on the search results displayed and sorting
		 * the filtered search results in ascending order
		 */
	 public void filterPhones() {
		 wait.until(ExpectedConditions.visibilityOf(filterTitle));
		 Select minPricedrpdwn = new Select(mindrpdwn);
		 try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 minPricedrpdwn.selectByIndex(1);
		 js.executeScript("arguments[0].scrollIntoView()", searchBrandBox);
		 wait.until(ExpectedConditions.elementToBeClickable(searchBrandBox));
		 Select maxPricedrpdwn = new Select(maxdrpdwn);
		 maxPricedrpdwn.selectByIndex(2);
		 js.executeScript("arguments[0].scrollIntoView()", searchBrandBox);
		 wait.until(ExpectedConditions.elementToBeClickable(searchBrandBox));
		 
		 searchBrandBox.sendKeys("redmi");
		 wait.until(ExpectedConditions.visibilityOf(brandSelectorChkBox));
		 wait.until(ExpectedConditions.elementToBeClickable(brandSelectorChkBox));
		 waitAndClick(brandSelectorChkBox);
		 wait.until(ExpectedConditions.visibilityOf(sortByLwstPrice));
		 wait.until(ExpectedConditions.elementToBeClickable(sortByLwstPrice));
		 waitAndClick(sortByLwstPrice);
		 wait.until(ExpectedConditions.visibilityOf(cheapestPricedPhoneName));
		 wait.until(ExpectedConditions.visibilityOf(cheapestPricedPhone));
		 System.out.println("Name of cheapest phone is: "+cheapestPricedPhoneName.getText()+" and price is: "+cheapestPricedPhone.getText());
	 }
	 
	 /**
		 * adding the cheapest phone into the cart
		 * and removing it from cart
		 */
	 public void addPhoneToCart() {
		 String parent=driver.getWindowHandle();
		 //wait.until(ExpectedConditions.elementToBeClickable(cheapestPricedPhone));
		 waitAndClick(byLstOfProducts.get(0));
		 try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			 Set<String> s=driver.getWindowHandles();
			 Iterator<String> I1= s.iterator();
			 while(I1.hasNext()){
				 String child_window=I1.next();
				 if(!parent.equals(child_window)) {
					 driver.switchTo().window(child_window);
					 js.executeScript("arguments[0].scrollIntoView()", addCrtBtn);
					 Assert.assertEquals(addCrtBtn.isEnabled(), true);
					 waitAndClick(addCrtBtn);
					 wait.until(ExpectedConditions.invisibilityOf(byBtnRemoveItem));
					 waitAndClick(byBtnRemoveItem);
					 waitAndClick(byBtnRemoveFromPopUp);
					 Assert.assertEquals(byMessageItemRemoved.isDisplayed(), true);
					 driver.close();
				 }
			 }
			 driver.switchTo().window(parent);

	 }
	 
	 /**
		 * common method perform wait operation
		 * before clicking on any element
		 */
	 public void waitAndClick(WebElement element) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			element.click();
		}
}
