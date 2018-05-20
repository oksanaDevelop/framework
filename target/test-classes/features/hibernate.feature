Feature: Hibernate functionality
  As a user
  I want to interact with database
  In order to create pre-conditions for tests

  Background:
    Given Insert user Tony from company Coca in DB

  Scenario: Delete user from DB
    Given User Tony from company Coca exists in DB
    When Admin executes query to delete user Tony
    Then User Tony is deleted from DB
