package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AutomationStore {

    public AutomationStore(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//html/body/header/nav/div/a/span")
    public WebElement logoStore;

    @FindBy(id = "menuUserSVGPath")
    public WebElement menuCategoryWomen;

    @FindBy(css = "a[title='Women'] ~ ul  a")
    public List<WebElement> dropdownItemsCategoryWomen;

    @FindBy(name = "username")
    public WebElement userName;

    @FindBy(xpath = "//label[contains(text(),'Password')]")
    public WebElement passwordLabel;

    @FindBy(name = "password")
    public WebElement password;

    @FindBy(id = "sign_in_btn")
    public WebElement signInButton;

    @FindBy(xpath = "//a[@id='menuUserLink']/span")
    public WebElement menuItem;
}
