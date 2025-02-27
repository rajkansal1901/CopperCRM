package com.qa.copperCrm.pages;

import com.qa.copperCrm.constants.AppConstants;
import com.qa.copperCrm.utils.ElementUtil;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PeoplePage {

    protected WebDriver driver;
    protected ElementUtil elementUtil;

    @FindBy(css = "[data-test='globalAddNew']")
    private WebElement addBtn;

    @FindBy(css = "[data-test='btnBrowseAddEntity']")
    private WebElement toolbarAddPersonBtn;

    @FindBy(css = "[data-test='savedFilterPanelToggle']")
    private WebElement collapseButton;

    @FindBy(css = ".savedSearchListContainer")
    private WebElement searchListContainer;

    @FindBy(css = "[data-test='contactOptionListItem']")
    private WebElement addNewPerson;

    @FindBy(css = "[data-test='emptyStateTitle']")
    private WebElement noResultFoundTitle;

    @FindBy(css = "[data-test='bulkSelectAll']")
    private WebElement bulkSelectAllCheckbox;

    @FindBy(css = "[data-test='bulkActionsMenu']")
    private WebElement bulkMenuOption;

    @FindBy(css = ".BrowseBulkActions_selectedStatus")
    private WebElement selectStatus;

    @FindBy(css = "[data-test='bulkActionsDelete']")
    private WebElement deleteBtn;

    @FindBy(css = "[data-test='modalCtaButton']")
    private WebElement confirmDeleteBtn;

    @FindBy(className = "Toast_icon-message-wrapper")
    private WebElement confirmationMessage;

    @FindBy(css = "[data-test='btnEntityActionsClosePreview']")
    private WebElement personPreviewCloseBtn;

    @FindBy(css = "[data-test='Toast_close']")
    private WebElement closeBtn;


    public PeoplePage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * this method is used to get People page title
     *
     * @return String (People Page title)
     */
    public String getPeoplePageTitle() {
        String title = elementUtil.waitForTitleContainsAndReturn(AppConstants.PEOPLE_PAGE_TITLE, AppConstants.DEFAULT_SHORT_TIMEOUT);
        System.out.println("People Page Title : " + title);
        return title;
    }

    /**
     * this method is used to get People Page Url
     *
     * @return String (People Page Url)
     */
    public String getUrl() {
        String url = elementUtil.waitForURLContainsAndReturn(AppConstants.PEOPLE_PAGE_FRACTION_URL, AppConstants.DEFAULT_SHORT_TIMEOUT);
        System.out.println("People Page url : " + url);
        return url;
    }

    /**
     * this method click on add button in top action bar
     *
     * @return People Page
     */
    public PeoplePage clickAddPersonBtn() {
        elementUtil.waitAndClick(addBtn);
        return this;
    }

    /**
     * this method click on add new person button under add button dropdown
     *
     * @return AddPerson Page
     */
    public AddPersonPage clickNewPersonBtn() {
        elementUtil.waitAndClick(addNewPerson);
        return new AddPersonPage(driver);
    }

    /**
     * this method is to bulk select all the available people on people page
     */
    public void selectAllPeople() {
        elementUtil.waitUntilElementVisibility(bulkSelectAllCheckbox, AppConstants.DEFAULT_SHORT_TIMEOUT);
        if (bulkSelectAllCheckbox.isSelected()) {
            elementUtil.click(bulkSelectAllCheckbox);
            elementUtil.click(bulkSelectAllCheckbox);
        } else {
            elementUtil.click(bulkSelectAllCheckbox);
        }
    }

    /**
     * this method deletes the selected items on People Page
     */
    public void deleteAllItem() {
        selectAllPeople();
        elementUtil.waitAndClick(bulkMenuOption);
        elementUtil.waitAndClick(deleteBtn);
        elementUtil.waitAndClick(confirmDeleteBtn);
    }

    /**
     * this method validates if page is empty
     *
     * @return boolean
     */
    public boolean isPageEmpty() {
        try {
            elementUtil.waitUntilElementVisibility(noResultFoundTitle, AppConstants.DEFAULT_SHORT_TIMEOUT);
        } catch (TimeoutException e) {
            deleteAllItem();
            elementUtil.waitUntilElementVisibility(noResultFoundTitle, AppConstants.DEFAULT_SHORT_TIMEOUT);

        }
        return noResultFoundTitle.isDisplayed();
    }

    /**
     * this method close the preview panel & validate whether confirmation message is displayed on screen or not
     *
     * @return boolean
     */
    public boolean isConfirmationMessageDisplayed() {
        elementUtil.waitAndClick(personPreviewCloseBtn);
        elementUtil.waitUntilElementVisibility(confirmationMessage, AppConstants.DEFAULT_SHORT_TIMEOUT);
        if (confirmationMessage.isDisplayed()) {
            elementUtil.click(closeBtn);
            return true;
        } else {
            return false;
        }
    }

    /**
     * this method validates whether item selected title is visible or not
     * @return boolean
     */
    public boolean isSelectStatusVisible() {
        elementUtil.waitUntilElementVisibility(selectStatus, AppConstants.DEFAULT_MEDIUM_TIMEOUT);
        return selectStatus.isDisplayed();
    }


}
