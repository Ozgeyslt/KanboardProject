# Kanboard UI Testing Project
This project involves UI test automation for [Kanboard](https://kanboard.org/), an open-source project management software. The purpose of these tests is to verify the core functionality and elements of key pages in the Kanboard web application, ensuring UI components behave as expected.

The goal is to ensure that the user interface is responsive, accurate, and reliable for typical user workflows in Kanboard. The tests are designed following the Page Object Model (POM) structure and use Cucumber-style annotations for clear and maintainable test steps. The tests cover several important pages of the Kanboard application.

Technologies Used
-
- Java
- Selenium WebDriver
- Selenide
- Cucumber
- Allure Report

These test scenarios:
-
New Project Page
-
- Verify required fields visibility, clickability, and validation for project name and identifier.
- Validate project creation with various input combinations (name, identifier, task limit, special characters).
- Ensure warnings appear for missing name, duplicate identifiers, and invalid inputs.
- Test project duplication, cancellation, and handling of projects with same names but different identifiers.

My Projects Page
-
- Validate navigation to My Projects page and visibility of project list, headers, and action buttons.
- Test core project actions: create, update, delete, and verify project changes are reflected.
- Verify sorting functionality by ID and name; ensure view switching between board, list, activity, analytics, and config pages
- Ensure all UI elements (dropdowns, buttons, project views) are functional and interactive.

Overview Page
-
- Validates project detail editing, including name, description, dates, and email, with proper input validation and warning messages.
- Ensures task limit settings behave correctly (including zero, negative, and arrow key adjustments) and reflect accurately in the UI.
- Confirms task movement between columns updates task counts, and verifies new task creation and deletion across backlog and activity.
- Checks project settings functionality like marking as personal, changing project owner, tooltip visibility, and cancel/close behavior.

![Ekran Alıntısı](https://github.com/user-attachments/assets/54b16ba8-07a3-4637-86cd-cb402662f895)

# Overview Page
 Minor Bug: 
 -
 - While the task count cannot be reduced below zero using the arrows on the Edit Description page of the Overview, a negative number can be manually entered into the Task Limit text field and saved, resulting in the project's task limit appearing as a negative number.
