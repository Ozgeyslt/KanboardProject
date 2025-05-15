package steps;

import com.codeborne.selenide.Condition;
import common.PageManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import pages.*;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Project Configuration and Task Management")
public class ProjectOverviewTest {

    protected PageManager pageManager=new PageManager();
    ProjectOverviewPage projectOverviewPage =pageManager.getProjectOverviewPage();
    ProjectPage projectPage=pageManager.getProjectPage();
    ProjectSummary projectSummary=pageManager.getProjectSummary();


    @Step("Navigate to the Project Overview page")
    @Description("Navigates to the Project Overview page for the current project")
    @When("the user navigates to the Project Overview page")
    public void navigateToOverviewPage() {
        projectOverviewPage.goToOverviewPage();
    }

    @Step("Verify the Project Overview page title is visible")
    @Description("Verifies that the page title of the Project Overview page is correctly displayed")
    @Then("the page title should be visible")
    public void overviewPageOpenSuccessfully(){
        projectOverviewPage.assertThatText(projectOverviewPage.pageTitle, "1.TEST (12 /\n" +
                "Task limit\n" + "20)");
    }

    @Step("Verify Project Setting titles are visible and clickable")
    @Description("Checks if all Project Setting titles are visible and clickable")
    @And("Project Setting Titles should be visible and clickable")
    public void checkProjectSettingTitlesAreDisplayed(){
        projectOverviewPage.assertElementVisibleAndClickable(projectOverviewPage.descriptionBtn);
        projectOverviewPage.assertElementVisibleAndClickable(projectOverviewPage.attachments);
        projectOverviewPage.assertElementVisibleAndClickable(projectOverviewPage.information);
        projectOverviewPage.assertElementVisibleAndClickable(projectOverviewPage.lastActivity);
    }

    @Step("Verify task section titles are displayed")
    @Description("Verifies the visibility of task count section headers: Backlog, Ready, Work in Progress, and Done")
    @And("The task counts for the Backlog, Ready, Work in Progress, and Done titles should be visible")
    public void projectTaskTitlesAreDisplayed(){
        projectOverviewPage.assertThatText(projectOverviewPage.backlogTestCountTitle, "Backlog");
        projectOverviewPage.assertThatText(projectOverviewPage.readyTaskCountTitle, "Ready");
        projectOverviewPage.assertThatText(projectOverviewPage.workInProgressTaskCountTitle, "Work in progress");
        projectOverviewPage.assertThatText(projectOverviewPage.doneTaskCountTitle, "Done");
    }

    @Step("Verify task counts match expected values")
    @Description("Verifies the displayed task counts in each section match the expected values")
    @And("the task counts should match expected values")
    public void projectTaskCountsAreDisplayed(){
        projectOverviewPage.assertThatText(projectOverviewPage.backlogTaskCount, "Task count\n" + "3");
        projectOverviewPage.assertThatText(projectOverviewPage.readyTaskCount, "Task count\n" + "3");
        projectOverviewPage.assertThatText(projectOverviewPage.workInProgressTaskCount, "Task count\n" + "2");
        projectOverviewPage.assertThatText(projectOverviewPage.doneTaskCount, "Task count\n" + "4");
    }

    @Step("Edit project name to empty and save")
    @Description("Attempts to update the project name with an empty value and saves")
    @When("the user edits the project name to empty and saves")
    public void emptyName(){
        projectOverviewPage.goToOverviewPage().clickDescriptionBtn().setName("").save();
    }

    @Step("Verify warning message for empty project name")
    @Description("Verifies that a warning message appears when attempting to save an empty project name")
    @Then("a warning This field cannot be empty should be displayed")
    public void emptyNameWarning(){
        projectOverviewPage.assertThatText(projectOverviewPage.warning, "This field cannot be empty");
    }

    @Step("Edit project name to 'gh&90' and save")
    @Description("Updates the project name to 'gh&90' including special characters and saves")
    @When("the user edits the project name with special characters and saves")
    public void projectNameWithSpecialCharacters(){
        projectOverviewPage.goToOverviewPage().clickDescriptionBtn().setName("gh&90").save();
    }

