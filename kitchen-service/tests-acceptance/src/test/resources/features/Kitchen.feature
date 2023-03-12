Feature: Kitchen

  Scenario: Assign an order to an available chef
    Given chef "Rata" is available
    When an order for a "burger" is created
    Then the order for a "burger" is assigned to chef "Rata"
