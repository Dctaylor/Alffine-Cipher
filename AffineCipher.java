import java.util.Scanner;

public class AffineCipher {
   public static void main(String[] args) {
      Methods run = new Methods();
      Scanner input = new Scanner(System.in);
      
      System.out.println("Would you like encipher or decipher a string?");
      System.out.println("1. Encrypt | 2. Decipher");
      int choice = input.nextInt();
      
      if(choice == 1) {
         //If the user chose to encrypt
         System.out.print("\nPlease enter a string to be encrypted (no spaces): ");
         String plaintext = input.next();
         System.out.print("\nPlease enter an additive key: ");
         int keyAdd = input.nextInt();
         System.out.println();
         System.out.print("Please enter a multiplicative key (don't use multiples or divisors of 26): ");
         int keyMult = input.nextInt();
         
         String ciphertext = run.encrypt(plaintext, keyAdd, keyMult);
         System.out.println("\nCiphertext: " + ciphertext);
      }
      else {
         //If the user chose to decipher
         System.out.print("\nPlease enter a string to be deciphered (no spaces): ");
         String ciphertext = input.next();
         System.out.print("\nPlease enter an additive key: ");
         int keyAdd = input.nextInt();
         System.out.println("");
         System.out.print("Please enter a multiplicative key (don't use multiples or divisors of 26): ");
         int keyMult = input.nextInt();
         
         String plaintext = run.decipher(ciphertext, keyAdd, keyMult);
         System.out.println("\nPlaintext: " + plaintext);
      }
   }
}

class Methods {
   /**
      Encrypts a string using an additive and multiplicative key
      @param plaintext A legible string
      @param keyAdd An additive key
      @param keyMulti A multiplicative key
      @return The encrypted plaintext, or ciphertext
   */
   public String encrypt(String plaintext, int keyAdd, int keyMult) {
      String result = "";
      for(int i = 0; i < plaintext.length(); i++) {
         int charInt = (char) plaintext.charAt(i);
         if(charInt < 94) {
            //For uppercase letters
            charInt = ((charInt - 65) * keyMult + keyAdd) % 26 + 65;
         }
         else {
            //For lowercase letters
            charInt = ((charInt - 97) * keyMult + keyAdd) % 26 + 97;
         }
         result += (char) charInt;
      }
      return result;
   }
   
   /**
      Deciphers a string using an additive and multiplicative key
      @param ciphertext An enciphered string
      @param keyAdd The additive key used to encipher the string
      @param keyMult The multiplicative key used to encipher the string
      @return The deciphered ciphertext, or plaintext
   */
   public String decipher(String ciphertext, int keyAdd, int keyMult) {
      //Gets the inverse of the original additive key
      int keyAddReverse = 26 - keyAdd;
      
      //Gets the inverse of the original multiplicative key
      int keyMultReverse;
      for(int i = 1;; i++) {
         if((i * keyMult) % 26 == 1) {
            keyMultReverse = i;
            break;
         }
      }
      
      //Deciphers the ciphertext
      String result = "";
      for(int j = 0; j < ciphertext.length(); j++) {
         int charInt = (int) ciphertext.charAt(j);
         if(charInt < 94) {
            //For uppercase letters
            charInt = ((charInt - 65 + keyAddReverse) * keyMultReverse) % 26 + 65;
         }
         else {
            //For lowercase letters
            charInt = ((charInt - 97 + keyAddReverse) * keyMultReverse) % 26 + 97;
         }
         result += (char) charInt;
      }
      return result;
   }
}