    @Step("Verify updated project name with special characters is displayed in the title")
    @Description("Verifies that the page title updates correctly when special characters are used in the project name")
    @Then("the page title should reflect the new name with special characters")
    public void verifyNewProjectNameWithSpecialCharacter(){
        projectOverviewPage.projectAlertSuccess.should(Condition.visible);
        projectOverviewPage.assertThatText(projectOverviewPage.pageTitle, "gh&90 (12 /\n" + "Task limit\n" + "20)");
    }

    @Step("Edit project name to '1.TEST' and set start date to 11/12/2024 and end date to 12/12/2024")
    @Description("Updates the project name and date fields, then saves the changes")
    @When("the user edits the name and dates")
    public void editNameAndDate() {
        projectOverviewPage.goToOverviewPage().clickDescriptionBtn().setName("1.TEST")
                .setDate("11/12/2024", "12/12/2024").save();
    }

    @Step("Verify the project title and date fields display the updated values")
    @Description("Checks that the updated project name and date fields are correctly on the page")
    @Then("the page title and information dates should be updated accordingly")
    public void verifyNewNameAndDate(){
        projectOverviewPage.projectAlertSuccess.should(Condition.visible);
        projectOverviewPage.assertThatText(projectOverviewPage.pageTitle, "1.TEST (12 /\n" + "Task limit\n" + "20)");
        projectOverviewPage.assertThatText(projectOverviewPage.informationStartDate, "Start date: 11/12/2024");
        projectOverviewPage.assertThatText(projectOverviewPage.informationEndDate, "End date: 12/12/2024");
    }

    @Step("Edit project description to 'Writing..' and email to 'gmail@co'")
    @Description("Updates the project description and email fields, then saves the changes")
    @When("the user edits the description and email")
    public void editDescriptionAndEmail() {
        projectOverviewPage
                .goToOverviewPage().clickDescriptionBtn().editDescription("Writing..")
                .editEmail("gmail@co").save();
    }

    @Step("Verify updated project description and email are displayed correctly")
    @Description("Checks that the updated description and email are visible and correctly saved")
    @Then("the description and email should be updated")
    public void verifyNewDescriptionAndEmail() throws InterruptedException {
        projectOverviewPage.projectAlertSuccess.shouldBe(Condition.visible);
        projectOverviewPage.assertThatText(projectOverviewPage.descriptionText, "Writing..");
    }

    @Step("Change project email to 'gmail@mail.coo'")
    @Description("Change project email to 'gmail@mail.coo'")
    @And("The first e-mail address is entered")
    public void changeemail(){
        projectOverviewPage.clickDescriptionBtn().editEmail("gmail@mail.coo").save();
        projectOverviewPage.projectAlertSuccess.shouldBe(Condition.visible);
    }

    @Step("Enter invalid email format 'hotmail' and save")
    @Description("Attempts to update the email with an invalid format")
    @When("the user enters invalid email and saves")
    public void giveInvalidEmail() {
        projectOverviewPage.goToOverviewPage().clickDescriptionBtn().editEmail("hotmail").save();
    }

    @Step("Verify warning message is shown for invalid email")
    @Description("Checks that a warning is displayed after entering an invalid email")
    @Then("a warning message should be displayed")
    public void invalidEmailWarning(){
        projectOverviewPage.assertThatText(projectOverviewPage.warning, "Email address invalid");
    }


    @Step("Navigate to the project overview page and verify initial task count")
    @Description("Opens the Project Overview page and asserts the task count")
    @Given("Check task count")
    public void checkTaskNumber(){
        projectOverviewPage.goToOverviewPage();
        projectOverviewPage.assertThatText(projectOverviewPage.pageTitle, "1.TEST (12 /\n" + "Task limit\n" + "20)");
    }

    @Step("Set task limit to 5 and save the changes")
    @Description("Edits the task limit field and sets it to 5")
    @When("the user sets task limit to 5")
    public void insufficientNumberOfTasks() {
        projectOverviewPage.clickDescriptionBtn().editNumberOfTask(5).save();
    }

