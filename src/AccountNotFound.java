/*
 * Shanna Benammar
 * 
 * Final Exam part one
 * 
 * Professor Ziegler 
 * 
 */
package FinalExamClasses;

public class AccountNotFound extends Exception{
	
	/*
	 * Constructor AccountNotFound
	 * Input:
	 *  int a: holds the number of the account
	 * Process:
	 * 	Calls the Exception class constructor, and sends it an 
	 * 	error message.
	 * Output:
	 *  N/A
	 */
	public AccountNotFound(int a) {
		super("Error: an account with the number " + a + 
				" \ndoes not exist in our system");
	}
}
