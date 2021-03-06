package com.cos.security1;

//import org.apache.commons.codec.binary.Base64;
import java.util.Base64;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class UserRsa {
	private Key publicKey;
	private Key privateKey;

	
	public Key getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(Key publicKey) {
		this.publicKey = publicKey;
	}

	public Key getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKeyByString(String privateKey){
		try {
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		byte[] bytePrivateKey = Base64.getDecoder().decode(privateKey.getBytes());
		//X509EncodedKeySpec privateKeySpec = new X509EncodedKeySpec(bytePrivateKey);
		PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(bytePrivateKey);
       //KCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(bytePrivateKey);
        this.privateKey = keyFactory.generatePrivate(privateKeySpec);
		}catch(NoSuchAlgorithmException | InvalidKeySpecException e)
		{
			
		}
	}
	
	public void setPublicKeyByString(String publicKey)
	{
		KeyFactory keyFactory;
		try {
			keyFactory = KeyFactory.getInstance("RSA");
			byte[] bytePublicKey = Base64.getDecoder().decode(publicKey.getBytes());
		    X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(bytePublicKey);
		    this.publicKey = keyFactory.generatePublic(publicKeySpec);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
		}
       
	}

/*	
	public String encrypt(String inputStr) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		Cipher c = Cipher.getInstance("RSA");
		c.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] encrypted = c.doFinal(inputStr.getBytes("UTF-8"));

// ???????????? ?????????(byte ??????)
		return new String(Base64.encodeBase64(encrypted));
	}
*/
	public String decrypt(String inputStr) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		Cipher c = Cipher.getInstance("RSA");
		byte[] encrypted = hexToByteArr(inputStr);
		c.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] decrypted = c.doFinal(encrypted);
		String returnValue = new String(decrypted,"UTF-8");
		//byte[] decrypted = Base64.getDecoder().decode(inputStr.getBytes());
		return returnValue;
	}
	
	public static byte[] hexToByteArr(String hex)
	{
		if(hex==null || hex.length()%2 != 0)
		{
			return new byte[] {};
		}
		byte[] bytes = new byte[hex.length()/2];
		for(int i=0; i<hex.length(); i+=2)
		{
			byte value = (byte)Integer.parseInt(hex.substring(i,i+2),16);
			bytes[(int)Math.floor(i/2)]=value;
		}
		return bytes;
	}
	
	
	
	  /**
     * ?????????
     */
    static String encode(String plainData, String stringPublicKey) {
        String encryptedData = null;
        try {
            //???????????? ???????????? ???????????? ?????????????????? ????????? ??????
            PublicKey publicKey =  getPublicKey(stringPublicKey);
            //???????????? ?????????????????? ???????????? ?????????????????? ???????????? ??????
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            //????????? ??????????????? ??????
            byte[] byteEncryptedData = cipher.doFinal(plainData.getBytes());
            encryptedData = Base64.getEncoder().encodeToString(byteEncryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedData;
    }
    /**
     * ?????????
     */
    static String decode(String encryptedData, String stringPrivateKey) {
        String decryptedData = null;
        try {
            //???????????? ???????????? ???????????? ?????????????????? ????????? ??????
            PrivateKey privateKey = getPrivateKey(stringPrivateKey);
            //???????????? ?????????????????? ???????????? ?????????????????? ???????????? ??????
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            //???????????? ??????????????? ??????
            byte[] byteEncryptedData = Base64.getDecoder().decode(encryptedData.getBytes());
            byte[] byteDecryptedData = cipher.doFinal(byteEncryptedData);
            decryptedData = new String(byteDecryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedData;
    }

    static PublicKey getPublicKey (String stringPublicKey) {
        PublicKey publicKey = null;
        try {
            //???????????? ???????????? ???????????? ?????????????????? ????????? ??????
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] bytePublicKey = Base64.getDecoder().decode(stringPublicKey.getBytes());
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(bytePublicKey);
            publicKey = keyFactory.generatePublic(publicKeySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return publicKey;
    }
    static PrivateKey getPrivateKey (String stringPrivateKey) {
        PrivateKey privateKey = null;
        try {
            //???????????? ???????????? ???????????? ?????????????????? ????????? ??????
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] bytePrivateKey = Base64.getDecoder().decode(stringPrivateKey.getBytes());
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(bytePrivateKey);
            privateKey = keyFactory.generatePrivate(privateKeySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return privateKey;
    }
    
    public static String sign(String plainText,String strPrivateKey) {
        try {
            PrivateKey privateKey = getPrivateKey(strPrivateKey);
            Signature privateSignature = Signature.getInstance("SHA256withRSA");
            privateSignature.initSign(privateKey);
            privateSignature.update(plainText.getBytes("UTF-8"));
            byte[] signature = privateSignature.sign();
            return Base64.getEncoder().encodeToString(signature);
        } catch (NoSuchAlgorithmException | InvalidKeyException | UnsupportedEncodingException | SignatureException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean verifySignarue(String plainText, String signature, String strPublicKey) {
        Signature sig;
        try {
            PublicKey publicKey = getPublicKey(strPublicKey);
            sig = Signature.getInstance("SHA256withRSA");
            sig.initVerify(publicKey);
            sig.update(plainText.getBytes());
            if (!sig.verify(Base64.getDecoder().decode(signature)));
        } catch (NoSuchAlgorithmException | SignatureException e) {
            throw new RuntimeException(e);
        } catch(InvalidKeyException e)
        {
        	return false;
        }
        return true;
    }
	
	
}
