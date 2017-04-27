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
 * Elizabeth Reed
 * Jamie Leary
 * Marleigh Williams
 * 
 * BEST GROUP EVER
 * @author marleighwilliams
 */
public class RSA_idea {
    
    private BigInteger p, q, e, n, d, z; //variables
    
    private int lengthofbit = 1024; //kilobit #
    
    private Random r; 
    
    //RSA Algorithm!
    public RSA_idea()
    {
        r = new Random();
        
        // generating two prime numbers p,q
        p = BigInteger.probablePrime(lengthofbit, r); //probablePrime (BigInteger Method) gives a positive integer that is probably a prime with the specified bit length
        
        q = BigInteger.probablePrime(lengthofbit, r); //gives positive probable prime with specified bit length
        
        e = BigInteger.probablePrime(lengthofbit/2, r);
        
        //computing n p*q
        n = p.multiply(q);
        
        //computing z which is ((p-1)*(q-1)
        z = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)); //ONE = 1
       
        while(z.gcd(e).compareTo(BigInteger.ONE)>0 && e.compareTo(z)<0)
        {
            e.add(BigInteger.ONE);
        }
        
        d = e.modInverse(z);
        }
    
    
 
    public static void main(String[] args) throws IOException{
        
        RSA_idea rsa = new RSA_idea();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(in)); //allows user input
        
        String checkString;
        
        System.out.println("Enter text:");
        
        checkString = br.readLine();
        
        System.out.println("String to be Encrypted: " + checkString);
        
        System.out.println("Encryption: " + byteMyString(checkString.getBytes()));
        
        byte[] encrypt = rsa.encrypt(checkString.getBytes());
        
        byte[] decrypt = rsa.decrypt(encrypt);
        
        System.out.println("Decryption: " + byteMyString(decrypt));
        
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
