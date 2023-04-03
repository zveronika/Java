package hr.fer.oprpp1.hw05.crypto;

import java.util.regex.Pattern;

public class Util {

	private static final Pattern HEXADECIMAL_PATTERN = Pattern.compile("\\p{XDigit}+");

	public static byte[] hextobyte(String keyText) {
		if (!HEXADECIMAL_PATTERN.matcher(keyText).matches()) 
			throw new IllegalArgumentException();
		
		int len = keyText.length();

		if (len % 2 != 0)
			throw new IllegalArgumentException();
		
		if (len == 0)
			return new byte[0];

		byte[] bytes = new byte[len / 2];

		for (int i = 0; i < len; i += 2) {
			bytes[i / 2] = (byte) ((Character.digit(keyText.charAt(i), 16) << 4)
					+ Character.digit(keyText.charAt(i + 1), 16));
		}
		return bytes;
	}
	

	public static String bytetohex(byte[] bytearray) {
		StringBuilder sb = new StringBuilder();

		for (byte b : bytearray)
			sb.append(String.format("%02X", b));

		return sb.toString().toLowerCase();
	}
}
