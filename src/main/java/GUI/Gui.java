package GUI;

import Listeners.BinaryKeyListener;
import Listeners.DecimalKeyListener;
import Listeners.HexKeyListener;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.*;
import java.math.BigInteger;
import java.util.Objects;

public class Gui extends JFrame{

    DataFormatConverter dataFormatConverter = new DataFormatConverter();

    private static JTextField jhexNumber;
    private static JTextField jbinaryNumber;
    private static JTextField jdecimalNumber;





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


    public void gui(){
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

    public String getJhexNumber() {
        return jhexNumber.getText();
    }

    public void setJhexNumber(String text) {
        jhexNumber.setText(text);
    }

    public String getJbinaryNumber() {
        return jbinaryNumber.getText();
    }

    public void setJbinaryNumber(String text) {
        jbinaryNumber.setText(text);
    }

    public String getJdecimalNumber() {
        if (jdecimalNumber == null) {
            return "";}
        else {
            return jdecimalNumber.getText();
        }

    }

    public void setJdecimalNumber(String text) {
        jdecimalNumber.setText(text);
    }

    public void updateFromDecimal(char c) {
        dataFormatConverter.updateFromDecimal(c);
        }

    public void updateFromBinary(char c){ //TODO MOVE CODE
        DataFormatConverter.updateFromBinary(c);
    }
    public void updateFromHex(char c) {
        dataFormatConverter.updateFromHex(c);
    }

    public void setJhexNumber(Color color){
        jhexNumber.setForeground(color);
    }
    public void setJbinaryNumber(Color color){
        jbinaryNumber.setForeground(color);
    }
    public void setJdecimalNumber(Color color){
        jdecimalNumber.setForeground(color);
    }
}
