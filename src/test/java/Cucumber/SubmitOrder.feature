@tag
Feature: Purchase the order from the Ecommerce Website
  I want to use this template for my feature file

 Background:
 Given I landed on the Ecommerce page


  @Regression
  Scenario Outline: Positive test of submitting the order
    Given Login with username <name> and passwowrd <password>
    When I add the product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page

    Examples: 
      | name  													| password 			 | productName 				 |
      | harshitsrivastava1991@gmail.com | Oct@1810	 		 | ADIDAS ORIGINAL 		 |
      | soumyasrivastava1992@gmail.com  | Oct@1810	 		 | ZARA COAT 3 	    	 |
