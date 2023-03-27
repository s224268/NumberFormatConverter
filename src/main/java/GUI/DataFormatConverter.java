package GUI;

import java.awt.*;
import java.math.BigInteger;
import java.util.Base64;
import java.util.Objects;

public class DataFormatConverter {

    static Gui gui = Gui.getInstance();

    public void updateFromDecimal(){
        String text = gui.getJdecimalNumber();
        text = text.toUpperCase();
        boolean foundMistake = false;
        char[] tempArray = text.toCharArray();
        for (int i = 0; i<text.length();i++){
            if (!isdigit(tempArray[i], 10)){
                foundMistake = true;
                break;
            }
        }
        if (foundMistake){
            gui.setJdecimalNumber(Color.red);
        }
        else{
            gui.setJdecimalNumber(Color.black);
            if (!Objects.equals(text, "")){
                BigInteger value = new BigInteger(text,10);
                String binary = value.toString(2);
                String hexValue = value.toString(16);
                gui.setJhexNumber(hexValue);
                gui.setJhexNumber(Color.black);
                gui.setJbinaryNumber(binary);
                gui.setJbinaryNumber(Color.black);
            }
            else {
                gui.setJdecimalNumber("");
                gui.setJbinaryNumber("");
            }
        }
    }
    public static void updateFromBinary() {

        String text = gui.getJbinaryNumber();
        text = text.toUpperCase();
        boolean foundMistake = false;
        char[] tempArray = text.toCharArray();
        for (int i = 0; i<text.length();i++){
            if (!isdigit(tempArray[i], 2)){
                foundMistake = true;
                break;
            }
        }
        if (foundMistake){
            gui.setJbinaryNumber(Color.red);
        }
        else{
            gui.setJbinaryNumber(Color.black);
            if (!Objects.equals(text, "")){
                BigInteger value = new BigInteger(text,2);
                String hexValue = value.toString(16);
                String decimalValue = value.toString(10);
                gui.setJdecimalNumber(decimalValue);
                gui.setJdecimalNumber(Color.black);
                gui.setJhexNumber(hexValue);
                gui.setJhexNumber(Color.black);
            }
            else {
                System.out.println("String is null?");
                gui.setJdecimalNumber("");
                gui.setJhexNumber("");
            }
        }
    }
    public static void updateFromHex(){
        String text = gui.getJhexNumber();
        text = text.toUpperCase();
        boolean foundMistake = false;
        char[] tempArray = text.toCharArray();
        for (int i = 0; i<text.length();i++){
            if (!isdigit(tempArray[i], 16)){
                foundMistake = true;
                break;
            }
        }
        if (foundMistake){
            gui.setJhexNumber(Color.red);
        }
        else{
            gui.setJhexNumber(Color.black);
            if (!Objects.equals(text, "")){
                BigInteger value = new BigInteger(text,16);
                String binary = value.toString(2);
                String decimalValue = value.toString(10);
                gui.setJdecimalNumber(decimalValue);
                gui.setJdecimalNumber(Color.black);
                gui.setJbinaryNumber(binary);
                gui.setJbinaryNumber(Color.black);
            }
            else {
                gui.setJdecimalNumber("");
                gui.setJbinaryNumber("");
            }
        }
    }


    public static boolean isdigit(char c, int radix){
        char[] charset = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        String cString = Character.toString(c);
        if (radix == 64){
            String j = "k" + cString;
            try {
                Base64.getDecoder().decode(j);
                System.out.println("Char c is valid for base64");
                return true;
            }
            catch (Exception e){
                System.out.println("Not a base 64 number");
                return false;
            }
        }
        else{
            for (int i = 1;i<=radix;i++){
                if (charset[i-1] == c){
                    System.out.println("Char c (" + c + ") is within radix");
                    return true;
                }
            }
            System.out.println("Char c " + c + "is not within radix");
            return false;
        }
    }

}
