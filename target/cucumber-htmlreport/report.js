$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("Scenarios/Login.feature");
formatter.feature({
  "line": 2,
  "name": "Basic coverage of login feature",
  "description": "",
  "id": "basic-coverage-of-login-feature",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@Critical"
    },
    {
      "line": 1,
      "name": "@Login"
    }
  ]
});
formatter.scenarioOutline({
  "line": 5,
  "name": "User able to login using valid credentials",
  "description": "",
  "id": "basic-coverage-of-login-feature;user-able-to-login-using-valid-credentials",
  "type": "scenario_outline",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 4,
      "name": "@1"
    },
    {
      "line": 4,
      "name": "@Login"
    }
  ]
});
formatter.step({
  "line": 6,
  "name": "I\u0027m on login page",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "I\u0027m trying to login with \"\u003clogin\u003e\" and \"\u003cpassword\u003e\"",
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "I\u0027m on home page and i\u0027m logged in as \"\u003clogin\u003e\"",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "I\u0027m on the \"Sessions Count\" report page",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "Report default time preset is one year",
  "keyword": "And "
});
formatter.step({
  "line": 11,
  "name": "Report selector appear as \"Entire Organization\"",
  "keyword": "And "
});
formatter.step({
  "line": 12,
  "name": "I click on \"Export\" and then choose \"CSV\"",
  "keyword": "When "
});
formatter.step({
  "line": 13,
  "name": "File is downloaded and file name is \"CU Insights - User Sessions Count - 1Y\" and it is not empty",
  "keyword": "Then "
});
formatter.step({
  "line": 14,
  "name": "File \"CU Insights - User Sessions Count - 1Y\" deleted successfully",
  "keyword": "And "
});
formatter.examples({
  "line": 16,
  "name": "",
  "description": "",
  "id": "basic-coverage-of-login-feature;user-able-to-login-using-valid-credentials;",
  "rows": [
    {
      "cells": [
        "login",
        "password"
      ],
      "line": 17,
      "id": "basic-coverage-of-login-feature;user-able-to-login-using-valid-credentials;;1"
    },
    {
      "cells": [
        "NewInsightsTest@gmail.com",
        "Test123456!"
      ],
      "line": 18,
      "id": "basic-coverage-of-login-feature;user-able-to-login-using-valid-credentials;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 4528462,
  "status": "passed"
});
formatter.scenario({
  "line": 18,
  "name": "User able to login using valid credentials",
  "description": "",
  "id": "basic-coverage-of-login-feature;user-able-to-login-using-valid-credentials;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 1,
      "name": "@Login"
    },
    {
      "line": 4,
      "name": "@1"
    },
    {
      "line": 1,
      "name": "@Critical"
    }
  ]
});
formatter.step({
  "line": 6,
  "name": "I\u0027m on login page",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "I\u0027m trying to login with \"NewInsightsTest@gmail.com\" and \"Test123456!\"",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "I\u0027m on home page and i\u0027m logged in as \"NewInsightsTest@gmail.com\"",
  "matchedColumns": [
    0
  ],
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "I\u0027m on the \"Sessions Count\" report page",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "Report default time preset is one year",
  "keyword": "And "
});
formatter.step({
  "line": 11,
  "name": "Report selector appear as \"Entire Organization\"",
  "keyword": "And "
});
formatter.step({
  "line": 12,
  "name": "I click on \"Export\" and then choose \"CSV\"",
  "keyword": "When "
});
formatter.step({
  "line": 13,
  "name": "File is downloaded and file name is \"CU Insights - User Sessions Count - 1Y\" and it is not empty",
  "keyword": "Then "
});
formatter.step({
  "line": 14,
  "name": "File \"CU Insights - User Sessions Count - 1Y\" deleted successfully",
  "keyword": "And "
});
formatter.match({
  "location": "LoginPageStepDef.iMOnLoginPage()"
});
formatter.result({
  "duration": 713869226,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "NewInsightsTest@gmail.com",
      "offset": 26
    },
    {
      "val": "Test123456!",
      "offset": 58
    }
  ],
  "location": "LoginPageStepDef.iMTryingToLoginWithAnd(String,String)"
});
formatter.result({
  "duration": 453260991,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "NewInsightsTest@gmail.com",
      "offset": 39
    }
  ],
  "location": "HomePageStepDef.iMOnHomePageAndIMLoggedInAs(String)"
});
formatter.result({
  "duration": 2991270505,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Sessions Count",
      "offset": 12
    }
  ],
  "location": "HomePageStepDef.iMOnTheReportPage(String)"
});
formatter.result({
  "duration": 207080694,
  "status": "passed"
});
formatter.match({
  "location": "HomePageStepDef.reportDefaultTimePresetIsOneYear()"
});
formatter.result({
  "duration": 2486751878,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Entire Organization",
      "offset": 27
    }
  ],
  "location": "HomePageStepDef.reportSelectorAppearAs(String)"
});
formatter.result({
  "duration": 62530598,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Export",
      "offset": 12
    },
    {
      "val": "CSV",
      "offset": 37
    }
  ],
  "location": "HomePageStepDef.iClickOnAndThenChoose(String,String)"
});
formatter.result({
  "duration": 2335763932,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "CU Insights - User Sessions Count - 1Y",
      "offset": 37
    }
  ],
  "location": "HomePageStepDef.fileIsDownloadedAndFileNameIsAndItIsNotEmpty(String)"
});
formatter.result({
  "duration": 5419359,
  "error_message": "java.lang.AssertionError: File is empty.\n\tat org.junit.Assert.fail(Assert.java:88)\n\tat pages.HomePage.checkThatFileContainsSomeData(HomePage.java:138)\n\tat stepdefinition.HomePageStepDef.fileIsDownloadedAndFileNameIsAndItIsNotEmpty(HomePageStepDef.java:43)\n\tat ✽.Then File is downloaded and file name is \"CU Insights - User Sessions Count - 1Y\" and it is not empty(Scenarios/Login.feature:13)\n",
  "status": "failed"
});
formatter.match({
  "arguments": [
    {
      "val": "CU Insights - User Sessions Count - 1Y",
      "offset": 6
    }
  ],
  "location": "HomePageStepDef.fileDeletedSuccessfully(String)"
});
formatter.result({
  "status": "skipped"
});
formatter.embedding("image/png", "embedded0.png");
formatter.after({
  "duration": 668876592,
  "status": "passed"
});
});