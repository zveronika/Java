package hr.fer.oprpp1.hw05.crypto;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * @author Veronika Žunar
 *
 */
public class Crypto {

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		int len = args.length;
		if (len < 2) {
			System.out.println("Not enough arguments given.");
			return;
		}

		String command = args[0].toLowerCase();

		if (len == 2 && command.equals("checksha")) {
			MessageDigest shaDigest = MessageDigest.getInstance("SHA-256");
			checkSha(shaDigest, args[1]);

		} else if (len == 3 && command.equals("encrypt") || command.equals("decrypt")) {
			boolean encrypt = command.equals("encrypt");
			crypting(args[1], args[2], encrypt);

		} else {
			System.out.println("Invalid arguments given.");
		}
	}

	/**
	 * 
	 * @param shaDigest
	 * @param file
	 * @throws IOException
	 */
	private static void checkSha(MessageDigest shaDigest, String file) throws IOException {
		Path filePath = Paths.get(file);
		Scanner sc = new Scanner(System.in);

		try {

			InputStream inputStream = new BufferedInputStream(Files.newInputStream(filePath));

			System.out.println("Please provide expected sha-256 digest for " + Paths.get(file).getFileName() + ":");
			System.out.print("> ");
			String expectedSha = sc.nextLine();

			byte[] byteArray = new byte[4096];
			int bytesCnt = 0;

			while ((bytesCnt = inputStream.read(byteArray)) != -1) {
				shaDigest.update(byteArray, 0, bytesCnt);
			}

			String digest = Util.bytetohex(shaDigest.digest());

			if (expectedSha.equals(digest)) {
				System.out.println("Digesting completed. Digest of " + Paths.get(file).getFileName()
						+ " matches expected digest.");
			} else {
				System.out.println("Digesting completed. Digest of " + Paths.get(file).getFileName()
						+ " does not match the expected digest. Digest was: " + digest);
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			sc.close();
		}
	}

	/**
	 * 
	 * @param string
	 * @param string2
	 */
	private static void crypting(String fromFile, String toFile, boolean encrypt) {
		Path fromFilePath = Paths.get(fromFile);
		Path toFilePath = Paths.get(toFile);
		Scanner sc = new Scanner(System.in);

		try {
			InputStream inputStream = new BufferedInputStream(Files.newInputStream(fromFilePath));
			OutputStream outputStream = new BufferedOutputStream(Files.newOutputStream(toFilePath));

			System.out.println("Please provide password as hex-encoded text (16 bytes, i.e. 32 hex-digits):");
			System.out.print("> ");
			String keyText = sc.nextLine();
			System.out.println("Please provide initialization vector as hex-encoded text (32 hex-digits):");
			System.out.print("> ");
			String ivText = sc.nextLine();

			SecretKeySpec keySpec = new SecretKeySpec(Util.hextobyte(keyText), "AES");
			AlgorithmParameterSpec paramSpec = new IvParameterSpec(Util.hextobyte(ivText));
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(encrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, keySpec, paramSpec);

			byte[] byteArray = new byte[4096];

			int bytesCnt = 0;

			while ((bytesCnt = inputStream.read(byteArray)) != -1) {
				outputStream.write(cipher.update(byteArray, 0, bytesCnt));
				outputStream.flush();

			}
			cipher.doFinal();

			System.out.println((encrypt ? "Encryption" : "Decryption") + " completed. Generated file "
					+ Paths.get(toFile).getFileName() + " based on file " + Paths.get(fromFile).getFileName() + ".");

		} catch (Exception e) {
			System.out.println(e);

		} finally {
			sc.close();
		}

	}
}
