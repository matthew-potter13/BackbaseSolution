import java.util.ArrayList;
import java.text.DecimalFormat;
public class Transaction{

	//Transactions Executed when overdraft is accessed
	public static void autoTransaction(ArrayList<String> transactionList, Customer customer, String transaction){
		//make sure float has trailing 0
		DecimalFormat df = new DecimalFormat("0.00");
		float currentBalance = customer.getCurrentBalance();
		float savingsBalance = customer.getSavingsBalance();
		String savingsID = customer.getSavingsID();
		String currentID = customer.getCurrentID();

		//get DateTime value from latest transaction
		String[] parts = transaction.split(",");
		String dateTime = parts[3];
		
		//current account needs to be in overdraft, savings need to be greater than 0;
		if (currentBalance < 0 && savingsBalance > 0){
			if((currentBalance += savingsBalance) >= 0){
				//overdraft is less than savings, so overdraft is value transferred
				String line = (savingsID+",SAVINGS,SYSTEM,"+dateTime+","+df.format(customer.getCurrentBalance()));
				transactionList.add(line);
				//Value multiplied by -1 to make value positive, so transaction shows as deposit
				line = (currentID+",CURRENT,SYSTEM,"+dateTime+","+df.format(customer.getCurrentBalance()*-1));
				transactionList.add(line);
				
				//Reduce savings balance by overdraft, set current balance to 0
				savingsBalance = customer.getSavingsBalance();
				savingsBalance += customer.getCurrentBalance();
				customer.setSavingsBalance(savingsBalance);
				customer.setCurrentBalance(0);
			}else {
				//Savings is less than overdraft so savings is value transferred
				//Value of savings is positive, multiply by -1 to make negative
				String line = (savingsID+",SAVINGS,SYSTEM,"+dateTime+","+df.format(customer.getSavingsBalance()*-1));
				transactionList.add(line);
				line = (currentID+",CURRENT,SYSTEM,"+dateTime+","+df.format(customer.getSavingsBalance()));
				transactionList.add(line);
				
				//Reduce overdraft by savings balance, set savings to 0
				currentBalance = customer.getCurrentBalance();
				currentBalance += customer.getSavingsBalance();
				customer.setCurrentBalance(currentBalance);
				customer.setSavingsBalance(0);
			}
		}
	}
		
	//Transactions from CSV ledger
	public static void ledgerTransaction(ArrayList<String> transactionList, Customer customer, String transaction){
		String[] parts = transaction.split(",");
		String accountID = parts[0];
		String  accountType = parts[1];
		String TransactionValue = parts[4];
		float transactionValue = Float.parseFloat(TransactionValue);
		
		if(accountType.contains("CURRENT")){
			//set ID of current account for use in autoTransaction
			customer.setCurrentID(accountID);
			//add transaction value to current balance then update
			float currentBalance = customer.getCurrentBalance();
			currentBalance += transactionValue;
			customer.setCurrentBalance(currentBalance);
			transactionList.add(transaction);
		} 
		else if(accountType.contains("SAVINGS")){
			float savingsBalance = customer.getSavingsBalance();
			if ((savingsBalance += transactionValue) > 0){
				//set ID of savings account for use in autoTransaction
				customer.setSavingsID(accountID);
				savingsBalance = customer.getSavingsBalance();
				savingsBalance += transactionValue;
				customer.setSavingsBalance(savingsBalance);
				transactionList.add(transaction);
			}else{
				//Transaction is ignored if it would make savings balance negative
				System.out.println("Attempt to withdraw more than allowed, check CSV");
				System.out.println("System exiting without writing file");
				System.exit(-1);
			}
		}
	}
}
