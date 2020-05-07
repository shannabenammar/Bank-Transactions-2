public class InsufficientFunds extends Exception{

	/*
	 * Constructor InsufficientFunds
	 * Input:
	 *  double b: holds the value of the number which was to high
	 * Process:
	 *  Calls the Exception class constructor, and sends it an 
	 * 	error message.
	 * Output:
	 *  N/A
	 */
	public InsufficientFunds(double b) {
		super("Error: " + b + " is an amount which is"
				+ "\nmore than the balance of the account");
	}

}
