package com.qa.copperCrm.pages;

import com.qa.copperCrm.constants.AppConstants;
import com.qa.copperCrm.utils.ElementUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddPersonPage {
    private WebDriver driver;
    private ElementUtil elementUtil;

    @FindBy(css = "[data-test='createEntityModalTitle']")
    private WebElement headerMessage;

    @FindBy(css = "[data-test='btnCancel']")
    private WebElement cancelBtn;

    @FindBy(css = "input[data-test='firstNameInputField']")
    private WebElement firstName;

    @FindBy(css = "input[data-test='middleNameInputField']")
    private WebElement middleName;

    @FindBy(css = "input[data-test='lastNameInputField']")
    private WebElement lastName;

    @FindBy(css = "input[data-test='prefixInputField']")
    private WebElement prefix;

    @FindBy(css = "input[data-test='organizationInputField']")
    private WebElement companyName;

    @FindBy(css = "input[data-test='titleInputField']")
    private WebElement title;

    @FindBy(css = "input[data-test='emailInputField']")
    private WebElement workEmail;

    @FindBy(css = "input[data-test='phoneInputField']")
    private WebElement workPhone;

    @FindBy(css = "input[data-test='addressStreetInputField']")
    private WebElement streetName;

    @FindBy(css = "input[data-test='addressCityInputField']")
    private WebElement city;

    @FindBy(css = "input[data-test='addressStateInputField']")
    private WebElement state;

    @FindBy(css = "input[data-test='addressPostalCodeInputField']")
    private WebElement postalCode;

    @FindBy(css = "input[data-test='addressCountryInputField']")
    private WebElement country;

    @FindBy(css = ".FormFramework_row  input[data-test='inputField']")
    private WebElement tags;

    @FindBy(css = "button[data-test='btnSave']")
    private WebElement saveBtn;

    @FindBy(className = "FormFramework_entityIconBox")
    private WebElement profileIcon;

    public AddPersonPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * this method is used to get Person page header text
     *
     * @return String (Header text)
     */
    public String getAddPersonPageHeader() {
        elementUtil.waitUntilElementVisibility(headerMessage, AppConstants.DEFAULT_SHORT_TIMEOUT);
        String headerText = headerMessage.getText();
        return headerText;
    }

    /**
     * this method validates whether is profile icon is displayed on Add Person Page on not
     * @return boolean
     */
    public boolean isProfileIconExists() {
        elementUtil.waitUntilElementVisibility(profileIcon, AppConstants.DEFAULT_SHORT_TIMEOUT);
        return profileIcon.isDisplayed();
    }

    public void navigateToPeoplePage() {
        elementUtil.waitAndClick(cancelBtn);
    }


    /**
     * this method is used to create a new people record
     *
     * @param firstname
     * @param lastname
     * @param prefix
     * @param companyName
     * @param title
     * @param workEmail
     * @param workPhone
     * @param street
     * @param city
     * @param state
     * @param zip
     * @param country
     * @param tags
     * @return People Page
     */
    public PeoplePage addNewPerson(String firstname, String lastname, String prefix, String companyName, String title,
                                   String workEmail, String workPhone, String street, String city, String state, String zip,
                                   String country, String tags) {
        elementUtil.waitAndClickElementAndSendKeys(firstName, firstname);
        elementUtil.doSendKeys(lastName, lastname);
        elementUtil.doSendKeys(this.prefix, prefix);
        elementUtil.doSendKeys(this.companyName, companyName);
        elementUtil.doSendKeys(this.title, title);
        elementUtil.doSendKeys(this.workEmail, workEmail);
        elementUtil.doSendKeys(this.workPhone, workPhone);
        elementUtil.doSendKeys(streetName, street);
        elementUtil.doSendKeys(this.city, city);
        elementUtil.doSendKeys(this.state, state);
        elementUtil.doSendKeys(postalCode, zip);
        elementUtil.doSendKeys(this.country, country);
        elementUtil.doSendKeys(this.tags, tags);

        elementUtil.click(saveBtn);
        return new PeoplePage(driver);
    }
}
