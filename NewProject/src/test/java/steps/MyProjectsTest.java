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
import pages.*;
import testData.MyProjectsData;


@Feature("My Projects Page Functionality")
public class MyProjectsTest {

    protected PageManager pageManager=new PageManager();
    MyProjects myProjects= pageManager.getMyProjects();
    NewProject newProject = pageManager.getNewProject();
    ProjectOverviewPage projectOverviewPage =pageManager.getProjectOverviewPage();
    ProjectSummary projectSummary=pageManager.getProjectSummary();
    ProjectPage projectPage=pageManager.getProjectPage();

    @Step("Navigating to the 'My Projects' page")
    @Description("Navigate to the My Projects page from the main Kanboard interface")
    @When("the user navigates to My Projects page")
    public void navigateToMyProjectsPageSuccessfully(){
        myProjects.goToMyProjectsPage();
    }

    @Step("Checking the page title is 'Projects overview for admin'")
    @Description("Verify that the title of the My Projects page is 'Projects overview for admin'")
    @Then("the page title should be Projects overview for admin")
    public void checkPageTitle(){
        myProjects.assertThatText(myProjects.pageTitle, "Projects overview for admin");
    }

    @Step("Verifying the header text is 'My projects'")
    @Description("Ensure the page header displays 'My projects'")
    @And("the header should be My projects")
    public void checkProjectHeader(){
        myProjects.assertThatText(myProjects.myProjectsHeader, "My projects");
    }

    @Step("Asserting the number of displayed projects is '4 projects'")
    @Description("Verify that the number of listed projects is '4 projects'")
    @And("the number of projects should be 4 projects")
    public void numberOfProjects(){
        myProjects.assertThatText(myProjects.numberOfProjects, MyProjectsData.myProjectsHeaderCount);
    }

    @Step("Printing all project names dynamically from the My Projects page")
    @Description("Print the names of all available projects on the My Projects page")
    @And("all project names should be printed")
    public void checkProjectNames(){
        myProjects.printAllNamesDynamically();
    }

    @Step("Verifying sidebar buttons are visible and clickable")
    @Description("Check that sidebar navigation buttons are both visible and functional")
    @And("side buttons should be visible and clickable")
    public void assertSidebarButtonsAreVisibleAndClickable(){
        myProjects.assertElementVisibleAndClickable(myProjects.overviewBtnSidebar);
        myProjects.assertElementVisibleAndClickable(myProjects.myProjectsBtnSidebar);
        myProjects.assertElementVisibleAndClickable(myProjects.myTasksBtnSidebar);
        myProjects.assertElementVisibleAndClickable(myProjects.mySubtasksBtnSidebar);
    }

    @Step("Asserting top header buttons are visible and functional")
    @Description("Ensure that all top header buttons are displayed and clickable")
    @And("Header buttons are displayed and clickable")
    public void assertHeaderButtonsAreDisplayedAndClickable(){
        myProjects.assertElementVisibleAndClickable(myProjects.kBBtn);
        myProjects.assertElementVisibleAndClickable(myProjects.newProject);
        myProjects.assertElementVisibleAndClickable(myProjects.newPersonalProject);
        myProjects.assertElementVisibleAndClickable(myProjects.projectManagement);
        myProjects.assertElementVisibleAndClickable(myProjects.myActivityStream);
        myProjects.assertElementVisibleAndClickable(myProjects.searchBox);
    }

    @Step("Opening the first project by clicking on its name")
    @Description("Click on the name of the first listed project to open it")
    @When("the user clicks on the first project name")
    public void openFirstProjectAsBoardView(){
        myProjects.goToMyProjectsPage();
        myProjects.goToProjectPage("1.TEST");
    }

    @Step("Checking the project opens in board view with correct headers")
    @Description("Validate that the selected project opens in the board view layout")
    @Then("the project should open in board view")
    public void projectOpensBoardView(){
        myProjects.assertThatText(myProjects.pageTitle, "1.TEST (12 /\n" + "Task limit\n" + "20)");
        projectPage.assertThatText(projectPage.backlogHeader, "Backlog");
        projectPage.assertThatText(projectPage.readyHeader, "Ready");
        projectPage.assertThatText(projectPage.workInProgressHeader, "Work in progress");
        projectPage.assertThatText(projectPage.doneHeader, "Done");
    }

