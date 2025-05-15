Feature: My Projects Page Functionality
  This feature validates the functionality of the My Projects page,including navigation, viewing,
  creating, updating, deleting, sorting, and switching between different project views and sections.

  Background:The user logs in to the system with valid credentials

  Scenario: Navigate to My Projects page successfully
    When the user navigates to My Projects page
    Then the page title should be Projects overview for admin
    And the header should be My projects
    And the number of projects should be 4 projects
    And all project names should be printed
    And side buttons should be visible and clickable
    And Header buttons are displayed and clickable

  Scenario:User clicks on a project name and it opens in board view on the My Projects page
    When the user clicks on the first project name
    Then the project should open in board view

  Scenario: Open the dropdown menu next to the project name on the My Projects page
    When the user clicks on the number next to the project name in the first project
    Then the dropdown menu should be visible and clickable

  Scenario: Open the sort dropdown menu on the My Projects page
    When the user clicks the Sort button
    Then the sort dropdown menu should be visible and clickable

  Scenario: Add a new project on the My Projects page
    Given the user navigates to My Projects page and the number of projects is 4 projects
    When the user adds a new project named PROJECTTEN
    Then user checks new project visibility

  Scenario: Update the last project on the My Projects page
    Given the user navigates to My Projects page and verifies the last project details
    When the user updates the project name to 5.TEST with start date and end date
    Then the user verifies updated project details

  Scenario: Delete the last project on the My Projects page
    Given user checks the details of the last project
    When the user delete the last project
    Then the user verifies deleted project not exist

  Scenario: Sort projects by ID in ascending order on the My Projects page
    When the user navigates to My Projects page and sort projects by ID in ascending order
    Then the user verifies the projects are sorted by ID in ascending order

  Scenario: Sort projects by Name in ascending order on the My Projects page
    When the user navigates to My Projects page and the projects are sorted by name in ascending order
    Then the user verifies the projects are sorted by name in ascending order

  Scenario: Go to KB page from the My Projects page
    When the user navigates to My Projects page and the user clicks on the KB button
    Then the page title should be Dashboard For Admin

  Scenario: The user navigates to My Projects page and switches to board view
    When the user navigates to My Projects page and the user switches to board view for Last Project
    Then the active view should be Board

  Scenario:The user navigates to My Projects page and switches to list view
    When the user navigates to My Projects page and the user switches to list view for Last Project
    Then the active view should be List

  Scenario:The user navigates to My Projects page and opens Project Activity page
    When the user navigates to activity page for Last Project
    Then view should be Activity page

  Scenario:The user navigates to My Projects page and opens Project Analytics page
    When the user navigates to Analytics page for Last Project
    Then view should be Analytics page

  Scenario:The user navigates to My Projects page and opens Configure this Project page
    When the user navigates to Configure this Project for Last Project
    Then page should be Configure this Project page