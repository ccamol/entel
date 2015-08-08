package com.esa.ponline.portalbolsas.sec.encrypt.test;



import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.esa.ponline.portalbolsas.sec.encrypt.Hex;


public class TestingEncriptacion {

		 static Cipher cipher;
		 
			public static void main(String[] args) throws Exception {
				String keyString = "ptraficoexcedido";

				SecretKey secretKey = new SecretKeySpec(keyString.getBytes(),"AES");
				cipher = Cipher.getInstance("AES");

				String plainText = "AES Symmetric Encryption Decryption";
				System.out.println("Plain Text Before Encryption: " + plainText);

				String encryptedText = new String();
				//encryptedText = encrypt(plainText, secretKey);
				//System.out.println("Encrypted Text After Encryption: " + encryptedText);

				encryptedText="B358CC727B87FD836562745F81DE2BE0";
				String decryptedText = decrypt(encryptedText, secretKey);
				System.out.println("Decrypted Text After Decryption: " + decryptedText);
				
				encryptedText="2277BA6C16A8CDD6DA2E548B8D9FA00E";
				decryptedText = decrypt(encryptedText, secretKey);
				System.out.println("Decrypted Text After Decryption: " + decryptedText);
				
				encryptedText="81066CF063AA97932E4FBF4E046CFC59";
				decryptedText = decrypt(encryptedText, secretKey);
				System.out.println("Decrypted Text After Decryption: " + decryptedText);
				
				//PMOVIL
				encryptedText="56977467726";
				System.out.println("NRO A ENCRIPTAR: " + encryptedText);
				decryptedText = encrypt(encryptedText, secretKey).toUpperCase();
				System.out.println("Decrypted Text After Decryption: " + decryptedText);
				
				//WEBSGOPP CANAL PARA LISTAR BOLSAS
				encryptedText="56994997553";
				System.out.println("NRO A ENCRIPTAR: " + encryptedText);
				decryptedText = encrypt(encryptedText, secretKey).toUpperCase();
				System.out.println("Decrypted Text After Decryption: " + decryptedText);
				
				//WEBSGOPP VALIDA PLAN MM AUTOGESTION
				encryptedText="56956275885";
				System.out.println("NRO A ENCRIPTAR: " + encryptedText);
				decryptedText = encrypt(encryptedText, secretKey).toUpperCase();
				System.out.println("Decrypted Text After Decryption: " + decryptedText);
				
				// NUEVOS NUMEROS
				encryptedText="56962000720";
				System.out.println("NRO A ENCRIPTAR: " + encryptedText);
				decryptedText = encrypt(encryptedText, secretKey).toUpperCase();
				System.out.println("Decrypted Text After Decryption: " + decryptedText);
				
				encryptedText="56962000347";
				System.out.println("NRO A ENCRIPTAR: " + encryptedText);
				decryptedText = encrypt(encryptedText, secretKey).toUpperCase();
				System.out.println("Decrypted Text After Decryption: " + decryptedText);
				
				encryptedText="56981560126";
				System.out.println("NRO A ENCRIPTAR: " + encryptedText);
				decryptedText = encrypt(encryptedText, secretKey).toUpperCase();
				System.out.println("Decrypted Text After Decryption: " + decryptedText);		
				
                encryptedText="56942104073";
                System.out.println("NRO A ENCRIPTAR: " + encryptedText);
                decryptedText = encrypt(encryptedText, secretKey).toUpperCase();
                System.out.println("Decrypted Text After Decryption: " + decryptedText);
                
                encryptedText="56942209651";
                System.out.println("NRO A ENCRIPTAR: " + encryptedText);
                decryptedText = encrypt(encryptedText, secretKey).toUpperCase();
                System.out.println("Decrypted Text After Decryption: " + decryptedText);  
                
                encryptedText="56993170035";
                System.out.println("NRO A ENCRIPTAR: " + encryptedText);
                decryptedText = encrypt(encryptedText, secretKey).toUpperCase();
                System.out.println("Decrypted Text After Decryption: " + decryptedText);
                
                encryptedText="56981560126";
                System.out.println("NRO A ENCRIPTAR: " + encryptedText);
                decryptedText = encrypt(encryptedText, secretKey).toUpperCase();
                System.out.println("Decrypted Text After Decryption: " + decryptedText);                
			}

			public static String encrypt(String plainText, SecretKey secretKey)
					throws Exception {
				byte[] plainTextByte = plainText.getBytes();
				cipher.init(Cipher.ENCRYPT_MODE, secretKey);
				byte[] encryptedByte = cipher.doFinal(plainTextByte);
				char[] encryptedHex = Hex.encodeHex(encryptedByte);
				String encryptedText = new String(encryptedHex);
				return encryptedText;
			}

			public static String decrypt(String encryptedText, SecretKey secretKey)
					throws Exception {
				
				byte[] encryptedTextByte = Hex.decodeHex(encryptedText.toCharArray());
				cipher.init(Cipher.DECRYPT_MODE, secretKey);
				byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
				String decryptedText = new String(decryptedByte);
				return decryptedText;
			}

}
