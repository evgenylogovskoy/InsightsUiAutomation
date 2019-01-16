@Critical @Login
Feature: Basic coverage of login feature

  @1 @Login
  Scenario Outline: User able to login using valid credentials
    Given I'm on login page
    And I'm trying to login with "<login>" and "<password>"
    And I'm on home page and i'm logged in as "<login>"
    And I'm on the "Sessions Count" report page
    And Report default time preset is one year
    And Report selector appear as "Entire Organization"
    When I click on "Export" and then choose "CSV"
    Then File is downloaded and file name is "CU Insights - User Sessions Count - 1Y" and it is not empty
    And  File "CU Insights - User Sessions Count - 1Y" deleted successfully

    Examples:
      |login  |password|
      |NewInsightsTest@gmail.com  |Test123456!|

