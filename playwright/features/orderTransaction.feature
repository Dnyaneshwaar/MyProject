Feature: Order Transaction
  Test related to order transaction

  Scenario Outline: verify order success message shown in details page
    Given place the item order with <username> and <password>
    And user is on landing page
    When I login to portal with <username> and <password>
    And Navigate to order page
    And select the order id
    Then verify the order success message
    Examples:
      |username           |password |
      |dsc03692@gmail.com |03692Dsc@|