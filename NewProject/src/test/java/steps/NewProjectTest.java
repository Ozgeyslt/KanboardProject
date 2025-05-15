package steps;

import com.codeborne.selenide.Condition;
import common.PageManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import pages.KBPage;
import pages.NewProject;
import pages.ProjectSummary;
import testData.NewProjectData;

@Feature("Project Creation Functionality")
public class NewProjectTest {

    protected PageManager pageManager=new PageManager();
    NewProject newProject = pageManager.getNewProject();
    ProjectSummary projectSummary =pageManager.getProjectSummary();
    KBPage kbPage = pageManager.getKbPage();

    @Step("Navigate to the New Project page")
    @Description("Opens the 'New Project'")
    @Given("Navigate To New Project Page")
    public void navigateToNewProjectPage() {
        newProject.openNewProject();
    }

    @Step("Verify New Project page opened successfully")
    @Description("Checks the visibility of the header on the New Project page")
    @Then("The new project page opens successfully")
    public void NewProjectPageOpenSuccessfully(){
        newProject.assertThatText(newProject.newProjectHeader, "New project");
    }

    @Step("Check required fields on the New Project page")
    @Description("Ensures all input fields and their labels are present")
    @And("Checked Required fields are displayed")
    public void assertRequiredFieldsAreDisplayed(){
        newProject.assertThatText(newProject.newProjectHeader, "New project");
        newProject.nameLabel.shouldBe(Condition.visible);
        newProject.identifierLabel.shouldBe(Condition.visible);
        newProject.taskLimitLabel.shouldBe(Condition.visible);
        newProject.createFromAnotherProjectLabel.shouldBe(Condition.visible);

        newProject.assertThatText(newProject.identifierComment,
                "The project identifier is optional and must be alphanumeric, example: MYPROJECT.");
        newProject.assertThatText(newProject.checkBoxText, " Column task limits apply to each swimlane individually");
    }

    @Step("Check if required fields are clickable")
    @Description("Verifies clickability of form input fields and buttons on the New Project page")
    @And("Checked Required fields are clickable")
    public void assertRequiredFieldsAreClickable(){
        newProject.assertThatText(newProject.newProjectHeader, "New project");

        newProject.assertElementVisibleAndClickable(newProject.name);
        newProject.assertElementVisibleAndClickable(newProject.identifier);
        newProject.assertElementVisibleAndClickable(newProject.taskLimit);
        newProject.assertElementVisibleAndClickable(newProject.createFromAnotherProjectLabel);
        newProject.assertElementVisibleAndClickable(newProject.checkbox);
        newProject.assertElementVisibleAndClickable(newProject.saveButton);
        newProject.assertElementVisibleAndClickable(newProject.cancelButton);
        newProject.assertElementVisibleAndClickable(newProject.closeButton);
    }


    @Step("Try to save project without a name")
    @Description("Attempts to save the form without entering the project name")
    @When("Click Save button without entering name")
    public void openNewProjectAndSave(){
        newProject.openNewProject().submit();
    }

    @Step("Verify warning for missing project name")
    @Description("Checks if the validation message appears when the project name is missing")
    @Then("Warning Message should be seen for project name")
    public void nameWarning(){
        newProject.assertWarningDisplayed("The project name is required");
    }


    @Step("Create project named PROJECTONE")
    @Description("Enters a valid project name and saves the project")
    @When("Enter project name PROJECTONE and save")
    public void giveNameAndSave() {
        newProject.openNewProject().withName("PROJECTONE").submit();
    }

    @Step("Verify project PROJECTONE is created")
    @Description("Asserts that the project creation was successful and the summary is displayed")
    @Then("PROJECTONE project is created successfully")
    public void verificationNewProject(){
        projectSummary.alertSuccessWarning.shouldBe(Condition.visible);
        projectSummary.summaryTitle.shouldBe(Condition.visible);
        projectSummary.assertThatText(projectSummary.projectTitle, NewProjectData.projectName);

        newProject.removeProject();
    }


    @Step("Entering project name with special character 'PLP%60' and saving the project")
    @Description("Enter a project name with a special character and save it")
    @When("Enter name with special character and save")
    public void giveNameWithSpecialCharacter() {
        newProject.openNewProject().withName("PLP%60").submit();
        projectSummary.alertSuccessWarning.shouldBe(Condition.visible);
    }

