package stepdefinitions;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.After;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobjects.Homepage;
import pageobjects.Searchresults;
import pageobjects.AutomationStore;
import utils.FileUtils;
import utils.Validations;
import utils.Waits;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class StepDefinitions {

    private WebDriver driver;

    public void startDriver(String url){

        System.setProperty("webdriver.chrome.driver", new File("chromedriver.exe").getPath());
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
//        options.addArguments("--headless");
//        options.addArguments("start-maximized"); // https://stackoverflow.com/a/26283818/1689770
        options.addArguments("enable-automation"); // https://stackoverflow.com/a/43840128/1689770
        options.addArguments("--disable-browser-side-navigation"); //https://stackoverflow.com/a/49123152/1689770
        options.addArguments("--disable-gpu");
        driver = new ChromeDriver(options);
        driver.get(url);

    }

    @After
    public void tearDown(Scenario scenario) throws IOException {

        if(scenario.isFailed()){
            FileUtils fileUtils = new FileUtils();
            fileUtils.addScreenshot(scenario, driver);
        }

        driver.quit();

    }

    @Given("the site {string} is open")
    public void theSiteDuckDuckGoIsOpen(String site) throws InterruptedException {
        String url = "";

        switch (site.toLowerCase()){
            case "duckduckgo":
                url = "https://duckduckgo.com/";
                break;
            case "advantageonlineshopping":
                url = "https://advantageonlineshopping.com/";
                break;
            default:
                Assert.fail("Something is wrong. The website '" + site + "' you are trying to open in not recognised. ");
        }

        startDriver(url);
        driver.manage().window().fullscreen();
        Waits waits = new Waits(driver);
        Thread.sleep(10000);
        switch (site.toLowerCase()){
            case "duckduckgo":
                Homepage homepage = new Homepage(driver);
                waits.waitForElement(homepage.logoHomepage);
                break;
            case "advantageonlineshopping":
                AutomationStore automationStore = new AutomationStore(driver);
                waits.waitForElement(automationStore.logoStore);
                break;
            default:
                System.out.println("Something is wrong. The website '" + site + "' you are trying to open in not recognised. ");
        }
    }

    @Then("the searchbar is enabled")
    public void theSearchbarIsEnabled() {

        Waits waits = new Waits(driver);
        Homepage homepage = new Homepage(driver);

        waits.waitForElement(homepage.searchBar);
        Assert.assertTrue(homepage.searchBar.isEnabled());

    }

    @When("I search for {string}")
    public void iSearchFor(String searchObject) {

        Homepage homepage = new Homepage(driver);
        homepage.searchBar.sendKeys(searchObject);
        homepage.searchButton.click();

    }

    @Then("I can see the search results for {string}")
    public void iCanSeeTheSearchResultsFor(String searchObject) {

        Waits waits = new Waits(driver);
        Searchresults searchresults = new Searchresults(driver);

        waits.waitForElement(searchresults.sidebarTitle);
        Assert.assertEquals(searchObject, searchresults.sidebarTitle.getText().toLowerCase());

    }

    @When("I click the {string} form")
    public void iOpenTheMenu(String menu) {
        Waits waits = new Waits(driver);
        AutomationStore automationStore = new AutomationStore(driver);

        waits.waitForElement(automationStore.menuCategoryWomen);
        Actions click = new Actions(driver);
        click.click(automationStore.menuCategoryWomen).build().perform();
    }

    @Then("I can see the textboxs:")
    public void iCanSeeTheMenuItems(List<String> lijstExpected) {
        Waits waits = new Waits(driver);
        Validations validations = new Validations(driver);
        AutomationStore automationStore = new AutomationStore(driver);

        waits.waitForElements(automationStore.dropdownItemsCategoryWomen);
        validations.compareLists(automationStore.dropdownItemsCategoryWomen, lijstExpected);

    }
    @Then("I can enter username: {string} and password: {string}")
    public void iCanEnterAOSCredentials(String userName, String password) throws InterruptedException {
        Waits waits = new Waits(driver);
        Validations validations = new Validations(driver);
        AutomationStore automationStore = new AutomationStore(driver);

        Homepage homepage = new Homepage(driver);
        automationStore.userName.sendKeys(userName);
        Thread.sleep(1000);
        //driver.findElement(By.xpath("//label[contains(text(),'Password')]"));
        automationStore.passwordLabel.click();
        Thread.sleep(2000);
        automationStore.password.sendKeys(password);
         // 1 minute in milliseconds
        Thread.sleep(2000);
        automationStore.signInButton.click();
        Thread.sleep(2000);
    }

    @Then("I should be signed in as {string}")
    public void iAmSignedIn(String user){
        Waits waits = new Waits(driver);
        Validations validations = new Validations(driver);
        AutomationStore automationStore = new AutomationStore(driver);
        String menuItemText = automationStore.menuItem.getText();
        waits.waitForElement(automationStore.menuItem);
        Assert.assertTrue(automationStore.menuItem.getText().equals(user));
    }
}
