

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Calendar;

public class Bank {

	public static void main(String[] args) throws IOException{
		
		
		char choice;
		boolean notDone = true;
		ArrayList <SavingsAccount> savingsAccount
		= new ArrayList<SavingsAccount>();
		
		File testFile = new File("inPut.txt");
		Scanner kybd = new Scanner(testFile);
		//Scanner kybd = new Scanner(System.in);
		PrintWriter outFile = new PrintWriter("myOutput.txt");
		
		
		readAccts(savingsAccount);
		printAccts(savingsAccount,outFile);
		
		do {
			menu();
			choice = kybd.next().charAt(0);
			choice = Character.toUpperCase(choice);
			switch (choice) {
			case 'Q':
				notDone = false;
				printAccts(savingsAccount, outFile);
				break;
			case 'B':
				getBalance(savingsAccount, outFile, kybd);
				break;
			case 'D':
				makeDeposit(savingsAccount, outFile, kybd);
				break;
			case 'W':
				makeWithdrawal(savingsAccount, outFile, kybd);
				break;
			case 'I':
				addInterest(savingsAccount, outFile, kybd);
				break;
			default:
				System.out.println("Error: " + choice 
						+ " is an invalid selection-  try again");
				System.out.println();
				break;
			}

		} while (notDone);

		System.out.println();
		System.out.println("The program is terminating");

		outFile.println();
		outFile.flush(); 
		outFile.close(); 
		kybd.close(); 

}
	/*
	 * Method menu
	 * Input:
	 *  N/A
	 * Process:
	 * 	Creates and prints a menu for the user to select from
	 * Output:
	 * 	Prints out a menu to the console for the user to select from
	 */
	private static void menu() {
		System.out.println();
		System.out.println("Select one of the following transactions:");
		System.out.println("\t****************************");
		System.out.println("\t    List of Choices         ");
		System.out.println("\t****************************");
		System.out.println("\t     Q -- quit the program");
		System.out.println("\t     B -- get account balance");
		System.out.println("\t     D -- make deposit");
		System.out.println("\t     W -- make withdrawal");
		System.out.println("\t     I -- add Interest");
		System.out.println();
		System.out.print("\tEnter your selection: ");
	
		
	}
	/*
	 * Method readAccts
	 * Input:
	 *  ArrayList savingsAccount: holds the a reference to the 
	 *  arraylist of savings account objects
	 * Process:
	 * 	Reads from the myAccts.txt file into the savingsAccount arraylist
	 * Output:
	 * 	N/A	
	 */
	public static void readAccts(ArrayList<SavingsAccount> savingsAccount)
			throws FileNotFoundException {
		File custFile = new File("myAccts.txt");
		Scanner sc = new Scanner(custFile);
		

		while (sc.hasNext()) {
			
			int accountNumber = sc.nextInt();
			double accountBalance = sc.nextDouble();
			System.out.println(accountNumber + accountBalance);
			SavingsAccount savings = 
					new SavingsAccount(accountNumber,accountBalance);
			SavingsAccount savings1 = new SavingsAccount(savings);
			savingsAccount.add(savings1);
			
			
		}

		sc.close();
	}
	/*
	 * Method printAccts
	 * Input:
	 *  ArrayList savingsAccount: holds a reference to the 
	 *  ArrayList of savingsAccount objects
	 *  PrintWriter outFile: holds reference to output file
	 * Process:
	 * 	Goes through the arrayList of savingsAccount objects and prints
	 * 	out the information into a neatly formatted table to the 
	 * 	output file
	 * Output:
	 * 	The Account information which includes Account number and 
	 * 	Account balances is printed out the output file.
	 */
	public static void printAccts(ArrayList<SavingsAccount>savingsAccount,
			PrintWriter outFile) {
		
		
		String divider ="========================================";
		outFile.println("\t\tAccounts in the Database");
		outFile.println(divider);
		outFile.printf("%-15s%-16s", "| Account Number |",
				"| Account Balance |");
		outFile.println();
		outFile.println(divider);
		
		for(int i = 0; i < savingsAccount.size(); i++){
			
			outFile.printf("%-5s", "    ");
			outFile.printf("%-15s",savingsAccount.get(i).accountNum);
			outFile.printf("$%-15.2f",savingsAccount.get(i).accountBalance);
			outFile.println();
			outFile.println(divider);
			
		}
		outFile.println(divider);
		outFile.flush();
		
	}
	/*
	 * Method findAccount
	 * Input:
	 *  ArrayList savings: holds a reference to the 
	 *  ArrayList of savingsAccount objects
	 *  PrintWriter outFile: holds reference to output file
	 *  int requestedAccount: holds the account number that is going 
	 *  to be searched for
	 * Process:
	 * 	Searches through the savings arraylist for the requested account
	 * 	number.
	 * 	if the account is found then the index of that account is returned
	 * 	if that account is not found then a custom made, 
	 * 	AccountNotFound exception
	 * Output:
	 * 	N/A
	 */
	public static int findAccount(ArrayList<SavingsAccount> savings,
			PrintWriter outFile, int requestedAccount) 
					throws AccountNotFound {
		
		for (int index = 0; index < savings.size(); index++) 
			if (savings.get(index).accountNum == requestedAccount) { 
				return index;
			}
		throw new AccountNotFound(requestedAccount);
	}
	/*
	 * Method getBalance
	 * Input:
	 *  ArrayList savings: holds a reference to the 
	 *  ArrayList of savingsAccount objects
	 *  PrintWriter outFile: holds reference to output file
	 *  Scanner kybd: holds a reference to the input file
	 * Process:
	 * 	Prompts the user for an account number
	 * 	The account Number is then send to the findAccount method
	 * 	which searches for the account number in the arraylist 
	 * 	if the account number is found in the arraylist and an index is 
	 * 	returned then the method continues
	 * 	Otherwise, it throws the custom made, AccountNotFound exception
	 * 	which was thrown in the findAccount Method
	 * Output:
	 * 	Prints a receipt of the transaction
	 * 	If an Account is found, it print a receipt with the 
	 * 	account information	including the account balance
	 * 	If the account is not found then it prints a receipt with an 
	 * 	error message of why the transaction could not be completed
	 */
	public static void getBalance(ArrayList<SavingsAccount> savings, 
			PrintWriter outFile, Scanner kybd)  {
		
		String divider = "--------------------------------";
		int requestedAccount, index;
		double balance;
		Calendar calendar = Calendar.getInstance();
		System.out.println("Please enter an account number: ");
		try {
			requestedAccount = kybd.nextInt();
			index=findAccount(savings, outFile, requestedAccount);
			balance = savings.get(index).accountBalance;
			outFile.println();
			outFile.println("***RECEIPT***");
			outFile.println(divider);
			outFile.println("Transaction: Get Balance");
			outFile.println("Date of transaction: " + calendar.getTime());
			outFile.println("The Account Number is: " + requestedAccount);
			outFile.println("The Balance is: $" + balance);
			outFile.println(divider);
		}catch(AccountNotFound e) {
			outFile.println();
			outFile.println("***RECEIPT***");
			outFile.println(divider);
			outFile.println("Transaction: Get Balance");
			outFile.println("Date of transaction:: " + calendar.getTime());
			outFile.println(e.getMessage());
			outFile.println(divider);
		}finally{
			outFile.println();
			outFile.flush();
			
		}
		
	}
	/*
	 * Method makeDeposit
	 * Input:
	 *  ArrayList savings: holds a reference to the 
	 *  ArrayList of savingsAccount objects
	 *  PrintWriter outFile: holds reference to output file
	 *  Scanner kybd: holds a reference to the input file
	 * Process:
	 * 	Prompts the user for an account number
	 * 	The account Number is then send to the findAccount method
	 * 	which searches for the account number in the arraylist 
	 * 	if the account number is not found in the arraylist 
	 * `it catches the custom made, AccountNotFound exception
	 * 	which was thrown in the findAccount Method
	 * 	If the Account is found then it prompts the user for an amount
	 * 	to deposit. The amount it sent to the makeDeposit method in the 
	 * 	savings Account class which completes the transaction only if
	 * 	the amount to deposit is over 0, if it is not then it catches
	 * 	a custom made, NegativeAmountEntered exception.
	 * Output:
	 * 	Prints a receipt of the transaction
	 * 	If an Account is found, it print a receipt with the 
	 * 	account information	
	 * 	If the account is not found then it prints a receipt with 
	 * 	the exceptions error messages of why the
	 *  transaction could not be completed
	 */
	public static void makeDeposit(ArrayList<SavingsAccount> savings,
			PrintWriter outFile, Scanner kybd) {
		
		String divider = "--------------------------------";
		int requestedAccount, index;
		double oldbalance, amountToDeposit;
		Calendar calendar = Calendar.getInstance();
		System.out.println("Please enter an account number: ");
		try {
			requestedAccount = kybd.nextInt();
			index=findAccount(savings, outFile, requestedAccount);
			oldbalance = savings.get(index).accountBalance;
			System.out.println("Please enter an amount to deposit: ");
			amountToDeposit = kybd.nextDouble();
			savings.get(index).makeDeposit(amountToDeposit);
			outFile.println();
			outFile.println("***RECEIPT***");
			outFile.println(divider);
			outFile.println("Transaction: Make Deposit");
			outFile.println("Date of transaction:: " + calendar.getTime());
			outFile.println("The account number is: " + requestedAccount);
			outFile.println("The old balance was: $" + oldbalance);
			outFile.println("The amount to deposit was: $" 
			+ amountToDeposit);
			outFile.print("The new balance is: "); 
			outFile.printf("$%3.2f",  savings.get(index).accountBalance); 
			outFile.println("\n" + divider);
		}catch(AccountNotFound e) {
			outFile.println();
			outFile.println("***RECEIPT***");
			outFile.println(divider);
			outFile.println("Transaction: Make Deposit");
			outFile.println("Date of transaction: " + calendar.getTime());
			outFile.println(e.getMessage());
			outFile.println(divider);
		}catch(NegativeAmountEntered e) {
			outFile.println();
			outFile.println("***RECEIPT***");
			outFile.println(divider);
			outFile.println("Transaction: Make Deposit");
			outFile.println("Date of transaction: " + calendar.getTime());
			outFile.println(e.getMessage());
			outFile.println(divider);
		}finally{
			outFile.println();
			outFile.flush();
			
		}
		
	}
	/*
	 * Method makeWithdrawal
	 * Input:
	 *  ArrayList savings: holds a reference to the 
	 *  ArrayList of savingsAccount objects
	 *  PrintWriter outFile: holds reference to output file
	 *  Scanner kybd: holds a reference to the input file
	 * Process:
	 * 	Prompts the user for an account number
	 * 	The account Number is then send to the findAccount method
	 * 	if the account is not found
	 * 	it throws the custom made, AccountNotFound exception
	 * 	which was thrown in the findAccount Method
	 * 	If it is found it then prompts the user for the 
	 * 	amount to withdrawal which is sent to the makeWithdrawal method
	 * 	in the savings account class
	 * 	If that method throws an exception then this method catches it
	 * 	and prints the appropriate messages
	 * Output:
	 * 	Prints a receipt of the transaction
	 * 	If an Account is found, it print a receipt with the 
	 * 	account information	If the account is not found then 
	 *  it prints a receipt with an 
	 * 	error message of why the transaction could not be completed
	 */
	public static void makeWithdrawal(ArrayList<SavingsAccount> savings,
			PrintWriter outFile, Scanner kybd) {
		
		String divider = "--------------------------------";
		int requestedAccount, index;
		double oldbalance, amountToWithdrawal;
		Calendar calendar = Calendar.getInstance();
		System.out.println("Please enter an account number: ");
		try {
			requestedAccount = kybd.nextInt();
			index=findAccount(savings, outFile, requestedAccount);
			oldbalance = savings.get(index).accountBalance;
			System.out.println("Please enter an amount to withdraw: ");
			amountToWithdrawal = kybd.nextDouble();
			savings.get(index).makeWithdrawal(amountToWithdrawal);
			outFile.println();
			outFile.println("***RECEIPT***");
			outFile.println(divider);
			outFile.println("Transaction: Make Withdrawal");
			outFile.println("Date of transaction: " + calendar.getTime());
			outFile.println("The account number is: " + requestedAccount);
			outFile.println("The old balance was: $" + oldbalance);
			outFile.println("The amount to withdraw was: $" 
			+ amountToWithdrawal);
			outFile.println("The new balance is : $" 
			+ savings.get(index).accountBalance);
			outFile.println(divider);
		}catch(AccountNotFound e) {
			outFile.println();
			outFile.println("***RECEIPT***");
			outFile.println(divider);
			outFile.println("Transaction: Make Withdrawal");
			outFile.println("Date of transaction: " + calendar.getTime());
			outFile.println(e.getMessage());
			outFile.println(divider);
		}catch(NegativeAmountEntered e) {
			outFile.println();
			outFile.println("***RECEIPT***");
			outFile.println(divider);
			outFile.println("Transaction: Make Withdrawal");
			outFile.println("Date of transaction: " + calendar.getTime());
			outFile.println(e.getMessage());
			outFile.println(divider);
		}catch(InsufficientFunds e) {
			outFile.println();
			outFile.println("***RECEIPT***");
			outFile.println(divider);
			outFile.println("Transaction: Make Withdrawal");
			outFile.println("Date of transaction: " + calendar.getTime());
			outFile.println(e.getMessage());
			outFile.println(divider);
		}finally{
			outFile.println();
			outFile.flush();
			
		}
		
	}
	/*
	 * Method addInterest
	 * Input:
	 *  ArrayList savings: holds a reference to the 
	 *  ArrayList of savingsAccount objects
	 *  PrintWriter outFile: holds reference to output file
	 *  Scanner kybd: holds a reference to the input file
	 * Process:
	 * 	Prompts the user for an account number
	 * 	The account Number is then send to the findAccount method
	 * 	if the account is not found
	 * 	it catches the custom made, AccountNotFound exception
	 * 	which was thrown in the findAccount Method
	 * 	If it is found it then prompts the user for the 
	 * 	interest rate which is sent to the addInterest method
	 * 	in the savings account class
	 * 	If that method throws an exception then this method catches it
	 * 	and prints the appropriate messages
	 * Output:
	 * 	Prints a receipt of the transaction
	 * 	If an Account is found, it print a receipt with the 
	 * 	account information	If the account is not found then 
	 *  it prints a receipt with an 
	 * 	error message of why the transaction could not be completed
	 */
	public static void addInterest(ArrayList<SavingsAccount> savings,
			PrintWriter outFile, Scanner kybd) {
		
		String divider = "--------------------------------";
		int requestedAccount, index;
		double oldbalance, newBalance, amountCredited, interestRate;
		Calendar calendar = Calendar.getInstance();
		System.out.println("Please enter an account number: ");
		try {
			requestedAccount = kybd.nextInt();
			index=findAccount(savings, outFile, requestedAccount);
			oldbalance = savings.get(index).accountBalance;
			System.out.println("Please enter the interest rate: ");
			interestRate = kybd.nextDouble();
			savings.get(index).addInterest(interestRate);
			newBalance = savings.get(index).accountBalance;
			amountCredited = newBalance - oldbalance;
			outFile.println();
			outFile.println("***RECEIPT***");
			outFile.println(divider);
			outFile.println("Transaction: Add Interest");
			outFile.println("Date of transaction: " + calendar.getTime());
			outFile.println("The account number is: " + requestedAccount);
			outFile.println("The old balance was: $" + oldbalance);
			outFile.println("The rate of interest is: " + interestRate 
					+ "%");
			outFile.print("The amount to be credited is: "); 
			outFile.printf("$%3.2f", amountCredited); 
			outFile.println("\nThe new balance is: $" 
			+ newBalance);
			outFile.println(divider);
		}catch(AccountNotFound e) {
			outFile.println();
			outFile.println("***RECEIPT***");
			outFile.println(divider);
			outFile.println("Transaction: Add Interest");
			outFile.println("Date of transaction: " + calendar.getTime());
			outFile.println(e.getMessage());
			outFile.println(divider);
		}finally{
			outFile.println();
			outFile.flush();
			
		}
		
	}
	
}
