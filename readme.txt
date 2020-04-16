Matthew Potter Backbase challenge

-System is only currently only used for amending ledgers, Reads a ledger -> Does new transactions -> Writes new ledger to new-customer-number-ledger.csv
-The CSV file must be validated by regular expressions, if not program rejects the whole ledger
-DateTime stamp does not need to be validated or sorted
-Customer only has one of each account
-If a CSV tries to put the savings account into an overdraft, The program rejects the whole ledger
-The overdraft transfer happens instantly if the overdraft is accessed and the customer has savings so time from the last transaction is used for the two new system transactions

Install Guide:
java -version
openjdk version "1.8.0_232"
OpenJDK Runtime Environment (Zulu 8.42.0.23-CA-linux64) (build 1.8.0_232-b18)
OpenJDK 64-Bit Server VM (Zulu 8.42.0.23-CA-linux64) (build 25.232-b18, mixed mode)

Javac Main.java
java Main
Enter filename in format of customer-number-ledger.csv


