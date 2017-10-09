Feature: Search functionality

  Scenario Outline: Search results contain keyword
    Given User is logged in and opens HomePage
    When User submit search query <query>
    Then All search results contain keyword
    Examples:
      | query   |
      | HP      |
#      | Lenovo  |
#    |    Canon     |
