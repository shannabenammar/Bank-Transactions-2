/*
 * Shanna Benammar
 * 
 * Final Exam part one
 * 
 * Professor Ziegler 
 * 
 */
package FinalExamClasses;


public abstract class BankAccount {
	
	protected int accountNum;
	protected double accountBalance;
	
	/*
	 * BankAccount No Arg constructor
	 * Input:
	 * 	N/a
	 * Process:
	 *	 Initializes protected data fields 
	 * Output:
	 *	 N/A
	 */
	public BankAccount() {
		accountNum = 0;
		accountBalance = 0;
	}
	/*
	 * BankAccount constructor 
	 * Input:
	 *  int accountNumber: holds the number for the account
	 *  double accountBalance: hold the balance for the account
	 * Process:
	 * 	sets the value of the data fields accountNum, and accountBalance
	 * 	to the values that were received. 
	 * Output:
	 * 	N/A
	 */
	
	public BankAccount(int accountNum, double accountBalance) {
		this.accountNum = accountNum;
		this.accountBalance = accountBalance;		
	}
	/*
	 * BankAccount copy constructor 
	 * Input:
	 *  BankAccount bank: copy of a bankAccount object
	 * Process:
	 * 	sets the value of the data fields accountNum, and accountBalance
	 * 	to the values held for those fields within the object that was
	 * 	received
	 * Output:
	 * 	N/A
	 */
	public BankAccount(BankAccount bank) {
		this.accountNum = bank.accountNum;
		this.accountBalance = bank.accountBalance;
	}
	/*
	 * Abstract method getBalance()
	 * Input:
	 *  N/A
	 * Process:
	 * 	Waiting to be overridden by subclass
	 * Output:
	 * 	will be returning a double once overridden
	 */
	public abstract double getBalance();
	
	/*
	 * Abstract method makeDeposit()
	 * Input:
	 *  double Balance: the balance of an account
	 * Process:
	 * 	waiting to be overridden by subclass
	 * Output:
	 * 	N/A
	 */
	public abstract void makeDeposit(double Balance)
			throws NegativeAmountEntered;
	/*
	 * Abstract method makeWithdrawal
	 * Input:
	 *  double amount: holds the amount that is going to be taken out 
	 *  of the account
	 * Process:
	 * 	waiting to be overridden by subclass
	 * Output:
	 * 	N/A
	 */
	public abstract void makeWithdrawal(double amount)
			throws NegativeAmountEntered, InsufficientFunds;
	
}
