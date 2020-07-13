@All
Feature: Buy a Bond
@Bond
Scenario Outline: To Buy a Bond
Given Login is Valid User Name and Password
And AccountStatus is Active
And PortfolioCreated is Yes
And SufficientFunds is Yes
And OrderPlaced is Yes
And Navigation is Navigate to > Private Operation > Securities > Front office > delete screen
When DealReferene is Yes
And TradeActivity is Delete
And TradeDate is Current
And Session is Continuous
And Order is Buy
And Currency is LCY
And TypeofOrder is Market
And LimitType is Good till day
And Commitandvalidate is Yes

Examples:
|TestConditionID|
|TC_03|