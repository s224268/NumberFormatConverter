package GUI;

import java.awt.*;
import java.math.BigInteger;
import java.util.Base64;
import java.util.Objects;

public class DataFormatConverter {

    static Gui gui = Gui.getInstance();
    static Color textColor = gui.getTextColor();

    static boolean twosCompliment = true;

    public void updateFromDecimal(){
        String text = gui.getJDecimalNumber();
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
            gui.setJDecimalNumber(textColor);
            if (!Objects.equals(text, "")){
                BigInteger value = new BigInteger(text,10);
                String binary = value.toString(2);
                //TODO

                String hexValue = value.toString(16).toUpperCase();
                gui.setJHexNumber(hexValue);
                gui.setJBinaryNumber(binary);
                gui.updateJDecimalNumberTextColor();
                gui.updateJBinaryNumberTextColor();
                gui.updateJHexNumberTextColor();
            }
            else {
                gui.setJHexNumber("");
                gui.setJBinaryNumber("");
            }
        }
    }
    public static void updateFromBinary() {

        String text = gui.getJBinaryNumber();
        System.out.println(text);
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
            gui.updateJBinaryNumberTextColor(); //This may not be nessecary
            if (!Objects.equals(text, "")){
                BigInteger value = new BigInteger(text,2);
                String hexValue = value.toString(16).toUpperCase();
                String decimalValue = value.toString(10);
                gui.updateJDecimalNumberTextColor();
                gui.updateJBinaryNumberTextColor();
                gui.updateJHexNumberTextColor();
                gui.setJDecimalNumber(decimalValue);
                gui.setJHexNumber(hexValue);
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
        int i = 0;
        try {
            if (tempArray[0] == '-'){
                System.out.println("negative number");
                i = 1;
            }
        } catch (ArrayIndexOutOfBoundsException e){
            //ignore;
        }

        for (; i<text.length();i++){
            if (isNotDigit(tempArray[i], 16)){
                foundMistake = true;
                break;
            }
        }
        if (foundMistake){
            gui.setJHexNumber(Color.red);
        }
        else{
            gui.setJHexNumber(textColor);
            if (!Objects.equals(text, "") && !Objects.equals(text, "-")){
                BigInteger value = new BigInteger(text,16);
                String binary = value.toString(2);
                if (twosCompliment){ //TODO: ADD THIS TO OTHER CONVERTERS
                    binary = findTwoscomplement(new StringBuffer(binary));
                }
                String decimalValue = value.toString(10);
                gui.setJDecimalNumber(decimalValue);
                gui.setJBinaryNumber(binary);
                gui.updateJDecimalNumberTextColor();
                gui.updateJBinaryNumberTextColor();
                gui.updateJHexNumberTextColor();
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
        if (radix == 64){ //Not sure this is right, and are we even doing base64?
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
                    //System.out.println("Char c (" + c + ") is within radix");
                    return false;
                }
            }
            System.out.println("Char c " + c + "is not within radix");
            return true;
        }
    }

    static String findTwoscomplement(StringBuffer str)
    {
        int n = str.length();

        // Traverse the string to get first '1' from
        // the last of string
        int i;
        for (i = n-1 ; i >= 0 ; i--)
            if (str.charAt(i) == '1')
                break;

        // If there exists no '1' concat 1 at the
        // starting of string
        if (i == -1)
            return "1" + str;

        // Continue traversal after the position of
        // first '1'
        for (int k = i-1 ; k >= 0; k--)
        {
            //Just flip the values
            if (str.charAt(k) == '1')
                str.replace(k, k+1, "0");
            else
                str.replace(k, k+1, "1");
        }

        // return the modified string
        return str.toString();
    }
}
