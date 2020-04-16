import java.util.Scanner;
import java.util.ArrayList;
public class Main{
	public static void main( String[] args ) {
		System.out.println("Please enter a customer ledger or e to exit");
		Scanner in = new Scanner( System.in );
		String filename = in.nextLine();
		while (true){
			//stored transactions from ledger file
			ArrayList<String> transactionList = new ArrayList<String>();
			//stores transactions from ledger and updated transactions
			ArrayList<String> autoTransactionList = new ArrayList<String>();
			Customer customer = new Customer();
			Transaction transaction = new Transaction();

			//filename should be in the format of customer-"n amount of numbers"-ledger.csv
			while ( !filename.matches("^customer-[0-9]*-ledger.csv$") && !filename.equals("e") ){
				System.out.println( "Please enter a valid filename or e to exit" );
				filename = in.nextLine();
			}

			if (filename.equals("e")){
				System.exit(0);
			}

			//read ledger store entries in arrayList
			ReadWriteFile file = new ReadWriteFile();
			transactionList = file.readFile(filename);

			//iterate through transactions and add overdraft transfers
			for (int i = 0; i < transactionList.size(); i++){
				if (i == 0){
					autoTransactionList.add(transactionList.get(i));
				}
				if (i > 0){
					transaction.ledgerTransaction(autoTransactionList, customer, transactionList.get(i));
					transaction.autoTransaction(autoTransactionList, customer, transactionList.get(i));
				}
			}

			//overwrite original ledger
			file.writeFile(autoTransactionList, filename);

			System.out.println("Please enter a customer ledger or e to exit");
			filename = in.nextLine();
		}
	}
}
