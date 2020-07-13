@All
Feature: Sell a Share
@Share
Scenario Outline: To Sell a Share
Given Login is Valid User Name and Password
And AccountStatus is Active
And PortfolioCreated is Yes
And SufficientFunds is Yes
And OrderPlaced is Yes
And Navigation is Navigate > Private Operation > Securities > Front office > amendment screen
When DealReferene is Yes
And TradeActivity is Amend
And TradeDate is Current
And Session is Pre Session
And Order is Sell
And Currency is LCY
And TypeofOrder is Market
And LimitType is Good till day
And Commitandvalidate is Yes
Then PostionTransfer should be Yes
And Navigate should be T24 Home Screen >  Retail operation > accounts > accounting entries > account number
And CommitANDvalidate should be Clicked
And Accountingentries should be Checked

Examples:
|TestConditionID|
|TC_06|