    @Step("Verify the page title reflects the updated task limit")
    @Description("Checks that the updated task limit is correctly displayed in the project title")
    @Then("the page title should reflect the updated task limit")
    public void verifyInsufficientNumberOfTask(){
        projectOverviewPage.projectAlertSuccess.should(Condition.visible);
        projectOverviewPage.assertThatText(projectOverviewPage.pageTitle, "1.TEST (12 /\n" +
                "Task limit\n" +
                "5)");
    }


    @Step("Check initial task counts in 'Work in Progress' and 'Done' columns")
    @Description("Navigates to the Board page and verifies the 'Work in Progress' and the 'Done' column tasks counts")
    @Given("The user checks the task count in the Work in Progress and Done columns")
    public void checkTaskCounts(){
        projectOverviewPage.goToOverviewPage();
        projectOverviewPage.goToBoardPage();
        projectPage.assertThatText(projectPage.workInProgressTaskCount, "Task count\n" + "2 ");
        projectPage.assertThatText(projectPage.doneTaskCount, "Task count\n" + "4 ");
    }

    @Step("Drag a task from 'Work in Progress' to 'Done'")
    @Description("Simulates a drag-and-drop action where a task is moved from the 'Work in Progress' column to the 'Done' column")
    @When("The user holds and drags a task from the Work in Progress area to the Done area")
    public void holdAndDragTaskToDoneArea(){
        projectPage.dragAndDrop(projectPage.workInProgressFirstTask, projectPage.doneArea);
        projectOverviewPage.boardTaskLimitWarning.shouldNotBe(Condition.visible);
    }

    @Step("Verify updated task counts after moving task to 'Done'")
    @Description("Checks the 'Work in Progress' task count and the 'Done' task count")
    @Then("The user verifies the task counts for the Done and Work in Progress columns")
    public void afterHoldAndDragCheckTaskCount(){
        projectPage.workInProgressTaskCount.shouldBe(Condition.visible, Duration.ofSeconds(1000));
        projectPage.doneTaskCount.shouldBe(Condition.visible, Duration.ofSeconds(1000));
        projectPage.assertThatText(projectPage.workInProgressTaskCount, "Task count\n" + "1 ");
        projectPage.assertThatText(projectPage.doneTaskCount, "Task count\n" + "5 ");
    }

    @Step("Move the first task from 'Done' back to 'Work in Progress'")
    @Description("Drags the first task in the 'Done' column back to the 'Work in Progress' and verifies the task counts return to their original state")
    @And("The first project has been moved from Done to Work in Progress")
    public void holdAndDragBackToWorkInProgressArea() {
        projectPage.dragAndDrop(projectPage.doneFirstTask, projectPage.workInProgressArea);

        projectPage.workInProgressTaskCount.shouldBe(Condition.visible, Duration.ofSeconds(1000));
        projectPage.doneTaskCount.shouldBe(Condition.visible, Duration.ofSeconds(1000));
        projectPage.assertThatText(projectPage.workInProgressTaskCount, "Task count\n" + "2 ");
        projectPage.assertThatText(projectPage.doneTaskCount, "Task count\n" + "4 ");
    }


    @Step("Set the project task limit to zero and save changes")
    @Description("Sets the task limit to zero through the description panel and saves")
    @When("the user sets task limit to zero")
    public void zeroNumberOfTask(){
        projectOverviewPage.goToOverviewPage().clickDescriptionBtn().editNumberOfTask(0).save();
    }

    @Step("Verify the project title does not display any task limit")
    @Description("Asserts that the project title is shown without the task limit after setting it to zero")
    @Then("the project title should display without task limit")
    public void verifyZeroNumberOfTask(){
        projectOverviewPage.projectAlertSuccess.should(Condition.visible);
        projectOverviewPage.assertThatText(projectOverviewPage.pageTitle, "1.TEST");
    }

