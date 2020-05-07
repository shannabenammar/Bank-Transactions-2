public class NegativeAmountEntered extends Exception{
	
	/*
	 * Constructor NegativeAmountEntered
	 * Input:
	 *  double a: holds the value of the negative amount
	 * Process:
	 *  Calls the Exception class constructor, and sends it an 
	 * 	error message.
	 * Output:
	 *  N/A
	 */
	public NegativeAmountEntered(double a) {
		super("Error: " + a + " is a negative amount \nwhich cannot be "
				+ "deposited into an account");
	}

}