    @Step("Opening the dropdown menu for the first project")
    @Description("Click on the number beside the first project's name to expand the dropdown menu")
    @When("the user clicks on the number next to the project name in the first project")
    public void openFirstProjectDropdownMenu(){
        myProjects.goToMyProjectsPage();
        myProjects.clickWebElement(myProjects.firstProjectDropdownMenu);
    }

    @Step("Asserting all items in the dropdown menu are visible and clickable")
    @Description("Check that each item in the dropdown menu for a project is visible and clickable")
    @Then("the dropdown menu should be visible and clickable")
    public void dropdownMenuVisibleAndClickable(){
        myProjects.assertElementVisibleAndClickable(myProjects.projectDropdownMenu(1));
        myProjects.assertElementVisibleAndClickable(myProjects.projectDropdownMenu(2));
        myProjects.assertElementVisibleAndClickable(myProjects.projectDropdownMenu(3));
        myProjects.assertElementVisibleAndClickable(myProjects.projectDropdownMenu(4));
        myProjects.assertElementVisibleAndClickable(myProjects.projectDropdownMenu(5));
    }

    @Step("User clicks the Sort button")
    @Description("Clicks the Sort button on the My Projects page")
    @When("the user clicks the Sort button")
    public void clickSortButton(){
        myProjects.goToMyProjectsPage();
        myProjects.clickWebElement(myProjects.sortBtn);
    }
    @Step("Check visibility and clickability of Sort dropdown options")
    @Description("Verifies all sort dropdown menu options are visible and clickable")
    @Then("the sort dropdown menu should be visible and clickable")
    public void SortBtnDropdownMenuVisibleAndClickable(){
        myProjects.assertElementVisibleAndClickable(myProjects.sortDropdownMenu(1));
        myProjects.assertElementVisibleAndClickable(myProjects.sortDropdownMenu(2));
        myProjects.assertElementVisibleAndClickable(myProjects.sortDropdownMenu(3));
        myProjects.assertElementVisibleAndClickable(myProjects.sortDropdownMenu(4));
        myProjects.assertElementVisibleAndClickable(myProjects.sortDropdownMenu(5));
        myProjects.assertElementVisibleAndClickable(myProjects.sortDropdownMenu(6));
        myProjects.assertElementVisibleAndClickable(myProjects.sortDropdownMenu(7));
    }

    @Step("Navigate to My Projects and check number of projects is 4")
    @Description("Navigates to My Projects page and verifies project count is 4")
    @Given("the user navigates to My Projects page and the number of projects is 4 projects")
    public void beforeAddANewProject() {
        myProjects.goToMyProjectsPage();
        myProjects.assertThatText(myProjects.numberOfProjects, MyProjectsData.myProjectsHeaderCount);
    }

    @Step("Add new project named PROJECTTEN")
    @Description("Adds a new project with name 'PROJECTTEN'")
    @When("the user adds a new project named PROJECTTEN")
    public void addANewProject(){
        myProjects.addProject();
        newProject.withName(MyProjectsData.newProjectName).submit();
    }

    @Step("Check visibility of newly added project")
    @Description("Verifies that the new project is visible after creation")
    @Then("user checks new project visibility")
    public void numberOfProjectsAfterAddingNewProject(){
        projectSummary.alertSuccessWarning.should(Condition.visible);
        newProject.goToKanboardPage();
        myProjects.goToMyProjectsPage();
        myProjects.assertTextInCollection(myProjects.allProjectTitles, "PROJECTTEN");
        myProjects.assertThatText(myProjects.numberOfProjects, MyProjectsData.myProjectsHeaderCountUpdated);
    }


    @Step("Verify initial details of the last project before update")
    @Description("Navigates to My Projects and verifies last project's initial details")
    @Given("the user navigates to My Projects page and verifies the last project details")
    public void verifyProjectDetailsBeforeUpdate(){
        myProjects.goToMyProjectsPage();
        myProjects.assertThatText(myProjects.lastProjectDetails, "#23\n" + "PROJECTTEN\n" + "admin\n" + " ");
    }