    @Step("Attempt to decrease task limit from zero using arrow key")
    @Description("Tries to decrease task limit using the arrow key while it's already at zero, then saves")
    @When("the user tries to decrease the task limit using the arrow key")
    public void decreaseTaskNumberFromZero(){
        projectOverviewPage.goToOverviewPage().clickDescriptionBtn().decreaseTaskNumber(3).save();
    }

    @Step("Verify the task limit remains zero")
    @Description("Confirms that task limit remains at zero after attempting to decrease it below zero")
    @Then("the project task limit should be zero")
    public void verifyTaskNumberIsSame(){
        projectOverviewPage.projectAlertSuccess.should(Condition.visible);
        projectOverviewPage.clickDescriptionBtn();
        projectOverviewPage.assertThatText(projectOverviewPage.formTaskLimit, "");
    }

    @Step("Increase the task limit using the arrow key and save")
    @Description("Uses the arrow key to increase the task limit")
    @When("the user tries to increase the task limit using the arrow key")
    public void increaseTaskNumberFromZero(){
        projectOverviewPage.goToOverviewPage().clickDescriptionBtn().increaseTaskNumber(3).save();
    }

    @Step("Verify the task limit has been increased and displayed correctly in the title")
    @Description("Checks that the task limit has increased to three and the page title reflects the new value")
    @Then("the project task limit should be increase")
    public void verifyTaskNumberIsIncreased(){
        projectOverviewPage.projectAlertSuccess.should(Condition.visible);
        projectOverviewPage.assertThatText(projectOverviewPage.pageTitle, "1.TEST (12 /\n" +
                "Task limit\n" +
                "3)");
    }

    @Step("Set task limit to a negative number -33")
    @Description("Attempts to set the task limit to -33 and save the changes through the project overview page")
    @When("the user sets task limit to -33")
    public void negativeTaskNumber() {
        projectOverviewPage.goToOverviewPage().clickDescriptionBtn().editNumberOfTask(-33).save();
    }

    @Step("Verify warning is shown and page title reflects negative task limit")
    @Description("Asserts that a warning message is displayed and that the page title updates to include the negative task limit")
    @Then("a warning should be displayed and page title should update accordingly")
    public void verifyNegativeTaskNumberFailure(){
        projectOverviewPage.projectAlertSuccess.shouldBe(Condition.visible);
        projectOverviewPage.assertThatText(projectOverviewPage.pageTitle, "1.TEST (12 /\n" + "Task limit\n" + "-33)");
    }

    @Step("Enter 20 as the task limit value")
    @Description("Sets the task limit to 20 using the edit task limit input and verifies the success alert is visible")
    @And("Task limit value is entered 20")
    public void editTaskLimitValue(){
        projectOverviewPage.clickDescriptionBtn().editNumberOfTask(20).save();
        projectOverviewPage.projectAlertSuccess.shouldBe(Condition.visible);
    }

    @Step("Mark the project as personal via checkbox and save")
    @Description("Sets the project as personal by selecting the checkbox and saving the configuration")
    @When("the user marks the project as personal")
    public void editPersonalProjectCheckBox() {
        projectOverviewPage.goToOverviewPage().clickDescriptionBtn().clickCheckBox().save();
        projectOverviewPage.projectAlertSuccess.shouldBe(Condition.visible);
    }

    @Step("Verify project is marked as personal in settings")
    @Description("Navigates to the Configure This Project section and verifies that the project is labeled as personal")
    @Then("the project should show as personal in project settings")
    public void verifyPersonalProjectCheckBoxVisible(){
        projectOverviewPage.projectAlertSuccess.shouldBe(Condition.visible);
        projectOverviewPage.goToConfigureThisProject();

        projectSummary.assertTextInCollection(projectSummary.projectSummaryInformation, "This project is personal");
    }

    @Step("Unmark the project as personal")
    @Description("Clears the personal project checkbox and saves the settings")
    @And("project is changed to not personal")
    public void editProjectNotPersonal(){
        projectOverviewPage.clickOverviewButton().clickDescriptionBtn().clickCheckBox().save();
        projectOverviewPage.projectAlertSuccess.shouldBe(Condition.visible);
    }

