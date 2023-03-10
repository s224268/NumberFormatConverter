package GUI;

import Listeners.BinaryKeyListener;
import Listeners.DecimalKeyListener;
import Listeners.HexKeyListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigInteger;
import java.util.Objects;

public class Gui extends JFrame{

    public JTextField jhexNumber;
    private JTextField jbinaryNumber;
    private JTextField jdecimalNumber;



    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private int WIDTH = (int) (screenSize.getWidth()*0.3);
    private int HEIGHT = (int) (screenSize.getHeight()*0.4);
    private int BOXHEIGHT = (int) ((screenSize.getHeight())*0.05);
    private int BOXWIDTH = (int) (WIDTH*0.8);



    private static Gui gui;
    //Constructor


    public static Gui getInstance(){
        if (gui == null){
            gui = new Gui();
        }
        return gui;
    }


    public  void gui(){
        //this.smtp = smtp;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Anto's converter");
        this.setResizable(true);

        jhexNumber = new JTextField("F");
        jhexNumber.setBounds((WIDTH-BOXWIDTH)/2-4,(12+BOXHEIGHT)*1, BOXWIDTH, BOXHEIGHT);

        jbinaryNumber = new JTextField("1111");
        jbinaryNumber.setBounds((WIDTH-BOXWIDTH)/2-4,(12+BOXHEIGHT)*2, BOXWIDTH, BOXHEIGHT);

        jdecimalNumber = new JTextField("15");
        jdecimalNumber.setBounds((WIDTH-BOXWIDTH)/2-4, (12+BOXHEIGHT)*3, BOXWIDTH, BOXHEIGHT);

        JLabel label = new JLabel("Jeg ved ikke hvad den her gør");
        label.setBounds((WIDTH-BOXWIDTH)/2, 20, BOXWIDTH, BOXHEIGHT);

        jdecimalNumber.addFocusListener(new FocusListener() {
            public synchronized void focusGained(FocusEvent e) {
                jdecimalNumber.addKeyListener(DecimalKeyListener.getInstance());
            }
            public synchronized void focusLost(FocusEvent e) {
                System.out.println("Focus off decimal");
            }
        });
        jbinaryNumber.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                jbinaryNumber.addKeyListener(BinaryKeyListener.getInstance());
            }
            public void focusLost(FocusEvent e) {
                System.out.println("Focus off binary");
            }
        });
        jhexNumber.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                jhexNumber.addKeyListener(HexKeyListener.getInstance());
            }
            public void focusLost(FocusEvent e) {
                System.out.println("Focus off hex");
            }
        });

        this.add(jhexNumber);
        this.add(jbinaryNumber);
        this.add(jdecimalNumber);
        this.setLayout(null);
        this.setVisible(true);
    }

    public void updateFromDecimal(char c) {
        String text = jdecimalNumber.getText();
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
    public void updateFromBinary(char c){
        String text = jbinaryNumber.getText();
        c = Character.toUpperCase(c);
        jbinaryNumber.setForeground(new Color(0,0,0));
        if (c == '0' || c == '1') {
            text = text + c;
            jbinaryNumber.setForeground(new Color(0,0,0));
            //jbinaryNumber.setForeground(new Color(255,0,0));

        }
        else if (c == 8){
            jbinaryNumber.setForeground(new Color(0,0,0));
        }
        else {
            jbinaryNumber.setForeground(new Color(255,0,0));
        }
        if (!Objects.equals(text, "")){
            BigInteger value = new BigInteger(text,2);
            //long value = Long.parseLong(text,2);
            String valueString = value.toString(10);
            //String valueString = Long.toString(value);
            String hex = value.toString(16);
            //String hex = mathMachine.getHexFromIntString(text);
            hex = hex.toUpperCase();
            jhexNumber.setText(hex);
            jdecimalNumber.setText(valueString);
        }
        else {
            System.out.println("String is null");
            jhexNumber.setText("");
            jdecimalNumber.setText("");
        }

    }
    public void updateFromHex(char c) {
        String text = jhexNumber.getText();
        c = Character.toUpperCase(c);
        if (('A' == c || 'B' == c || 'C' == c || 'D' == c || 'E' == c || 'F' == c || Character.isDigit(c))) {
            text = text + c;
            text = text.toUpperCase();
        }
        if (!Objects.equals(text, "")){
            BigInteger value = new BigInteger(text,16);
            //long value = Long.parseLong(text,16);
            System.out.println("Hex value is: " + value);
            String binary = value.toString(2);
            //String binary = Long.toString(value,2);
            String decimalvalue = value.toString(10);
            jdecimalNumber.setText(decimalvalue);
            jbinaryNumber.setText(binary);
        }
        else{
            System.out.println("String is null");
            jdecimalNumber.setText("");
            jbinaryNumber.setText("");
        }

    }


}