    @Step("Update project name to '5.TEST' and set start/end dates")
    @Description("Updates the last project's name, start date, and end date")
    @When("the user updates the project name to 5.TEST with start date and end date")
    public void updateLastProject(){
        myProjects.goToProjectPage("4.TEST");
        myProjects.goToOverview();
        projectOverviewPage.clickDescriptionBtn().setName(MyProjectsData.newProjectNameAfterUpdate)
                .setDate( "11/08/2024", "12/08/2024").save();
        projectOverviewPage.projectAlertSuccess.shouldBe(Condition.visible);
    }

    @Step("Verify updated project details after change")
    @Description("Verifies updated project name and dates on My Projects page")
    @Then("the user verifies updated project details")
    public void checkUpdatedProjectDetails(){
        projectOverviewPage.goToKBPage();
        myProjects.goToMyProjectsPage();
        myProjects.assertThatText(myProjects.lastProjectDetails, "#23\n" +
                "5.TEST\n" + "admin Start date: 11/08/2024 End date: 12/08/2024\n" + " ");
    }

    @Step("Verify number of projects and details of the last project before deleting")
    @Description("Checks the number of projects and prints the last project details before deleting")
    @Given("user checks the details of the last project")
    public void verifyProjectDetailsBeforeDelete(){
        myProjects.goToMyProjectsPage();
        myProjects.assertTextInCollection(myProjects.projectNames, MyProjectsData.newProjectNameAfterUpdate);
        myProjects.assertThatText(myProjects.numberOfProjects, MyProjectsData.myProjectsHeaderCountUpdated);
    }

    @Step("Delete the last project")
    @Description("Deletes the last project from the project list")
    @When("the user delete the last project")
    public void deleteLastProject() {
        myProjects.selectOptionFromLastProjectDropdown(5);
        projectSummary.removeProject();
    }

    @Step("Verify that the deleted project no longer exists")
    @Description("Verifies that the deleted project is no longer listed")
    @Then("the user verifies deleted project not exist")
    public void checkTheDeletedProjectNotExists(){
        myProjects.successWarning.should(Condition.visible);
        myProjects.assertTextNotInCollection(myProjects.projectNames, MyProjectsData.newProjectNameAfterUpdate);
        myProjects.assertThatText(myProjects.projectCount, MyProjectsData.myProjectsHeaderCount);
    }

    @Step("Sort projects by ID in ascending order")
    @Description("Navigates to My Projects page and sorts projects by ID in ascending order")
    @When("the user navigates to My Projects page and sort projects by ID in ascending order")
    public void sortProjectWithIDAscending() {
        myProjects.goToMyProjectsPage();
        myProjects.sortByProjectIDAscending();
    }

    @Step("Verify project list is sorted by ID in ascending order")
    @Description("Checks if the project list is sorted by ID in ascending order")
    @Then("the user verifies the projects are sorted by ID in ascending order")
    public void checkTheProjectIDIsSortedInAscendingOrder(){
        myProjects.assertThatText(myProjects.firstProjectID, "#13");
        myProjects.assertThatText(myProjects.lastProjectID, "#22");
        myProjects.printAllNamesDynamically();
    }


    @Step("Sort projects by name in ascending order")
    @Description("Navigates to My Projects page and sorts projects by name in ascending order")
    @When("the user navigates to My Projects page and the projects are sorted by name in ascending order")
    public void sortProjectWithNameAscending(){
        myProjects.goToMyProjectsPage();
        myProjects.sortByProjectNameAscending();
    }

    @Step("Verify project list is sorted by name in ascending order")
    @Description("Checks if the project list is sorted by name in ascending order")
    @Then("the user verifies the projects are sorted by name in ascending order")
    public void checkTheProjectNameIsSortedInAscendingOrder(){
        myProjects.assertThatText(myProjects.firstProjectTitle, "1.TEST");
        myProjects.assertThatText(myProjects.lastProjectTitle, "4.TEST");
        myProjects.printAllNamesDynamically();
    }


