package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;

public class NewProject extends BasePage{
    public SelenideElement KBButton=$("a[title='Dashboard']");
    public SelenideElement newProject=$("div.page-header li:nth-child(1)");
    public SelenideElement newProjectHeader= $("div#modal-content div.page-header>h2");

    public SelenideElement name=$("input#form-name");
    public SelenideElement nameLabel=$("label[for='form-name']");
    public SelenideElement warningMessage =$("ul.form-errors");
    public SelenideElement identifier=$("input#form-identifier");
    public SelenideElement identifierLabel=$("label[for='form-identifier']");
    public SelenideElement identifierComment=$("p.form-help");
    public SelenideElement checkbox=$("input[name='per_swimlane_task_limits']");
    public SelenideElement checkBoxText=$(By.xpath("//*[contains(text(), 'Column task limits')]"));
    public SelenideElement taskLimit=$("input#form-task_limit");
    public SelenideElement taskLimitLabel=$("label[for='form-task_limit']");
    public SelenideElement createFromAnotherProject=$("select#form-src_project_id");
    public SelenideElement createFromAnotherProjectLabel=$("label[for='form-src_project_id']");
    public SelenideElement createFromSecondProject =$("option[value='14']");
    public SelenideElement saveButton=$("button[class='btn btn-blue']");
    public SelenideElement cancelButton=$("div[class='form-actions'] a[href='#']");
    public SelenideElement closeButton=$("a#modal-close-button");
    public SelenideElement remove=$("li:last-child>[class='js-modal-confirm']");
    public SelenideElement removeYes=$("button#modal-confirm-button");
    public SelenideElement lastProjectDropdown=$("div[class='table-list-row table-border-left']:last-child div.dropdown");
    public SelenideElement configureThisProjectBtn=$("div#dropdown li:last-child");

    public NewProject(String pageUrl) {
        super(pageUrl);
    }

    public NewProject openNewProject() {
        clickWebElement(newProject);
        return this;
    }

    public NewProject withName(String text ) {
        sendText(name, text);
        return this;
    }

    public NewProject withIdentifier(String identifierField) {
        identifier.setValue(identifierField);
        return this;
    }

    public NewProject withTaskLimit(String taskLimitField) {
        taskLimit.setValue(taskLimitField);
        return this;
    }

    public NewProject enableTaskLimitCheckbox() {
        clickWebElement(checkbox);
        return this;
    }

    public NewProject submit() {
        clickWebElement(saveButton);
        return this;
    }

    public NewProject duplicateAnotherProject(){
        clickWebElement(createFromAnotherProject);
        clickWebElement(createFromSecondProject);
        return this;
    }

    public NewProject cancel(){
        clickWebElement(cancelButton);
        return this;
    }

    public NewProject close(){
        clickWebElement(closeButton);
        return this;
    }

    public NewProject goToKanboardPage(){
        clickWebElement(KBButton);
        return this;
    }

    public void removeProject() {
        clickWebElement(remove);
        clickWebElement(removeYes);
    }

    public void openConfigureThisProject(){
        clickWebElement(lastProjectDropdown);
        clickWebElement(configureThisProjectBtn);
    }

    public void assertWarningDisplayed( String warningText){
        warningMessage.shouldBe(Condition.visible).shouldHave(Condition.text(warningText));
    }
}
