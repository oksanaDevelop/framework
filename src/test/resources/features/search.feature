Feature: Search functionality
  As a user
  I want to submit search queries
  In order to search fast necessary products

  Scenario Outline: Search results contain keyword
    Given User opens Home page
    When User submit search query <query>
    Then All search results contain keyword
    Examples:
      | query       |
      | HP          |
      | Great Value |
      | Canon       |
