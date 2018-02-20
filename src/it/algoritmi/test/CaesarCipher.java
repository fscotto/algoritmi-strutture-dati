package it.algoritmi.test;

public class CaesarCipher {
	protected char[] encoder = new char[26];
	protected char[] decoder = new char[26];
	
	public CaesarCipher(int rotation) {
		for (int k = 0; k < 26; ++k) {
			encoder[k] = (char) ('A' + (k + rotation) % 26);
			decoder[k] = (char) ('A' + (k - rotation + 26) % 26);
		}
	}
	
	/** Restituisce il messaggio cifrato. */
	public String encrypt(String message) {
		return trasform(message, encoder);
	}
	
	/** Restituisce il messaggio decifrato. */
	public String decrypt(String secret) {
		return trasform(secret, decoder);
	}
	
	/** Restituisce la trasformazione della stringa ricevuta secondo il codice dato. */
	private String trasform(String original, char[] code) {
		char[] msg = original.toCharArray();
		for (int k = 0; k < msg.length; ++k) {
			if(Character.isUpperCase(msg[k])) {
				int j = msg[k] - 'A';
				msg[k] = code[j];
			}
		}
		return new String(msg);
	}
	
	public static void main(String[] args) {
		CaesarCipher cipher = new CaesarCipher(3);
		System.out.println("Encryption code = " + new String(cipher.encoder));
		System.out.println("Decryption code = " + new String(cipher.decoder));
		String message = "THE EAGLE IS IN PLAY; MEET AT JOE'S.";
		String coded = cipher.encrypt(message);
		System.out.println("Secret: " + coded);
		String answer = cipher.decrypt(coded);
		System.out.println("Message: " + answer);
	}
	
}