    @Step("Verify project is not marked as personal in settings")
    @Description("Asserts that the 'This project is personal' label is not present in the project configuration")
    @And("the project should not show as personal in project settings")
    public void verifyPersonalProjectCheckBoxInvisible(){
        projectOverviewPage.goToConfigureThisProject();
        projectSummary.assertTextNotInCollection(projectSummary.projectSummaryInformation, "This project is personal");
    }

    @Step("Change the project owner to 'Unassigned'")
    @Description("Edits the project settings to assign the project owner to 'Unassigned'")
    @When("the user changes the project owner to Unassigned")
    public void editProjectOwner(){
        projectOverviewPage.goToOverviewPage().clickDescriptionBtn().editProjectOwner("Unassigned").save();
    }

    @Step("Verify project owner is not displayed in project settings")
    @Description("Checks that the 'Project owner: admin' label is no longer visible after assigning it to 'Unassigned'")
    @Then("the project owner should not be displayed in Configure This Project page")
    public void verifyProjectOwnerUnassigned(){
        projectOverviewPage.projectAlertSuccess.shouldBe(Condition.visible);
        projectSummary.assertTextNotInCollection(projectSummary.projectSummaryInformation, "Project owner: admin");
    }

    @Step("Change the project owner back to 'admin'")
    @Description("Resets the project owner from 'Unassigned' to 'admin' and verifies success")
    @And("the project owner is changed back to admin")
    public void projectOwnerUnassigned(){
        projectOverviewPage.clickOverviewButton().clickDescriptionBtn().editProjectOwner("admin").save();
        projectOverviewPage.projectAlertSuccess.shouldBe(Condition.visible);
    }

    @Step("Enter invalid date formats: verify fallback to 01.01.1970")
    @Description("Enters invalid start and end dates from a DataTable and verifies that both dates default to 01.01.1970 with a success message shown")
    @When("the user enters invalid dates and start and end dates should be shown as 01.01.1970")
    public void enterInvalidDateFormat(DataTable dataTable) {
        projectOverviewPage.goToOverviewPage();
        List<List<String>> dates = dataTable.asLists();

        for (List<String> row : dates) {
            String startDate = row.get(0);
            String endDate = row.get(1);
            projectOverviewPage.clickDescriptionBtn().setDate(startDate, endDate).save();
            projectOverviewPage.assertThatText(projectOverviewPage.projectAlertSuccess, "Project updated successfully.");

            assertThat(projectOverviewPage.findElementFromInformation(projectOverviewPage.informationPanel, "Start date: 01/01/1970"));
            assertThat(projectOverviewPage.findElementFromInformation(projectOverviewPage.informationPanel, "End date: 01/01/1970"));
        }
    }

    @Step("Enter valid project dates: 01/05/2025 to 05/05/2025")
    @Description("Enters valid start and end dates for the project and verifies the success alert appears")
    @And("A valid date is entered")
    public void validDateEntered(){
        projectOverviewPage.clickDescriptionBtn().setDate("1/05/2025", "5/05/2025").save();
        projectOverviewPage.assertThatText(projectOverviewPage.projectAlertSuccess, "Project updated successfully.");
    }

    @Step("Hover over project tooltip to display additional info")
    @Description("Navigates to the board page and performs a hover action on the project tooltip to reveal project details")
    @When("the user hovers over the project tooltip")
    public void hoverOverTest(){
        projectOverviewPage.goToOverviewPage().goToBoardPage();
        projectOverviewPage.hoverOver(projectOverviewPage.projectTooltip, projectOverviewPage.projectTooltipInformation);
    }

    @Step("Verify tooltip displays correct project details")
    @Description("Checks that the tooltip correctly displays project owner and additional information after hover")
    @Then("the project details should be visible")
    public void hoverOverVerify(){
        projectOverviewPage.assertThatText(projectOverviewPage.projectTooltipInformation, "Project owner: admin\n" + "Writing..");
    }

    @Step("Edit project name and click 'Close' button")
    @Description("Attempts to change the project name but clicks the 'Close' button instead of saving")
    @When("the user edits the project name and clicks close")
    public void checkCloseButton(){
        projectOverviewPage.goToOverviewPage().clickDescriptionBtn().setName("hgfg").close();
    }