    @Step("Verify that the project with name 'PLP%60' is created and displayed correctly")
    @Description("Verify that the project name with special characters is created successfully")
    @Then("Project name with special characters created successfully")
    public void verificationOfProjectNameWithSpecialCharacter(){
        projectSummary.summaryTitle.shouldBe(Condition.visible);
        projectSummary.assertThatText(projectSummary.projectTitle, "PLP%60");

        newProject.removeProject();
    }


    @Step("Entering project name 'PLP' and identifier 'P&P' and saving the project")
    @Description("Enter a project name and an identifier with special characters and save")
    @When("Enter the project name PLP and the identifier P&P")
    public void giveIdentifierWithSpecialCharacter() {
        newProject.openNewProject().withName("PLP").withIdentifier("P&P").submit();
    }

    @Step("Verifying the warning message for identifier 'P&P' to ensure it is alphanumeric")
    @Description("Verify that the warning message appears for an invalid identifier with special characters")
    @Then("The warning message appears for identifier")
    public void warningMessageForIdentifier(){
        newProject.assertWarningDisplayed( "This value must be alphanumeric");
    }


    @Step("Entering project name 'PROJECTONE' and identifier 'SEVEN' and submitting the project")
    @Description("Enter a valid project name and identifier, then submit the project")
    @When("Enter the project name PROJECTONE and the identifier SEVEN")
    public void giveNameAndIdentifier() {
        newProject.openNewProject().withName(NewProjectData.projectName)
                .withIdentifier(NewProjectData.projectIdentifier).submit();
    }

    @Step("Verifying the successful creation of project 'PROJECTONE' and displaying it in the project summary")
    @Description("Verify that the project 'PROJECTONE' is successfully created with the identifier 'SEVEN'")
    @Then("The project PROJECTONE should be created")
    public void verifyProjectCreatedWithIdentifier(){
        projectSummary.alertSuccessWarning.shouldBe(Condition.visible);
        projectSummary.summaryTitle.shouldBe(Condition.visible);
        projectSummary.assertThatText(projectSummary.projectTitle, NewProjectData.projectName);

        newProject.removeProject();
    }


    @Step("Entering project name 'PROJECTTWO' with an already used identifier 'TWO' and submitting")
    @Description("Attempt to create a new project with a duplicate identifier")
    @When("Enter the project name PROJECTTWO and the identifier TWO")
    public void giveDifferentNameAndSameIdentifier() {
        newProject.openNewProject().withName("PROJECTTWO")
                .withIdentifier("TWO").submit();
    }


    @Step("Verify warning message: 'The identifier must be unique' is displayed")
    @Description("Verify the warning message when a duplicate identifier is used")
    @Then("The identifier warning message appears as The identifier must be unique")
    public void verifyIdentifierWarning(){
        newProject.assertWarningDisplayed( "The identifier must be unique");
    }


    @Step("Entering project name 'PROJECTONE', identifier 'SEVEN', and task limit '10', then submitting the form")
    @Description("Create a new project with name, identifier, and task limit")
    @When("Enter the project name PROJECTONE, identifier SEVEN, and task limit 10")
    public void giveNameIdentifierAndTaskLimit() {
        newProject.openNewProject().withName(NewProjectData.projectName)
                .withIdentifier(NewProjectData.projectIdentifier).withTaskLimit(NewProjectData.projectTaskLimit)
                .submit();
    }

    @Step("Verifying project creation with task limit '10' and checking displayed project title and task limit")
    @Description("Verify that a project with task limit is successfully created")
    @Then("The project PROJECTONE with task limit 10 should be created successfully")
    public void verifyProjectWithTaskLimit() {
        projectSummary.alertSuccessWarning.shouldBe(Condition.visible);
        projectSummary.assertThatText(projectSummary.projectTitle, "PROJECTONE (0 /\n" + "Task limit\n" + "10)");
        projectSummary.assertThatText(projectSummary.taskLimit, "Task limit: 10");

        newProject.removeProject();
    }


