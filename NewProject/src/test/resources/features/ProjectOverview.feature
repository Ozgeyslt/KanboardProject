Feature: Project Configuration and Task Management
  This feature ensures that users can view and edit project details, manage task limits, validate input fields,
  and handle task creation and deletion accurately within the Project Overview and settings pages.

  Background:The user logs in to the system with valid credentials

  Scenario: User navigates to the Project Overview page and verifies page elements and task counts
    When the user navigates to the Project Overview page
    Then the page title should be visible
    And Project Setting Titles should be visible and clickable
    And The task counts for the Backlog, Ready, Work in Progress, and Done titles should be visible
    And the task counts should match expected values

  Scenario: Warning when project name is empty
    When the user edits the project name to empty and saves
    Then a warning This field cannot be empty should be displayed

  Scenario: Edit project name with special characters
    When the user edits the project name with special characters and saves
    Then the page title should reflect the new name with special characters

  Scenario: Edit project name and start-end dates
    When the user edits the name and dates
    Then the page title and information dates should be updated accordingly

  Scenario: Edit description and email successfully
    When the user edits the description and email
    Then the description and email should be updated
    And The first e-mail address is entered

  Scenario: Warning for invalid email input
    When the user enters invalid email and saves
    Then a warning message should be displayed

  Scenario: Set the task limit lower than the number of tasks
    Given Check task count
    When the user sets task limit to 5
    Then the page title should reflect the updated task limit

  Scenario: Moving a task from Work in Progress to Done updates the task counts correctly
    Given The user checks the task count in the Work in Progress and Done columns
    When The user holds and drags a task from the Work in Progress area to the Done area
    Then  The user verifies the task counts for the Done and Work in Progress columns
    And The first project has been moved from Done to Work in Progress

  Scenario: Set the task limit value to zero
    When the user sets task limit to zero
    Then the project title should display without task limit

  Scenario:The task limit cannot be lowered below zero with the arrow keys
      When the user tries to decrease the task limit using the arrow key
      Then the project task limit should be zero

  Scenario: The task limit is increased with the arrow keys
    When the user tries to increase the task limit using the arrow key
    Then the project task limit should be increase

  Scenario: Set a negative task limit, see warning, and update to valid value
    When the user sets task limit to -33
    Then a warning should be displayed and page title should update accordingly
    And Task limit value is entered 20

  Scenario: Mark project as personal, verify in settings, then unmark it
    When the user marks the project as personal
    Then the project should show as personal in project settings
    And project is changed to not personal
    And the project should not show as personal in project settings

  Scenario: Changing the project owner to Unassigned removes the project owner from the Configure This Project page
    When the user changes the project owner to Unassigned
    Then the project owner should not be displayed in Configure This Project page
    And the project owner is changed back to admin

  Scenario: Enter invalid date format for project
    When the user enters invalid dates and start and end dates should be shown as 01.01.1970
      | startDate  | endDate    |
      | 0/11/2024  | 1/11/2024  |
      | 32/11/2024 | 1/12/2024  |
      | 1/0/2024   | 1/1/2024   |
      | 1/13/2024  | 1/1/2025   |
      | 11/12/2024 | 11/12/2023 |
    And A valid date is entered

  Scenario: Project details are displayed when the user hovers over the element
    When the user hovers over the project tooltip
    Then the project details should be visible

  Scenario: Check close button behavior
    When the user edits the project name and clicks close
    Then the project name should remain unchanged

  Scenario: Check cancel button behavior
    When the user edits the project name and clicks cancel
    Then the project name should be same

  Scenario: Adding a task to the backlog increases the task count and the task is then deleted
    Given the user is on the Project Overview page and check page title
    When the user adds a new task named last task to the backlog
    Then the backlog task count should increase and be displayed correctly
    And Delete the newly created task

  Scenario: Scenario: Adding a new task to the backlog and verifying it appears in Last Activity
    When the user adds a new task named BacklogTask to backlog
    Then task should seen in last Activity section
    And The newly created task in the Backlog is deleted
