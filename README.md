# Rate calculation system 
## Goal 
There is a need for a rate calculation system allowing prospective borrowers to
obtain a quote from our pool of lenders for 36 month loans. This system will
take the form of a command-line application.
You will be provided with a file containing a list of all the offers being made
by the lenders within the system in CSV format, see the example market.csv file
provided alongside this specification.
You should strive to provide as low a rate to the borrower as is possible to
ensure that Zopa's quotes are as competitive as they can be against our
competitors'. You should also provide the borrower with the details of the
monthly repayment amount and the total repayment amount.
Repayment amounts should be displayed to 2 decimal places and the rate of the
loan should be displayed to one decimal place.
Borrowers should be able to request a loan of any £100 increment between
£1000 and £15000 inclusive. If the market does not have sufficient offers from
lenders to satisfy the loan then the system should inform the borrower that it
is not possible to provide a quote at that time.

## Install & Run

 - Clone repository
 ```bash
 git clone git@github.com:malltshik/ratecalculator.git
 ```
 
 - Install
 ``` bash    
 cd ratecalculator && ./mvnw clean install
 ```
 
 - Run
 ``` bash    
 ./calculator [market_file] [loan_amount]     
 ```
 
 - Configuration
 Script possible to run with custom loan min, max and length properties
```text
 ./calculator [market_file] [loan_amount]  --loan.min=100 --loan.max=1000 --loan.length=24
```
 
 ## Expected result should be like
 ```text
 Requested amount: £XXXX 
 Rate: X.X%     
 Monthly repayment: £XXXX.XX     
 Total repayment: £XXXX.XX  
```
 
 ### Market file CSV format
 ```text
 Lender,Rate,Available
 Bob,0.575,100
 Jane,0.369,480
 Fred,0.671,520
```
