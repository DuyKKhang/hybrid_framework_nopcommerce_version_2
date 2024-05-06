package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBase {
//	WebDriver driver;
//	
//	public PageBase(WebDriver driver) {
//		this.driver = driver;
//	}

	// Web browser commons
	public void getUrl(WebDriver driver, String url) {
		driver.get(url);
	}

	public String getTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getCurrentURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshToPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Alert waitAlertPresece(WebDriver driver) {
		return new WebDriverWait(driver, 30).until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		waitAlertPresece(driver).accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitAlertPresece(driver).dismiss();
	}

	public String getTexttAlert(WebDriver driver) {
		return waitAlertPresece(driver).getText();
	}

	public void acceptAlert(WebDriver driver, String value) {
		waitAlertPresece(driver).sendKeys(value);
	}

	public String getWindowHandle(WebDriver driver) {
		return driver.getWindowHandle();
	}

	public void switchWindowByID(WebDriver driver, String parentID) {
		Set<String> allIdWindows = driver.getWindowHandles();
		for (String runWindow : allIdWindows) {
			if (!parentID.equals(runWindow)) {
				driver.switchTo().window(runWindow);
				sleep(1);
				break;
			}
		}
	}
	
	public void switchWindowByTitle(WebDriver driver, String titlePage) {
		Set<String> allIdWindows = driver.getWindowHandles();
			for (String runWindow : allIdWindows) {
				driver.switchTo().window(runWindow);
				String titlePageCurrent  = driver.getTitle();	
				if (!titlePageCurrent.equals(titlePage)) {
					sleep(1);
					break;
				}
			}
	}
	
	public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}
	
	public Set<Cookie> getCookie(WebDriver driver) {
		return driver.manage().getCookies();
	}
	
	public void setCookie(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie setCookie : cookies) {
			driver.manage().addCookie(setCookie);
		}
		sleep(1);
	}
	
	public void deleteAllCookies(WebDriver driver) {
		driver.manage().deleteAllCookies();
	}
	// Web Element commons
	
	public By getByLocator(String locator) {
		return By.xpath(locator);
	}
	
	public WebElement getWebElement(WebDriver driver, String locator) {
		return driver.findElement(getByLocator(locator));
	}
	
	public List<WebElement> getListWebElements(WebDriver driver, String locator) {
		return driver.findElements(getByLocator(locator));
	}
	
	public void clickToElement(WebDriver driver, String locator) {	
		getWebElement(driver, locator).click();
	}
	public void sendKeysToElement(WebDriver driver, String locator, String value) {	
		getWebElement(driver, locator).clear();
		getWebElement(driver, locator).sendKeys(value);
	}
	public String getTextToElement(WebDriver driver, String locator) {	
		return getWebElement(driver, locator).getText();
	}
	
	public void selectItemDefaultDropdown_Text(WebDriver driver, String locator, String value) {	
		new Select(getWebElement(driver, locator)).selectByVisibleText(value);
	}
	
	public void selectItemDefaultDropdown_Value(WebDriver driver, String locator, String value) {	
		new Select(getWebElement(driver, locator)).selectByValue(value);
	}
	
	public void selectItemDefaultDropdown_Index(WebDriver driver, String locator, int index) {	
		new Select(getWebElement(driver, locator)).selectByIndex(index);
	}
	
	public String getFirstSelectTextInDefaultDropdown(WebDriver driver, String locator) {	
		return new Select(getWebElement(driver, locator)).getFirstSelectedOption().getText();
	}
	
	public boolean isDefaultDropdownMultiple(WebDriver driver, String locator) {	
		return new Select(getWebElement(driver, locator)).isMultiple();
	}
	
	public void selectItemDropdown(WebDriver driver,String loacator, String childLocator, String value) {
		clickToElement(driver, loacator);
		List<WebElement> listValuedropDown = new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));
		for (WebElement webElement : listValuedropDown) {
			if (webElement.getText().trim().equals(value)) {
				sleep(1);
				webElement.click();
				break;
			}
		}
	}
	
	public String getAttributeValue(WebDriver driver, String locator, String attributeValue) {
		return getWebElement(driver, locator).getAttribute(attributeValue);
	}

	public String getCssValue(WebDriver driver, String locator, String cssValue) {
		return getWebElement(driver, locator).getCssValue(cssValue);
	}
	
	public String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex().toLowerCase();
	}
	
	public int getElementsSiza(WebDriver driver, String locator) {
		return getListWebElements(driver, locator).size();
	}
	
	public void checkTheCheckboxOrRadio(WebDriver driver, String locator) {
		if(!getWebElement(driver, locator).isSelected()) {
			getWebElement(driver, locator).click();
		}
	}

	public void unCheckTheCheckboxOrRadio(WebDriver driver, String locator) {
		if(getWebElement(driver, locator).isSelected()) {
			getWebElement(driver, locator).click();
		}
	}

	public boolean isControlDisplaed(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isDisplayed();
	}
	
	public boolean isControlSelected(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isSelected();
	}
	
	public boolean isControlEnabled(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isEnabled();
	}

	public void switchToIframe(WebDriver driver, String locator) {
		new WebDriverWait(driver, 30).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getWebElement(driver, locator)));
	}
	
	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	public void doubleClickToElement(WebDriver driver, String locator) {
		new Actions(driver).doubleClick(getWebElement(driver, locator)).perform();
	}
	
	public void moveToToElement(WebDriver driver, String locator) {
		new Actions(driver).moveToElement(getWebElement(driver, locator)).perform();
	}
	
	public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator) {
		new Actions(driver).dragAndDrop(getWebElement(driver, sourceLocator), getWebElement(driver, targetLocator)).perform();
	}
	
	public void rightClick(WebDriver driver, String locator) {
		new Actions(driver).contextClick(getWebElement(driver, locator)).perform();
	}
	
	public void sendKeyboardToElement(WebDriver driver, String locator, Keys key) {
		new Actions(driver).sendKeys(getWebElement(driver, locator),key).perform();
	}
	
	public void hightlightElement(WebDriver driver, String locator) {
        WebElement element = getWebElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleep(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
        sleep(3);
    }

    public void scrollToElementOnTopByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
    }

    public void scrollToElementOnDownByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locator));
    }

    public void scrollToBottomPageByJS(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void setAttributeInDOM(WebDriver driver, String locator, String attributeName, String attributeValue) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');", getWebElement(driver, locator));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locator));
    }

    public String getAttributeInDOMByJS(WebDriver driver, String locator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getWebElement(driver, locator));
    }

    public String getElementValidationMessage(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete " +
                        "&& typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
                getWebElement(driver, locator));
    }
	
    public void waitForElementVisible(WebDriver driver, String locator) {
    	new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }
    
    public void waitListForElementVisible(WebDriver driver, String locator) {
    	new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElements(getWebElement(driver,locator)));
    }
    
    public void waitForElementInvisible(WebDriver driver, String locator) {
    	new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }
    
    public void waitListForElementInvisible(WebDriver driver, String locator) {
    	new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfAllElements(getWebElement(driver,locator)));
    }
    
    public void waitForElementClickable(WebDriver driver, String locator) {
    	new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }
    
    
    
    
	public void sleep(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
