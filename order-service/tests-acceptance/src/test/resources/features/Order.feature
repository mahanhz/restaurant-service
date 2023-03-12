Feature: Order

  Scenario Outline: Create an Order
    When creating an order for item <name> and date <orderDate>
    Then the order should be created
    And the order should contain the item <name> and date <orderDate>
    And the order should have an identity

    Examples:
      | name     | orderDate    |
      | "Burger" | "2022-01-01" |
      | "Salad"  | "2022-07-25" |