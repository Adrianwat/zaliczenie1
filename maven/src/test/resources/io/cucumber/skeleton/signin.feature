Feature: sign in
  Scenario Outline: Fill new address form
    Given I open the browser
    And I go to the sign in page
    And I type "adrian777@wp.pl" as email
    And I type "Qwerty123@" as password
    And I click on Sign In button
    When I click on Addresses
    And I click on Create new address
    And I enter "<alias>", "<address>", "<city>", "<zip>","<country>", "<phone>" in the form
    And I click on Save
    Then new address is added successfully
    And I check if "<alias>", "<address>", "<city>","<zip>","<country>", "<phone>" are added correctly
    And I close the browser

    Examples:
      | alias  | address      | city     | zip    | country        | phone     |
      | home   | Yellowstreet | Hull     | 32-143 | United Kingdom | 787100393 |
      | office | Firestation  | Cambridge| 67-392 | United Kingdom | 721418555 |