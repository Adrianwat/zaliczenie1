import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.Assert;
import java.util.List;

public class MyStepdefs {

    WebDriver webDriver;
    WebDriverWait wait;


    @Given("I open the browser")
    public void iOpenTheBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Webdriver\\chromedriver.exe");
        webDriver = new ChromeDriver();
        wait = new WebDriverWait(webDriver, 3);
    }

    @And("I go to the sign in page")
    public void iGoToTheSignInPage() {
        webDriver.get("https://mystore-testlab.coderslab.pl/index.php?controller=authentication&back=my-account");
    }

    @And("I type {string} as email")
    public void iTypeAsEmail(String email) {
        WebElement element = webDriver.findElement(By.cssSelector("input[id=field-email]"));
        element.sendKeys(email);
    }

    @And("I type {string} as password")
    public void iTypeAsPassword(String password) {
        WebElement element = webDriver.findElement(By.cssSelector("input[id=field-password]"));
        element.sendKeys(password);
    }

    @And("I click on Sign In button")
    public void iClickOnSignInButton() {
        WebElement element = webDriver.findElement(By.cssSelector("button[id=submit-login]"));
        element.click();

        wait.until(ExpectedConditions.urlContains("controller=my-account"));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.logout")));
    }

    @When("I click on Addresses")
    public void iClickOnAddresses() {
        WebElement element = webDriver.findElement(By.cssSelector("a[title=Addresses]"));
        element.click();
        wait.until(ExpectedConditions.urlContains("controller=addresses"));
    }

    @And("I click on Create new address")
    public void iClickOnCreateNewAddress() {
        WebElement element2 = webDriver.findElement(By.cssSelector("[data-link-action=\"add-address\"]"));
        element2.click();

    }

    @And("I enter {string}, {string}, {string}, {string},{string}, {string} in the form")
    public void iEnterInTheForm(String alias, String address, String city, String zip, String country, String phone) {
        WebElement element1 = webDriver.findElement(By.cssSelector("input[id=field-alias]"));
        element1.sendKeys(alias);

        WebElement element2 = webDriver.findElement(By.cssSelector("input[id=field-address1"));
        element2.sendKeys(address);

        WebElement element3 = webDriver.findElement(By.cssSelector("input[id=field-city"));
        element3.sendKeys(city);

        WebElement element4 = webDriver.findElement(By.cssSelector("input[id=field-postcode"));
        element4.sendKeys(zip);

        //WebElement element5 = webDriver.findElement(By.cssSelector("select[id=fields-id_country"));
        //element5.sendKeys(country);

        WebElement element6 = webDriver.findElement(By.cssSelector("input[id=field-phone]"));
        element6.sendKeys(phone);

    }

    @And("I click on Save")
    public void iClickOnSave() {
        WebElement element = webDriver.findElement(By.cssSelector("button[type=submit]"));
        element.click();
    }

    @Then("new address is added successfully")
    public void newAddressIsAddedSuccessfully() {
        wait.until(ExpectedConditions.urlContains("controller=addresses"));
        WebElement elementY = webDriver.findElement(By.xpath("//*[contains(text(), \"Address successfully added!\")]"));


    }

   @And("I check if {string}, {string}, {string},{string},{string}, {string} are added correctly")
    public void iCheckIfAreAddedCorrectly(String alias, String address, String city, String zip, String country, String phone) {
       List<WebElement> listOfAdresses = webDriver.findElements(By.cssSelector("article[class=address]"));
       int len = listOfAdresses.size();
       int topAddress = 0;
       for (int i = 0; i < len; i++) {
           String text = listOfAdresses.get(i).getAttribute("data-id-address");
           int id = Integer.parseInt(text);
           if (id > topAddress) {
               topAddress = id;
           }

       }
       String myCssSelection = "article[id=address-" + topAddress + "]";
       WebElement element13 = webDriver.findElement(By.cssSelector(myCssSelection)).findElement(By.xpath("div/h4"));
       String addedAlias = element13.getText();
       Assert.assertEquals(addedAlias, alias);
       WebElement elementAddresses = webDriver.findElement(By.cssSelector(myCssSelection)).findElement(By.xpath("div/address"));

       Assert.assertTrue(elementAddresses.getText().contains(address));
       Assert.assertTrue(elementAddresses.getText().contains(city));
       Assert.assertTrue(elementAddresses.getText().contains(zip));
       Assert.assertTrue(elementAddresses.getText().contains(country));
       Assert.assertTrue(elementAddresses.getText().contains(phone));
   }

    @And("I close the browser")
    public void iCloseTheBrowser() {
        webDriver.quit();
    }
}
