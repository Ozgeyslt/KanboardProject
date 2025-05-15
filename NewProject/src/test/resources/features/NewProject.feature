Feature: Project Creation Functionality
  This feature verifies that users can create, validate, duplicate, and manage projects with various combinations of required fields such as name, identifier, task limit, and form controls.

  Background:The user logs in to the system with valid credentials

  Scenario: Required fields are displayed and clickable on the New Project page
    Given Navigate To New Project Page
    Then The new project page opens successfully
    And Checked Required fields are displayed
    And Checked Required fields are clickable

  Scenario:Create a new project without a name on the New Project page
    When Click Save button without entering name
    Then Warning Message should be seen for project name

  Scenario: Create a new project with only a name on the New Project page
    When Enter project name PROJECTONE and save
    Then PROJECTONE project is created successfully

  Scenario: Create a project with special characters on the New Project page
    When Enter name with special character and save
    Then Project name with special characters created successfully

  Scenario: The user tries to enter a project identifier with special characters for a new project on the New Project page
    When  Enter the project name PLP and the identifier P&P
    Then The warning message appears for identifier

  Scenario: The User creates a new project with name and identifier on the New Project page
    When Enter the project name PROJECTONE and the identifier SEVEN
    Then The project PROJECTONE should be created

  Scenario: The user enters a different project name with a duplicate identifier on the New Project page
    When Enter the project name PROJECTTWO and the identifier TWO
    Then The identifier warning message appears as The identifier must be unique

  Scenario:Create a project with a name, identifier, and task limit on the New Project page
    When Enter the project name PROJECTONE, identifier SEVEN, and task limit 10
    Then The project PROJECTONE with task limit 10 should be created successfully

  Scenario: Try to save a project with an identifier and task limit but without a name on the New Project page
    When Enter identifier SEVEN and task limit 10 without a project name
    Then Warning message NAME IS REQUIRED should be displayed

  Scenario: Create a project with a name, identifier, task limit and check the checkbox on the New Project page
    When Enter name PROJECTONE, identifier SEVEN, task limit 10 and check the task limit checkbox
    Then The project PROJECTONE should be created with task limit 10 and checkbox confirmation message

  Scenario: Try to create a project without a name but with an identifier, task limit, and checkbox on the New Project page
    When Enter identifier SEVEN and task limit 10 and check the task limit checkbox without entering a name
    Then Warning that the project name is required

  Scenario: Duplicate an existing project and verify task limit is set correctly on the New Project page
    When Duplicate the project with name PROJECTONE, identifier SEVEN and no task limit
    Then Duplicated project's task limit is 5

  Scenario: Duplicate a project without providing a name on the New Project page
    When Duplicate the project without a name using identifier SEVEN and task limit 10
    Then Warning message is displayed

  Scenario: Enter a project name and cancel project creation on the New Project page
    Given There are 4 projects
    When Enter project name PROJECTONE and cancel project creation
    Then PROJECTONE name is not visible and project number is 4

  Scenario: Enter a project name and close project creation on the New Project page
    Given Check project count
    When Enter project name PROJECTONE and close page
    Then New project name is not visible and project number is 4

  Scenario: Create a new project with the same name but a different identifier on the New Project page
    Given Create a project with name PROJECTONE
    When Create another project with same name but different identifier
    Then The second project PROJECTONE should be created successfully and deleted
    And The first project PROJECTONE is deleted






