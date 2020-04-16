import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
public class ReadWriteFile{

	//Readfile line by line, validate lines, add to arrayList, return ArrayList
	public static ArrayList<String> readFile(String filename){
		ArrayList<String> ledgerTransactions = new ArrayList<String>();
		File fileIn = new File( filename );
		int i = 1;
		try {
			Scanner in = new Scanner( fileIn );
			while( in.hasNextLine() ) {
				String line = in.nextLine();
				String[] parts = line.split(",");
				String accountID = parts[0];
				String accountType = parts[1];
				String initiatorType = parts[2];
				String dateTime = parts[3];
				String transactionValue = parts[4];

				//validate first line of CSV
				if (i == 1){
					if (!accountID.matches("^AccountID$")){
						System.out.println("CSV AccountID format invalid");
						System.out.println("Error at line: " + i);
						System.exit(-1);
					}
					if (!accountType.matches("^AccountType$")){
						System.out.println("CSV AccountType format invalid");
						System.out.println("Error at line: " + i);
						System.exit(-1);
					}
					if (!initiatorType.matches("^InitiatorType$")){
						System.out.println("CSV InitiatorType format invalid");
						System.out.println("Error at line: " + i);
						System.exit(-1);
					}

					if (!dateTime.matches("^DateTime$")){
						System.out.println("CSV DateTime format invalid");
						System.out.println("Error at line: " + i);
						System.exit(-1);
					}

					if (!transactionValue.matches("^TransactionValue$")){
						System.out.println("CSV TransactionValue format invalid");
						System.out.println("Error at line: " + i);
						System.exit(-1);
					}
				}

				//validate transaction lines of CSV
				if (i > 1){
					if (!accountID.matches("^[0-9]*$")){
						System.out.println("CSV AccountID format invalid");
						System.out.println("Error at line: " + i);
						System.exit(-1);
					}
					if (!accountType.matches("^CURRENT$") && !accountType.matches("^SAVINGS$")){
						System.out.println("CSV AccountType format invalid");
						System.out.println("Error at line: " + i);
						System.exit(-1);
					}
					if (!initiatorType.matches("^ACCOUNT-HOLDER$") && !initiatorType.matches("^SYSTEM$")){
						System.out.println("CSV InitiatorType format invalid");
						System.out.println("Error at line: " + i);
						System.exit(-1);
					}
					if (!transactionValue.matches("^-?+[0-9]*+.[0-9]{2}$")){
						System.out.println("CSV TransactionValue format invalid");
						System.out.println("Error at line: " + i);
						System.exit(-1);
					}
				}
				i++;
				ledgerTransactions.add(line);
			}
			in.close();
		} catch( Exception e ) {
			System.out.println( e );
		}
		return ledgerTransactions;
	}

	//Overwrite the original ledger
	public static void writeFile(ArrayList<String> transactionList, String filename){
		try{
			String newName = "new-"+filename;
			FileWriter writer = new FileWriter( newName );
			PrintWriter out = new PrintWriter( writer );
			for (int i = 0; i < transactionList.size(); i++){
				out.println(transactionList.get(i));
			}
			out.close( );
			System.out.println( "Data written to: " + newName );   
		} catch ( Exception e ){
			System.out.println( e );
		}
	}
}
