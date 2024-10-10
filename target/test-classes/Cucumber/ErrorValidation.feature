@tag
Feature: Error Validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Error Scenario testing
    Given I landed on the Ecommerce page
    When Login with username <name> and passwowrd <password>
    Then "Incorrect email or password." message is displayed

  Examples: 
      | name  													| password 			 | 
      | harshitsrivastava1991@gmail.com | Oct@1810	 		 |
      | soumyasrivastava1992@gmail.com  | Oct@18101	 		 |