    @Step("Verify project name is unchanged after clicking 'Close'")
    @Description("Asserts that the project name remains the same after clicking the 'Close' button")
    @Then("the project name should remain unchanged")
    public void verifyProjectNameIsSame(){
        projectOverviewPage.assertThatText(projectOverviewPage.pageTitle, "1.TEST (12 /\n" +
                "Task limit\n" +
                "20)");
    }

    @Step("Edit project name and click 'Cancel' button")
    @Description("Attempts to change the project name but cancels the operation")
    @When("the user edits the project name and clicks cancel")
    public void checkCancelBtn(){
        projectOverviewPage.goToOverviewPage().clickDescriptionBtn().setName("hgfg").cancel();
    }

    @Step("Verify project name remains the same after cancel")
    @Description("Verifies that the project name has not changed after clicking 'Cancel'")
    @Then("the project name should be same")
    public void verifyProjectName(){
        projectOverviewPage.assertThatText(projectOverviewPage.pageTitle, "1.TEST (12 /\n" +
                "Task limit\n" +
                "20)");
    }


    @Step("Navigate to Project Overview page and check initial task count")
    @Description("Navigates to the Project Overview page and verifies that the initial backlog task count is 3")
    @Given("the user is on the Project Overview page and check page title")
    public void beforeProjectFeatures(){
        projectOverviewPage.goToOverviewPage();
        projectOverviewPage.assertThatText(projectOverviewPage.backlogTaskCount, "Task count\n" + "3");
    }

    @Step("Add a new task named 'last task' to the backlog")
    @Description("Navigates to the Board page and adds a new task titled 'last task' to the backlog.")
    @When("the user adds a new task named last task to the backlog")
    public void addNewTaskToBacklog(){
        projectOverviewPage.goToBoardPage();
        projectPage.addTaskToBacklog("last task");
    }

    @Step("Verify the backlog task count increased to 4")
    @Description("Verifies that a success alert is shown and the backlog task count has increased from 3 to 4 after adding a new task")
    @Then("the backlog task count should increase and be displayed correctly")
    public void verifyNewTaskCount(){
        projectPage.alert.should(Condition.visible);
        projectPage.goToOverview();
        projectOverviewPage.assertThatText(projectOverviewPage.backlogTaskCount, "Task count\n" + "4");
    }

    @Step("Delete the most recently created task from the backlog")
    @Description("Deletes the most recently added task from the backlog and checks for a success alert")
    @And("Delete the newly created task")
    public void deleteLastTaskFromBacklog(){
        projectOverviewPage.goToBoardPage().deleteTask();
        projectOverviewPage.projectAlertSuccess.shouldBe(Condition.visible);
    }


    @Step("Add a task named 'BacklogTask' to the backlog")
    @Description("Navigates to the Project page and adds a task named 'BacklogTask' to the backlog")
    @When("the user adds a new task named BacklogTask to backlog")
    public void addATaskToBacklog(){
        projectOverviewPage.goToProjectPage();
        projectPage.addTaskToBacklog("BacklogTask");
    }

    @Step("Verify that 'BacklogTask' appears in the Last Activity section")
    @Description("Checks that the task named 'BacklogTask' is displayed in the Last Activity section after it is added")
    @Then("task should seen in last Activity section")
    public void verifyNewTaskInLastActivity(){
        projectPage.alert.should(Condition.visible);
        projectPage.goToOverview();
        projectOverviewPage.assertThatText(projectOverviewPage.lastTaskNameInLastActivity, "BacklogTask");
    }

    @Step("Delete the 'backlog task' from the backlog")
    @Description("Deletes the previously created 'BacklogTask' from the backlog and confirms the success alert")
    @And("The newly created task in the Backlog is deleted")
    public void deleteBacklogTaskFromBacklog(){
        projectOverviewPage.goToBoardPage().deleteTask();
        projectOverviewPage.projectAlertSuccess.shouldBe(Condition.visible);
    }
}
