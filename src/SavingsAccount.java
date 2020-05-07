/*
 * Shanna Benammar
 * 
 * Final Exam part one
 * 
 * Professor Ziegler 
 * 
 */
package FinalExamClasses;


public class SavingsAccount extends BankAccount implements Interest{
	
	/*
	 * Constructor No Arg SavingsAccount
	 * Input:
	 *  N/A
	 * Process:
	 * 	Calls super classes no arg constructor
	 * Output:
	 * 	N/A
	 */
	public SavingsAccount() {
		super();
	}
	
	/*
	 * Constructor SavingsAccount()
	 * Input:
	 *  int a: holds the number for the account
	 *  int b: holds the balance for the account
	 * Process:
	 * 	Calls super constructor in the abstract BankAccount class
	 * 	which accepts the 2 parameters and initializes them.
	 * Output:
	 * 	N/A
	 */
	public SavingsAccount(int a, double b) {
		super(a,b);
	}
	/*
	 * Constructor SavingsAccount()
	 * Input:
	 *  SavingsAccount savings: copy of SavingsAccount object
	 * Process:
	 * 	Initializes the 2 parameters, accountNum and accountBalance,
	 * 	which are inherited by the BankAccount class, to the values held 
	 * 	inside the SavingsAccount copy object that was received.
	 * Output:
	 * 	N/A
	 */
	public SavingsAccount(SavingsAccount savings) {
		this.accountNum = savings.accountNum;
		this.accountBalance = savings.accountBalance;
		
	}
	/*
	 * Method getAcctNumber
	 * Input:
	 *  N/A		
	 * Process:
	 * 	Returns the value of the account number 
	 * Output:
	 * 	N/A
	 */
	public int getAcctNumber() {
		return accountNum;
	}
	/*
	 * Method getBalance
	 * Input:
	 *  N/A
	 * Process:
	 * 	Overrides the abstract method getBalance in the 
	 * 	abstract BankAccount class
	 * 	Returns the value of the accounts balance
	 * Output:
	 * 	N/A
	 */
	@Override
	public double getBalance() {
		return accountBalance;
	}
	/*
	 * Method makeDeposit
	 * Input:
	 *  double amountToDeposit: the amount that is to be added
	 *  to the current balance of the account
	 * Process:
	 * 	Overrides the abstract method makeDeposit in the abstract
	 * 	bankAccount class.
	 * 	checks to see if the amount thats to be deposited is less
	 * 	than zero. 
	 * 	If the amount is less than zero then it throws the custom made,
	 * 	NegativeAmountEntered exception. 
	 * 	If the amount thats to be deposited is more than zero then 
	 * 	the amount is added to the current balance of the account
	 * Output:
	 * 	N/A
	 */
	@Override
	public void makeDeposit(double amountToDeposit)
			throws NegativeAmountEntered {
		if(amountToDeposit < 0) {
			throw new NegativeAmountEntered(amountToDeposit);
		}
		else {
			accountBalance += amountToDeposit;
		}
	}
	
	/*
	 * Method makeWithdrawal
	 * Input:
	 *  double amountToWithdrawal: the amount to be withdrawn from the
	 *  current balance of the account
	 * Process:
	 *  Overrides the abstract method makeWithdrawal in the abstract
	 * 	bankAccount class
	 * 	Tests to see if the amount to be withdrawn is less than zero
	 * 	if it is, then a custom made, NegativeAmountEntered exception
	 * 	is thrown
	 * 	Then, it tests to see if the amount to be withdrawn is more than
	 * 	then the current balance of the account.
	 * 	If it is, then a custom made, InsufficientFunds exception
	 * 	is thrown
	 * 	If the amountToWithdrawal is larger than zero and less than the
	 *	amount in the account then the amount to be withdrawn is is taken
	 *	out of the account.	
	 * Output:
	 * 	N/A
	 */
	@Override
	public void makeWithdrawal(double amountToWithdrawal)
			throws NegativeAmountEntered, InsufficientFunds {
		if(amountToWithdrawal < 0)
			throw new NegativeAmountEntered(amountToWithdrawal);
		else if(amountToWithdrawal>accountBalance)
			throw new InsufficientFunds(amountToWithdrawal);
		else
			accountBalance -= amountToWithdrawal;
	}
	/*
	 * method addInterest
	 * Input:
	 *  double i: holds the interest rate
	 * Process:
	 * 	Overrides the addInterest method in the Interest interface
	 * 	It multiplies the current balance by the interest rate
	 * 	which has been turned into a fraction.
	 * 	then that amount is added to the current balance. 
	 * Output:
	 * 	N/A
	 */
	@Override
	public void addInterest(double i) {
		double newAmount = accountBalance * (i/100);
		accountBalance += newAmount;
		
	}
	
	

}
