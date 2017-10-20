Feature: API functionality

  Scenario: Test API functionality
    When User sends GET request to endpoint http://services.groupkt.com/country
    Then User receive response with the following data
      | responseCode | name                     | alpha2_code | alpha3_code |messages|
      | 200          | United States of America | US          | USA         |       Country found matching code [USA].|