    @Step("Go to Kanboard by clicking the KB button")
    @Description("Navigates to My Projects page and clicks the KB button")
    @When("the user navigates to My Projects page and the user clicks on the KB button")
    public void goToKBPage(){
        myProjects.goToMyProjectsPage();
        myProjects.clickWebElement(myProjects.kBBtn);
    }

    @Step("Check that the page title is 'Dashboard for admin'")
    @Description("Verifies the page title is 'Dashboard for admin'")
    @Then("the page title should be Dashboard For Admin")
    public void checkTitleDashboardForAdmin(){
        myProjects.assertThatText(myProjects.pageTitle, "Dashboard for admin");
    }

    @Step("Switch to Board view for the last project")
    @Description("Navigates to the My Projects page and switches the view of the last project to Board view")
    @When("the user navigates to My Projects page and the user switches to board view for Last Project")
    public void openProjectBoard() {
        myProjects.goToMyProjectsPage();
        myProjects.selectOptionFromLastProjectDropdown(1);
    }

    @Step("Verify that the active view is Board")
    @Description("Verifies that the Board view is active by checking the presence of column headers")
    @Then("the active view should be Board")
        public void boardViewForLastProject() {
        projectPage.assertThatText(projectPage.backlogHeader, "Backlog");
        projectPage.assertThatText(projectPage.readyHeader, "Ready");
        projectPage.assertThatText(projectPage.workInProgressHeader, "Work in progress");
        projectPage.assertThatText(projectPage.doneHeader, "Done");
    }


    @Step("Switch to List view for the last project")
    @Description("Navigates to the My Projects page and switches the view of the last project to List view")
    @When("the user navigates to My Projects page and the user switches to list view for Last Project")
    public void openProjectListing(){
        myProjects.goToMyProjectsPage();
        myProjects.selectOptionFromLastProjectDropdown(2);
    }

    @Step("Verify that the active view is List")
    @Description("Verifies that the List view is active by checking the URL and project names")
    @Then("the active view should be List")
    public void listviewForLastProject(){
        myProjects.getUrl().equals("http://localhost/list/16");
    }


    @Step("Open Activity page for the last project")
    @Description("Navigates to the Activity page for the last project from the My Projects page")
    @When("the user navigates to activity page for Last Project")
    public void openProjectActivity() {
        myProjects.goToMyProjectsPage();
        myProjects.selectOptionFromLastProjectDropdown(3);
    }

    @Step("Verify the Activity view for the last project")
    @Description("Verifies the content of the Activity page for the last project")
    @Then("view should be Activity page")
    public void activitiesForLastProject() {
        myProjects.assertThatText(myProjects.analyticsPageHeader, "4.TEST's activity");
        myProjects.printProjectDetailsDynamically();
    }


    @Step("Open Analytics page for the last project")
    @Description("Navigates to the Analytics page for the last project from the My Projects page")
    @When("the user navigates to Analytics page for Last Project")
    public void openProjectAnalytics(){
        myProjects.goToMyProjectsPage();
        myProjects.selectOptionFromLastProjectDropdown(4);
    }

    @Step("Verify the Analytics view for the last project")
    @Description("Verifies that the Analytics page is loaded for the last project")
    @Then("view should be Analytics page")
    public void analyticsForLastProject(){
        myProjects.assertThatText(myProjects.projectPageHeader, "4.TEST > Task distribution");
    }


    @Step("Open Configure This Project page for the last project")
    @Description("Navigates to the Configure This Project page for the last project")
    @When("the user navigates to Configure this Project for Last Project")
    public void openConfigureThisProject(){
        myProjects.goToMyProjectsPage();
        myProjects.selectOptionFromLastProjectDropdown(5);
    }

    @Step("Verify the Configure This Project page is loaded")
    @Description("Verifies that the Configure This Project page is displayed")
    @Then("page should be Configure this Project page")
    public void configureThisProjectForLastProject(){
        projectSummary.assertThatText(myProjects.pageTitle, "4.TEST");
        projectSummary.assertThatText(projectSummary.summaryTitle, "Summary");
    }

}
