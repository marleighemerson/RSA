/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsa._idea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.System.in;
import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author marleighwilliams
 */
public class RSA_idea {
    
    private BigInteger p, q, e, n, d, z; //variables
    
    private int lengthofbit = 1024; //kilobit #
    
    private Random r; 
    
    public RSA_idea()
    {
        r = new Random();
        
        p = BigInteger.probablePrime(lengthofbit, r);
        
        q = BigInteger.probablePrime(lengthofbit, r);
        
        e = BigInteger.probablePrime(lengthofbit/2, r);
        
        n = p.multiply(q);
        
        z = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
       
        while(z.gcd(e).compareTo(BigInteger.ONE)>0 && e.compareTo(z)<0)
        {
            e.add(BigInteger.ONE);
        }
        
        d = e.modInverse(z);
        }
    
    public RSA_idea(BigInteger e, BigInteger d, BigInteger n){
        
        this.d = d;
        
        this.n = n;
        
        this.e = e;
        
        
    }
    
 
    public static void main(String[] args) throws IOException{
        
        RSA_idea rsa = new RSA_idea();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        
        String checkString;
        
        System.out.println("Enter text:");
        
        checkString = br.readLine();
        
        System.out.println("Encrypted String: " + checkString);
        
        System.out.println("String in Bytes: " + byteMyString(checkString.getBytes()));
        
        byte[] encrypt = rsa.encrypt(checkString.getBytes());
        
        byte[] decrypt = rsa.decrypt(encrypt);
        
        System.out.println("Decrypted Bytes: " + byteMyString(decrypt));
        
        System.out.println("Decrypted String: " + new String(decrypt));
    }
    
    public static String byteMyString(byte[] encrypt){
        
        String check = "";
        
        for (byte b: encrypt)
        {
            check += Byte.toString(b);
        }
        return check;
    }
    
    public byte[] encrypt(byte[] message){
        return(new BigInteger(message)).modPow(e, n).toByteArray();
    }
    
    public byte[] decrypt(byte[] message){
        return (new BigInteger(message)).modPow(d,n).toByteArray();
    }
    
}