    @Step("Attempting to create project with identifier 'SEVEN' and task limit '10' but leaving name field empty")
    @Description("Try to create a project without providing a name")
    @When("Enter identifier SEVEN and task limit 10 without a project name")
    public void giveIdentifierAndTaskLimitWithoutName(){
        newProject.openNewProject().withIdentifier(NewProjectData.projectIdentifier)
                .withTaskLimit(NewProjectData.projectTaskLimit).submit();
    }

    @Step("Verifying warning: 'The project name is required' is displayed when name field is empty")
    @Description("Verify that a warning message appears when project name is not entered")
    @Then("Warning message NAME IS REQUIRED should be displayed")
    public void verifyWarningMessage(){
        newProject.assertWarningDisplayed("The project name is required");
    }


    @Step("Filling project details with task limit and enabling task limit checkbox before submitting")
    @Description("Create a project with task limit and enable task limit checkbox")
    @When("Enter name PROJECTONE, identifier SEVEN, task limit 10 and check the task limit checkbox")
    public void giveNameIdentifierTaskLimitClickAndCheckBox() {
        newProject.openNewProject().withName(NewProjectData.projectName)
                .withIdentifier(NewProjectData.projectIdentifier)
                .withTaskLimit(NewProjectData.projectTaskLimit)
                .enableTaskLimitCheckbox().submit();
    }

    @Step("Verifying project title, task limit value, and checkbox confirmation message after creation")
    @Description("Verify successful creation of project with task limit and checkbox confirmation")
    @Then("The project PROJECTONE should be created with task limit 10 and checkbox confirmation message")
    public void verifyProjectWithCheckbox() {
        projectSummary.alertSuccessWarning.shouldBe(Condition.visible);
        projectSummary.summaryTitle.shouldBe(Condition.visible);
        projectSummary.assertThatText(projectSummary.projectTitle, "PROJECTONE (0 /\n" + "Task limit\n" + "10)");
        projectSummary.assertThatText(projectSummary.taskLimit, "Task limit: 10");
        projectSummary.assertThatText(projectSummary.taskLimitCheckBox, "Column task limits are applied to each swimlane individually");

        newProject.removeProject();
    }


    @Step("Entering identifier 'SEVEN' and task limit '10', checking task limit checkbox, and submitting without a project name.")
    @Description("Try to create a project with identifier and task limit, and check the task limit checkbox, but leave the name field empty")
    @When("Enter identifier SEVEN and task limit 10 and check the task limit checkbox without entering a name")
   public void giveIdentifierTaskLimitCheckBoxWithoutName() {
       newProject.openNewProject().withIdentifier(NewProjectData.projectIdentifier)
               .withTaskLimit(NewProjectData.projectTaskLimit)
               .enableTaskLimitCheckbox()
               .submit();
   }

   @Step("Verifying that 'The project name is required' warning is displayed")
   @Description("Verify that a warning message is shown when project name is missing")
   @Then("Warning that the project name is required")
   public void verifyNameIsRequiredWarning(){
        newProject.assertWarningDisplayed("The project name is required");
   }


   @Step("Duplicating project with name 'PROJECTONE' and identifier 'SEVEN' without a task limit")
   @Description("Duplicate a project by providing name and identifier but without setting a task limit")
   @When("Duplicate the project with name PROJECTONE, identifier SEVEN and no task limit")
   public void duplicateProjectAndSave() {
       newProject.openNewProject().withName(NewProjectData.projectName)
               .withIdentifier(NewProjectData.projectIdentifier).duplicateAnotherProject().submit();
   }

   @Step("Confirming that duplicated project has task limit set to 5")
   @Description("Verify that the duplicated project inherits a task limit of 5.")
   @Then("Duplicated project's task limit is 5")
    public void verifyDuplicatedProjectTaskLimit(){
       projectSummary.alertSuccessWarning.shouldBe(Condition.visible);
       projectSummary.assertTextInCollection(projectSummary.projectSummaryInformation, "Task limit: 5");

       newProject.removeProject();
   }


   @Step("Duplicating project using identifier 'SEVEN' and task limit '10' without entering a name")
   @Description("Attempt to duplicate a project using identifier and task limit without providing a name")
   @When("Duplicate the project without a name using identifier SEVEN and task limit 10")
   public void duplicateProjectWithoutName(){
        newProject.openNewProject().withIdentifier(NewProjectData.projectIdentifier)
                .withTaskLimit(NewProjectData.projectTaskLimit).duplicateAnotherProject().submit();
   }

