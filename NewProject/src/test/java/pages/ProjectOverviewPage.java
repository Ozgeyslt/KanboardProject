package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.function.IntPredicate;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProjectOverviewPage extends BasePage {


    public SelenideElement firstTest =$("span>a[href='/board/13']");
    public SelenideElement KBButton=$("a[title='Dashboard']");
    public SelenideElement pageTitle=$("span.title");
    public SelenideElement backlogTaskCount=$("div.project-overview-columns>div:nth-child(1) strong");
    public SelenideElement readyTaskCount=$("div.project-overview-columns>div:nth-child(2) strong");
    public SelenideElement workInProgressTaskCount=$("div.project-overview-columns>div:nth-child(3) strong");
    public SelenideElement doneTaskCount=$("div.project-overview-columns>div:nth-child(4) strong");
    public SelenideElement boardButton=$("ul.views>li:nth-child(2)");
    public SelenideElement descriptionBtn =$("section#main>details:nth-child(3) summary.accordion-title");
    public SelenideElement attachments =$("section#main>details:nth-child(4) summary.accordion-title");
    public SelenideElement information =$("section#main>details:nth-child(5) summary.accordion-title");
    public SelenideElement lastActivity =$("section#main>details:nth-child(6) summary.accordion-title");
    public SelenideElement backlogTestCountTitle=$("div.project-overview-columns>div:nth-child(1) small");
    public SelenideElement readyTaskCountTitle=$("div.project-overview-columns>div:nth-child(2) small");
    public SelenideElement workInProgressTaskCountTitle=$("div.project-overview-columns>div:nth-child(3) small");
    public SelenideElement doneTaskCountTitle=$("div.project-overview-columns>div:nth-child(4) small");
    public SelenideElement editDescriptionButton=$("section#main>details:nth-child(3) a[class='js-modal-medium btn']");
    public SelenideElement projectOwner=$("section#main>details:nth-child(5) li:nth-child(1) strong");
    public SelenideElement projectName=$("input[type='text'][id='form-name']");
    public SelenideElement projectEmail=$("input#form-email");
    public SelenideElement projectTextarea=$("div.text-editor-write-mode textarea[name='description']");
    public SelenideElement formTaskLimit =$("input#form-task_limit");
    public SelenideElement personalProjectCheckbox=$("input[type='checkbox'][name='is_private']");
    public SelenideElement projectOwnerDropdown=$("select#form-owner_id");
    public SelenideElement projectOwnerUnassigned=$("select#form-owner_id>option:first-child");
    public SelenideElement projectOwnerAdmin=$("select#form-owner_id>option:last-child");
    public SelenideElement projectStartDate=$("input#form-start_date");
    public SelenideElement projectEndDate=$("input#form-end_date");
    public SelenideElement informationStartDate=$("div.panel li:nth-child(3)");
    public SelenideElement informationEndDate=$("div.panel li:nth-child(4)");
    public SelenideElement projectSaveBtn=$("button[class='btn btn-blue']");
    public SelenideElement projectAlertSuccess =$("div[class='alert alert-success alert-fade-out']");
    public SelenideElement warning=$("ul.form-errors");
    public SelenideElement overviewBtn =$("ul.views>li:nth-child(1)");
    public SelenideElement description=$("section#main>details:nth-child(3)>summary");
    public SelenideElement dropdownComponent=$("div.dropdown-component>div.dropdown");
    public SelenideElement configureThisProjectBtn=$("ul.dropdown-submenu-open>li:nth-child(7)");
    public SelenideElement descriptionText=$("section#main>details:nth-child(3) article.markdown");
    public SelenideElement closeBtn=$("a#modal-close-button");
    public SelenideElement cancelBtn=$("div.form-actions>a");
    public SelenideElement lastTaskNameInLastActivity=$("div.activity-event:first-child p.activity-task-title");
    public SelenideElement projectTooltip=$("div.title-container span.tooltip");
    public SelenideElement projectTooltipInformation=$("div#tooltip-container");
    public SelenideElement backlogLastTaskDropdown=$("td.board-column-49 div[data-position='4'] div.dropdown");
    public SelenideElement taskDropdownRemoveElement=$("div#dropdown li:nth-child(14)");
    public SelenideElement removeTaskYesButton=$("div#modal-box button#modal-confirm-button");
    public SelenideElement boardTaskLimitWarning=$("div[class='board-task-list-limit board-container-compact']");
    public ElementsCollection informationPanel=$$("div.panel li");


    public ProjectOverviewPage(String pageUrl) {
        super(pageUrl);
    }

    public ProjectOverviewPage clickDescriptionBtn() {
        if (editDescriptionButton.isDisplayed()) {
            editDescriptionButton.click();
        } else {
            description.click();
            editDescriptionButton.shouldBe(Condition.visible).click();
        }
        return this;
    }

    public ProjectOverviewPage setDate(SelenideElement element, String newDate) {
        String currentValue = element.getValue();
        if (currentValue == null || currentValue.trim().isEmpty()) {
            element.setValue(newDate);
        } else {
            element.clear();
            element.setValue(newDate);
        }
        return this;
    }

    public IntPredicate findElementFromInformation(ElementsCollection collection, String textToFind) {
        for (int i = 0; i < collection.size(); i++) {
            SelenideElement element = collection.get(i);
            if (element.getText().contains(textToFind)) {
                System.out.println("Matched text: " + element.getText());
                return null;
            }
        }
        System.out.println("No element found with text: " + textToFind);
        return null;
    }
    public ProjectOverviewPage setName(String text){
        clearText(projectName);
        sendText(projectName, text);
        return this;
    }

    public ProjectOverviewPage setDate(String text1, String text2){
        scrollToWebElement(projectOwner);
        setDate(projectStartDate, text1);
        setDate(projectEndDate, text2);
        return this;
    }

    public ProjectOverviewPage save(){
        clickWebElement(projectSaveBtn);
        return this;
    }

    public ProjectOverviewPage editDescription(String text){
        clearText(projectTextarea);
        sendText(projectTextarea, text);
        return this;
    }

    public ProjectOverviewPage editEmail(String text){
        clearText(projectEmail);
        sendText(projectEmail, text);
        return this;
    }

    public ProjectOverviewPage editNumberOfTask(int text){
        clearText(formTaskLimit);
        sendText(formTaskLimit, text);
        return this;
    }

    public ProjectOverviewPage decreaseTaskNumber(int number){
        decreaseValueWithArrowKey(formTaskLimit, number);
        return this;
    }

    public ProjectOverviewPage increaseTaskNumber(int number){
        increaseValueWithArrowKey(formTaskLimit, number);
        return this;
    }

    public ProjectOverviewPage goToBoardPage(){
        clickWebElement(boardButton);
        return this;
    }

    public ProjectOverviewPage goToOverviewPage(){
        clickWebElement(firstTest);
        clickWebElement(overviewBtn);
        return this;
    }

    public ProjectOverviewPage clickCheckBox() {
        personalProjectCheckbox.shouldBe(Condition.visible);
        clickWebElement(personalProjectCheckbox);
        return this;
    }

    public ProjectOverviewPage editProjectOwner(String text){
        scrollToWebElement(projectOwnerDropdown);
        clickWebElement(projectOwnerDropdown);
        if(text.equals("admin")){
            clickWebElement(projectOwnerAdmin);
        }
        else if (text.equals("Unassigned")){
            clickWebElement(projectOwnerUnassigned);
        }
        else {
            throw new IllegalArgumentException("Invalid value: " + text );
        }
        return this;
    }
    public ProjectOverviewPage goToKBPage(){
        clickWebElement(KBButton);
        return this;
    }

    public ProjectOverviewPage goToConfigureThisProject(){
        clickWebElement(dropdownComponent);
        clickWebElement(configureThisProjectBtn);
        return this;
    }

    public ProjectOverviewPage deleteTask(){
        clickWebElement(backlogLastTaskDropdown);
        clickWebElement(taskDropdownRemoveElement);
        clickWebElement(removeTaskYesButton);
        return this;
    }
    public ProjectOverviewPage goToProjectPage(){
        clickWebElement(firstTest);
        return this;
    }

    public ProjectOverviewPage clickOverviewButton(){
        clickWebElement(overviewBtn);
        return this;
    }
    public ProjectOverviewPage close(){
        clickWebElement(closeBtn);
        return this;
    }

    public ProjectOverviewPage cancel(){
        clickWebElement(cancelBtn);
        return this;
    }

}
