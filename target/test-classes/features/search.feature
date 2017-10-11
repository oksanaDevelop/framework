Feature: Search functionality

  Scenario Outline: Search results contain keyword
    Given User opens Home page
    When User submit search query <query>
    Then All search results contain keyword
    Examples:
      | query  |
      | HP     |
      | Lenovo |
      | Canon  |
