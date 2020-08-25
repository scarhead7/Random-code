/* Snell, Dallin
** 25 AUG 2020
** An adaptation on old code to answer a Quora question.
*/


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
	
public class bruteForce2 {
	/**
	 * This method will take a String and hash it using the MD5 algorithm.
	 *
	 * @param input the String to hash
	 */
	public static String hashMD5(String input)
	{
		try {
			/**
			 * Holds the MD5 instance.
			 */
			MessageDigest md = MessageDigest.getInstance("MD5");
			/**
			 * Holds the message digest.
			 */
			byte[] messageDigest = md.digest(input.getBytes());
			/**
			 * Holds the converted byte array.
			 */
			BigInteger bi = new BigInteger(1, messageDigest);
			/**
			 * Holds the hex value of the hash.
			 */
			String hashtext = bi.toString(16);

			while(hashtext.length() < 32)
				hashtext = "0" + hashtext;

			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} // end of try-catch
	} // end of hashMD5()

	/**
	 * This method will crack a six-digit PIN hash.
	 *
	 * @param hashToCrack the hashed string to crack
	 */
	public static String crackPIN(String hashToCrack) {
		/**
		 * Has the PIN been cracked?
		 */
		boolean cracked = false;
		/**
		 * The number of PIN permutations tried (100,000 needed).
		 */
		int count = 0;
		/**
		 * The current PIN to try. The variable count will be padded with zeros to
		 * create the proper length PIN.
		 */
		String temp;

		do {
			temp = String.format("%06d", count);
			if(hashMD5(temp).equals(hashToCrack))
				cracked = true;
			count++;
		} while(!cracked);

		return temp;
	} // end of crackPIN

	/**
	 * The driver function.
	 *
	 * @throws NoSuchAlgorithmException if the improper hash algorithm is used in
	 * the hash function
	 */
	public static void main(String args[]) throws NoSuchAlgorithmException
	{
		/**
		 * The PIN that will be hashed and subsequently cracked.
		 */
		String testPIN = "926002";
		/**
		 * The hashed PIN.
		 */
		String hashPIN = hashMD5(testPIN);

		System.out.println("PIN pre-hash: " + testPIN);
		System.out.println("PIN HASH: " + hashPIN);
		System.out.println("PIN post-crack: " + crackPIN(hashPIN));
	} // end of main()
} // end of class bruteForce2
