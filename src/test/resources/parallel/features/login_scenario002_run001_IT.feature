Feature: Login/Log out functionality

Scenario: Login with invalid credentials

Given User opens Home page
When User signs in with login olegmarket@gmail.com and password '-'
Then Error message Password is required. appears

# Generated by Cucable