   @Step("Verifying that 'The project name is required' warning is shown when name is missing during duplication")
   @Description("Verify the warning message when duplicating a project without providing a name")
   @Then("Warning message is displayed")
   public void verifyNameWarningForDuplicateWithoutName(){
        newProject.assertWarningDisplayed( "The project name is required");
   }


   @Step("Verifying that the project count is '4 projects'")
   @Description("Ensure that there are exactly 4 projects displayed on the Kanboard page")
   @Given("There are 4 projects")
    public void projectCount(){
        kbPage.assertThatText(kbPage.projectCount, "4 projects");
    }

    @Step("Entering project name 'PROJECTONE' and canceling the project creation process")
    @Description("Attempt to create a project with the name 'PROJECTONE' and then cancel the creation process")
    @When("Enter project name PROJECTONE and cancel project creation")
   public void giveNameAndCancel(){
        newProject.openNewProject().withName(NewProjectData.projectName).cancel();
   }

   @Step("Confirming that 'PROJECTONE' name is not visible and the project count remains '4 projects'")
   @Description("Verify that the project 'PROJECTONE' is not visible and the total project count remains 4")
   @Then("PROJECTONE name is not visible and project number is 4")
   public void verifyProjectCountUnchanged(){
        kbPage.assertTextNotInCollection(kbPage.projectTitles, "PROJECTONE");
        kbPage.assertThatText(kbPage.projectCount, "4 projects");
   }


   @Step("Checking the project count to ensure it is '4 projects'")
   @Description("Verify the current project count displayed on the Kanboard page")
   @Given("Check project count")
   public void checkProjectCount(){
       kbPage.assertThatText(kbPage.projectCount, "4 projects");
   }

   @Step("Entering project name 'PROJECTONE' and closing the project creation form")
   @Description("Create a project with the name 'PROJECTONE' and close the form without saving")
   @When("Enter project name PROJECTONE and close page")
   public void giveNameAndClose() {
       newProject.openNewProject().withName(NewProjectData.projectName).close();
   }

   @Step("Confirming that 'PROJECTONE' name is not visible and the project count remains '4 projects'")
   @Description("Ensure that the project 'PROJECTONE' is not created and the total project count remains 4")
   @Then("New project name is not visible and project number is 4")
   public void checkForProjectCountAgain(){
        kbPage.assertTextNotInCollection(kbPage.projectTitles, "PROJECTONE");
        kbPage.assertThatText(kbPage.projectCount, "4 projects");
   }

   @Step("Creating a new project with the name 'PROJECTONE'")
   @Description("Create a new project with the name 'PROJECTONE'")
    @Given("Create a project with name PROJECTONE")
    public void addANewProject(){
        newProject.openNewProject().withName(NewProjectData.projectName).submit();
        projectSummary.alertSuccessWarning.shouldBe(Condition.visible);
    }

    @Step("Creating a second project with the name 'PROJECTONE' and identifier 'ONE'")
    @Description("Create a new project with the same name 'PROJECTONE' but with a different identifier 'ONE'")
   @When("Create another project with same name but different identifier")
   public void newProjectSameNameDifferentIdentifier() {
        newProject.goToKanboardPage().openNewProject().withName(NewProjectData.projectName)
                .withIdentifier("ONE").submit();
   }

    @Step("Confirming that the second project 'PROJECTONE' is created successfully and deleted")
    @Description("Verify that the second project 'PROJECTONE' is created and then deleted")
   @Then("The second project PROJECTONE should be created successfully and deleted")
    public void theProjectShouldBeCreatedSuccessfully(){
        projectSummary.alertSuccessWarning.shouldBe(Condition.visible);
        projectSummary.clickWebElement(projectSummary.kbButton);
        kbPage.assertProjectNameAppearsTwice(kbPage.projectTitles, "PROJECTONE");
        newProject.openConfigureThisProject();
        newProject.removeProject();
    }

    @Step("Deleting the first project 'PROJECTONE'")
    @Description("Ensure that the first project 'PROJECTONE' is deleted")
    @And("The first project PROJECTONE is deleted")
    public void deleteProjectFour() {
      newProject.openConfigureThisProject();
      newProject.removeProject();
    }
}
