package pe.edu.galaxy.training.java.web.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import pe.edu.galaxy.training.java.web.util.Encrypt;

public class MainEncrypt {
	
	public static void main(String args[]) throws GeneralSecurityException, IOException{
	
		String clave="123";
		
		Encrypt.init("j4v42020.+");// LLave
		
		System.out.println(Encrypt.encrypt(clave));
		//System.out.println(Encrypt.decrypt("m8sr0+h8UWE="));
		
		//xdlOnb5Nv0M=
		//KWE5epAli+M=
		
	}

}
