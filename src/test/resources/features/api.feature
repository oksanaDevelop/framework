Feature: API functionality
  As a user
  I want to send API request
  In order to create pre-conditions for tests

  Scenario: GET testing
    When User sends GET request to endpoint http://services.groupkt.com/country/get/iso3code/USA
    Then User receives response with the following data
      | responseCode | name                     | alpha2_code | alpha3_code | messages                           |
      | 200          | United States of America | US          | USA         | Country found matching code [USA]. |

  Scenario: POST testing
    When User sends POST request to endpoint https://httpbin.org/post with the body
      | key    | value |
      | mybody | body  |
    Then Response contains response code 200 and the following data
      | key    | value |
      | mybody | body  |
