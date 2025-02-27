package com.qa.copperCrm.pages;

import com.qa.copperCrm.constants.AppConstants;
import com.qa.copperCrm.utils.ElementUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.NoSuchElementException;

public class TasksPage {
    private WebDriver driver;
    private ElementUtil elementUtil;

    @FindBy(className = "Toast_message")
    private WebElement taskUpdatedMessage;

    @FindBy(css = "[data-test='btnBrowseAddEntity']")
    private WebElement addTaskBtn;

    @FindBy(css = "[aria-label=\"Add a Task\"]")
    private WebElement addTaskOption;

    @FindBy(css = "[data-test='nameInputField']")
    private WebElement inputName;

    @FindBy(css = "[data-test='btnSave']")
    private WebElement saveBtn;

    @FindBy(css = "[data-test='dueDateInputField']")
    private WebElement dateInput;

    @FindBy(css = ".ember-power-calendar-day-grid  button")
    private List<WebElement> allDateBtn;

    @FindBy(css = ".ember-power-calendar-nav > button:last-child")
    private WebElement nextMonthBtn;

    @FindBy(css = "[data-test=\"bulkSelectAll\"]")
    private WebElement bulkSelectCheckBox;

    @FindBy(css = "div[class=\"BrowseBulkActions_toolbar\"]:first-child > button:nth-child(2) ")
    private WebElement markCompleteButton;

    @FindBy(css = ".Modal_footer  > button:nth-child(2)")
    private WebElement completeTaskButton;

    @FindBy(className = "ember-power-calendar-nav-title")
    private WebElement monthYear;

    @FindBy(className = "Toaster_toastWrapper")
    private WebElement confirmationMessage;

    @FindBy(css = "[data-test=\"btnEntityActionsClosePreview\"]")
    private WebElement closeButton;


    public TasksPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * this method is used to get the Task Page url
     * @return String (Task Page Url)
     */
    public String getTaskPageUrl() {
        String url = elementUtil.waitForURLContainsAndReturn(AppConstants.TASKS_PAGE_FRACTION_URL, AppConstants.DEFAULT_SHORT_TIMEOUT);
        System.out.println("Task Page Url : " + url);
        return url;
    }

    public String getTaskPageTitle() {
        String title = elementUtil.waitForTitleContainsAndReturn(AppConstants.TASKS_PAGE_TITLE, AppConstants.DEFAULT_SHORT_TIMEOUT);
        System.out.println("Task Page Title : " + title);
        return title;
    }

    public void addNewTask(String name, String monthYear, String date) {
        try {
            elementUtil.waitAndClick(addTaskBtn);
        } catch (NoSuchElementException e) {
           elementUtil.waitAndClick(addTaskOption);
        }
        elementUtil.waitAndClickElementAndSendKeys(inputName, name);
        elementUtil.waitAndClick(dateInput);
        elementUtil.selectDate(this.monthYear, nextMonthBtn, allDateBtn, monthYear, date);
        elementUtil.click(saveBtn);
        elementUtil.waitAndClick(closeButton);
    }

    public void bulkSelectTask() {
       if (bulkSelectCheckBox.isSelected()) {
           elementUtil.waitAndClick(bulkSelectCheckBox);
           bulkSelectCheckBox.click();
       } else {
           elementUtil.waitAndClick(bulkSelectCheckBox);
       }
    }

    public void markTaskComplete() {
        elementUtil.waitAndClick(markCompleteButton);
        elementUtil.waitAndClick(completeTaskButton);
    }

    public boolean isTaskUpdated() {
        elementUtil.waitUntilElementVisibility(confirmationMessage);
        return confirmationMessage.isDisplayed();
    }

    public void markTaskAsComplete() {
        bulkSelectTask();
        markTaskComplete();
    }
}
