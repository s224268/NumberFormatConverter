package GUI;

import java.awt.*;
import java.math.BigInteger;
import java.util.Base64;
import java.util.Objects;

public class DataFormatConverter {

    static Gui gui = Gui.getInstance();

    public void updateFromDecimal(){
        String text = gui.getJDecimalNumber();
        text = text.toUpperCase();
        boolean foundMistake = false;
        char[] tempArray = text.toCharArray();
        for (int i = 0; i<text.length();i++){
            if (isNotDigit(tempArray[i], 10)){
                foundMistake = true;
                break;
            }
        }
        if (foundMistake){
            gui.setJDecimalNumber(Color.red);
        }
        else{
            gui.setJDecimalNumber(Color.black);
            if (!Objects.equals(text, "")){
                BigInteger value = new BigInteger(text,10);
                String binary = value.toString(2);
                String hexValue = value.toString(16).toUpperCase();
                gui.setJHexNumber(hexValue);
                gui.setJHexNumber(Color.black);
                gui.setJBinaryNumber(binary);
                gui.setJBinaryNumber(Color.black);
            }
            else {
                gui.setJDecimalNumber("");
                gui.setJBinaryNumber("");
            }
        }
    }
    public static void updateFromBinary() {

        String text = gui.getJBinaryNumber();
        text = text.toUpperCase();
        boolean foundMistake = false;
        char[] tempArray = text.toCharArray();
        for (int i = 0; i<text.length();i++){
            if (isNotDigit(tempArray[i], 2)){
                foundMistake = true;
                break;
            }
        }
        if (foundMistake){
            gui.setJBinaryNumber(Color.red);
        }
        else{
            gui.setJBinaryNumber(Color.black);
            if (!Objects.equals(text, "")){
                BigInteger value = new BigInteger(text,2);
                String hexValue = value.toString(16).toUpperCase();
                String decimalValue = value.toString(10);
                gui.setJDecimalNumber(decimalValue);
                gui.setJDecimalNumber(Color.black);
                gui.setJHexNumber(hexValue);
                gui.setJHexNumber(Color.black);
            }
            else {
                System.out.println("String is null?");
                gui.setJDecimalNumber("");
                gui.setJHexNumber("");
            }
        }
    }
    public static void updateFromHex(){
        String text = gui.getJHexNumber();
        text = text.toUpperCase();
        boolean foundMistake = false;
        char[] tempArray = text.toCharArray();
        for (int i = 0; i<text.length();i++){
            if (isNotDigit(tempArray[i], 16)){
                foundMistake = true;
                break;
            }
        }
        if (foundMistake){
            gui.setJHexNumber(Color.red);
        }
        else{
            gui.setJHexNumber(Color.black);
            if (!Objects.equals(text, "")){
                BigInteger value = new BigInteger(text,16);
                String binary = value.toString(2);
                String decimalValue = value.toString(10);
                gui.setJDecimalNumber(decimalValue);
                gui.setJDecimalNumber(Color.black);
                gui.setJBinaryNumber(binary);
                gui.setJBinaryNumber(Color.black);
            }
            else {
                gui.setJDecimalNumber("");
                gui.setJBinaryNumber("");
            }
        }
    }

    public static boolean isNotDigit(char c, int radix){
        char[] charset = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        String cString = Character.toString(c);
        if (radix == 64){
            String j = "k" + cString;
            try {
                Base64.getDecoder().decode(j);
                System.out.println("Char c is valid for base64");
                return false;
            }
            catch (Exception e){
                System.out.println("Not a base 64 number");
                return true;
            }
        }
        else{
            for (int i = 1;i<=radix;i++){
                if (charset[i-1] == c){
                    System.out.println("Char c (" + c + ") is within radix");
                    return false;
                }
            }
            System.out.println("Char c " + c + "is not within radix");
            return true;
        }
    }
}
