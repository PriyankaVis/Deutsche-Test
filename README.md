# TradeStore

##### Steps to run the code <H6>
  - Downlaod the repo.
  - Import the Java maven Project
  - Right click on project - > run -> Run as java application
  
##### Steps to execute the code <H6>
  - Execute TradeTestRunner.java with Junit Test.


###### PROBLEM STATEMENT <h6> 
There is a scenario where thousands of trades are flowing into one store, assume any way of transmission of trades. We need to create a one trade store, which stores the trade in the following order

Trade Id    Version Counter-Party Id    Book-Id Maturity Date   Created Date    Expired
T1  1   CP-1    B1  20/05/2020  <today date>    N
T2  2   CP-2    B1  20/05/2021  <today date>    N
T2  1   CP-1    B1  20/05/2021  14/03/2015  N
T3  3   CP-3    B2  20/05/2014  <today date>    Y


###### There are some of validation, we need to provide in the above assignment <h6> 
- During transmission if the lower version is being received by the store it will reject the trade and throw an exception. If the version is same it will override the existing record.
- Store should not allow the trade which has less maturity date then today date.
- Store should automatically update the expire flag if in a store the trade crosses the maturity date.

  
##### Built information <h6>
- Java Maven Project 
- Java Version Used:-JDK 1.8,JRE 1.8
- Junt Version Used- JUnit 5

 ##### Built information <h6>
1. Check if Trade is added.
2. Check for Version High, low and same 
5. Check if maturity Date is greater than todays date the trade is added.
6. Check if maturity Date is lower than todays date the Trade will not be added.
7. Check if Version is Same and date is lower the trade is not updated.
8. Check if Maturity Date is Same as Todays Date the list will be added.
9. Check if version is high but maturity date is low the trade will be rejected.
10. Check If Maturity Date is Expired it will update the Expired Flag
  
###### Test Cases Output <H6>
- All test Case Passed.

