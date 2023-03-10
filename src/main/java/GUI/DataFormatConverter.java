package GUI;

import java.awt.*;
import java.math.BigInteger;
import java.util.Base64;
import java.util.Objects;
import java.util.Base64.Decoder;

public class DataFormatConverter {

    static Gui gui = Gui.getInstance();
    public void updateFromDecimal(char c){
        String text = gui.getJdecimalNumber();
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

    public static void updateFromBinary(char c){
        String text = gui.getJbinaryNumber();

        if (c == 8){
            text = gui.getJbinaryNumber();
            gui.setJbinaryNumber(Color.black);
        }
        else if (isdigit(c,2)) {
            text = text + c;
            gui.setJbinaryNumber(Color.black);
        }
        else {
            gui.setJbinaryNumber(Color.red);
        }
        if (!Objects.equals(text, "")){
            BigInteger value = new BigInteger(text,2);
            String valueString = value.toString(10);
            String hex = value.toString(16);
            hex = hex.toUpperCase();
            gui.setJhexNumber(hex);
            gui.setJdecimalNumber(valueString);
        }
        else {
            System.out.println("String is null");
            gui.setJdecimalNumber("");
            gui.setJhexNumber("");
        }
    }
    public void updateFromHex(char c){
        String text = gui.getJhexNumber();
        c = Character.toUpperCase(c);
        if (isdigit(c,16)) {
            text = text + c;
            text = text.toUpperCase();
        }
        if (!Objects.equals(text, "")){
            BigInteger value = new BigInteger(text,16);
            System.out.println("Hex value is: " + value);
            String binary = value.toString(2);
            String decimalvalue = value.toString(10);
            gui.setJdecimalNumber(decimalvalue);
            gui.setJbinaryNumber(binary);
        }
        else{
            System.out.println("String is null");
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
