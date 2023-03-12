Feature: The application should be healthy

  Scenario: Call the health endpoint
    When calling the health endpoint
    Then the response should be healthy