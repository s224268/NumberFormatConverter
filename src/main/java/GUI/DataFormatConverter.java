package GUI;

import java.awt.*;
import java.math.BigInteger;
import java.util.Base64;
import java.util.Objects;

public class DataFormatConverter {

    static Gui gui = Gui.getInstance();

    static int firstInvalidHexCharacterPosition = Integer.MAX_VALUE;
    static int firstInvalidBinaryCharacterPosition = Integer.MAX_VALUE;
    static int firstInvalidDecimalCharacterPosition = Integer.MAX_VALUE;

    public void updateFromDecimal(char c){
        String text = gui.getJdecimalNumber();
        boolean foundMistake = false;
        char[] tempArray = text.toCharArray();
        for (int i = 0; i<text.length();i++){
            if (!isdigit(tempArray[i],10)){
                foundMistake = true;
            }
        }
        if (foundMistake){
            gui.setJdecimalNumber(Color.red);
        }
        if (isdigit(c,10)) {
            text = text + c;
        }
        if (!Objects.equals(text, "")){
            BigInteger value = new BigInteger(text);
            String hex = value.toString(16);
            hex = hex.toUpperCase();
            String binary = value.toString(2);
            gui.setJhexNumber(hex);
            gui.setJbinaryNumber(binary);
        }
        else {
            System.out.println("Value is null");
            gui.setJbinaryNumber("");
            gui.setJhexNumber("");
        }
    }

    public static void updateFromBinary(char c) {
        String text = gui.getJbinaryNumber();
        boolean foundMistake = false;
        char[] tempArray = text.toCharArray();
        for (int i = 0; i<text.length();i++){
            foundMistake = !isdigit(tempArray[i], 2);
        }
        if (foundMistake){
            gui.setJbinaryNumber(Color.red);
        }
        else{
            gui.setJbinaryNumber(Color.black);
        }

        //if (text.length() < firstInvalidBinaryCharacterPosition){
            //gui.setJbinaryNumber(Color.black);
            //gui.setJhexNumber(Color.black);
            //gui.setJdecimalNumber(Color.black);
        //}
        c = Character.toUpperCase(c);
        if (isdigit(c,2)) {
            text = text + c;
            text = text.toUpperCase();
        }
        else if (!(c == 8 || c == 127)) {
            gui.setJbinaryNumber(Color.red);
            if (firstInvalidBinaryCharacterPosition == Integer.MAX_VALUE){
                firstInvalidBinaryCharacterPosition = text.length();
            }
            System.out.println("firstInvalidCharacterPosition: " + firstInvalidBinaryCharacterPosition);
        }
        if (!Objects.equals(text, "")){
            try {
                BigInteger value = new BigInteger(text,2);
                System.out.println("Hex value is: " + value);
                String hex = value.toString(16);
                String decimalValue = value.toString(10);
                gui.setJdecimalNumber(decimalValue);
                gui.setJhexNumber(hex);
            }
            catch (NumberFormatException e){
                System.out.println("NumberFormatException caught");
            }
        }
        else{
            System.out.println("String is null?");
            gui.setJdecimalNumber("");
            gui.setJhexNumber("");
        }
        /*

        if (c == 8 || c == 127) {
            text = gui.getJbinaryNumber();
            gui.setJbinaryNumber(Color.black);
        } else if (isdigit(c, 2)) {
            text = text + c;
            gui.setJbinaryNumber(Color.black);
        } else {
            gui.setJbinaryNumber(Color.red);
        }
        if (!Objects.equals(text, "")) {
            try {
                BigInteger value = new BigInteger(text, 2);
                String valueString = value.toString(10);
                String hex = value.toString(16);
                hex = hex.toUpperCase();
                gui.setJhexNumber(hex);
                gui.setJdecimalNumber(valueString);
            }
            catch (NumberFormatException e){
                System.out.println("NumberFormatException, not updating other fields");
            }
        } else {
            System.out.println("String is null");
            gui.setJdecimalNumber("");
            gui.setJhexNumber("");
        }
        /*
        if (text.length() > 40) {
            gui.setJbinaryNumberWidth(10 * text.length());
            gui.setJHexNumberWidth(10 * text.length());
            gui.setJDecimalNumberWidth(10 * text.length());
            gui.setLayout(null);
            gui.setSize(500,text.length()*10+70);
            gui.setLayout(null);
            gui.setVisible(true);
        }

         */
    }
    public static void updateFromHex(char c){
        String text = gui.getJhexNumber();
        if (text.length() < firstInvalidHexCharacterPosition){
            gui.setJhexNumber(Color.black);
            gui.setJbinaryNumber(Color.black);
            gui.setJdecimalNumber(Color.black);
        }
        c = Character.toUpperCase(c);
        //I dont understand this
        if (isdigit(c,16)) {
            text = text + c;
            text = text.toUpperCase();
        }
        else if (!(c == 8 || c == 127)) {
            gui.setJhexNumber(Color.red);
            if (firstInvalidHexCharacterPosition == Integer.MAX_VALUE){
                firstInvalidHexCharacterPosition = text.length();
            }
            System.out.println("firstInvalidCharacterPosition: " + firstInvalidHexCharacterPosition);
        }
        if (!Objects.equals(text, "")){
            try {
                BigInteger value = null;
                value = new BigInteger(text,16);
                System.out.println("Hex value is: " + value);
                String binary = null;
                String decimalValue = null;
                binary = value.toString(2);
                decimalValue = value.toString(10);
                gui.setJdecimalNumber(decimalValue);
                gui.setJbinaryNumber(binary);
            }
            catch (NumberFormatException e){
                System.out.println("NumberFormatException caught");
            }
        }
        else{
            System.out.println("String is null?");
            gui.setJdecimalNumber("");
            gui.setJbinaryNumber("");
        }
    }


    public static boolean isdigit(char c, int radix){
        char[] charset = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        String hexset = "0123456789ABCDEF";
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
            System.out.println("Char c is not within radix");
            return false;
        }
    }

}
