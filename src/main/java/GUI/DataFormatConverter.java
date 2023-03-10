package GUI;

import java.math.BigInteger;
import java.util.Objects;

public class DataFormatConverter {
    public void UpdateFromDecimal{
        Gui gui = Gui.getInstance();
        String text = gui.jdecimalNumber.getText();
        c = Character.toUpperCase(c);
        if (Character.isDigit(c)) {
            text = text + c;
        }
        if (!Objects.equals(text, "")){
            BigInteger value = new BigInteger(text);
            //long value = Long.parseLong(text);
            String hex = value.toString(16);
            //String hex = Long.toHexString(value);
            hex = hex.toUpperCase();
            String binary = value.toString(2);
            //String binary = Long.toBinaryString(value);
            jhexNumber.setText(hex);
            jbinaryNumber.setText(binary);
        }
        else {
            System.out.println("Value is null");
            jhexNumber.setText("");
            jbinaryNumber.setText("");
        }
    }
    }
}
