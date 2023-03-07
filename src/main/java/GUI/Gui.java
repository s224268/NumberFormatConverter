package GUI;

import THINKYBITS.MathMachine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import java.util.concurrent.ThreadPoolExecutor;

public class Gui extends JFrame{

    protected JTextField jhexNumber;
    private JTextField jbinaryNumber;
    private JTextField jdecimalNumber;
    private JFrame hexConverter;
    private String hexNumber;
    private String intNumber;
    private String binaryNumber;
    private JLabel label;
    private CustomKeyListener customkeyListener;

    MathMachine mathMachine = new MathMachine();


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
        this.setResizable(false);

        jhexNumber = new JTextField("F");
        jhexNumber.setBounds((WIDTH-BOXWIDTH)/2-4,(12+BOXHEIGHT)*1, BOXWIDTH, BOXHEIGHT);

        jbinaryNumber = new JTextField("1111");
        jbinaryNumber.setBounds((WIDTH-BOXWIDTH)/2-4,(12+BOXHEIGHT)*2, BOXWIDTH, BOXHEIGHT);

        jdecimalNumber = new JTextField("15");
        jdecimalNumber.setBounds((WIDTH-BOXWIDTH)/2-4, (12+BOXHEIGHT)*3, BOXWIDTH, BOXHEIGHT);

        JLabel label = new JLabel("Jeg ved ikke hvad den her gør");
        label.setBounds((WIDTH-BOXWIDTH)/2, 20, BOXWIDTH, BOXHEIGHT);

        jdecimalNumber.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                jdecimalNumber.addKeyListener(DecimalKeyListener.getInstance());
            }
            public void focusLost(FocusEvent e) {
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


        //this.add(label);
        this.add(jhexNumber);
        this.add(jbinaryNumber);
        this.add(jdecimalNumber);
        this.setLayout(null);
        this.setVisible(true);

    }



    private void sendRequest() {
        System.out.println("This shouldnt do something");
    }
/*
    protected void updateFromDecimal() {
        String text = jdecimalNumber.getText();
        if (!Objects.equals(text, "")){
            String binary = mathMachine.getBinaryFromIntString(text);
            String hex = mathMachine.getHexFromIntString(text);
            jhexNumber.setText(hex);
            jbinaryNumber.setText(binary);
            System.out.println(hex);
            System.out.println(binary);
        }
        else {
            System.out.println("Value is null");
            jhexNumber.setText("");
            jbinaryNumber.setText("");
        }
    }

 */

    protected void updateFromBinary() {
        String text = jbinaryNumber.getText();
        if (!Objects.equals(text, "")){
            int value = Integer.parseInt(text,2);
            String valueString = Integer.toString(value);
            String hex = mathMachine.getHexFromIntString(text);
            jhexNumber.setText(hex);
            jdecimalNumber.setText(valueString);
            System.out.println("Im dying");
        }
        else {
            System.out.println("String is null");
            jhexNumber.setText("");
            jdecimalNumber.setText("");
        }

    }

    protected void updateFromHex() {
        String text = jhexNumber.getText();
        if (!Objects.equals(text, "")){
            int value = Integer.parseInt(text,16);
            System.out.println("Hex value is: " + value);
            String binary = Integer.toString(value,2);
            System.out.println("Hex value as binary is: " + binary);
            jdecimalNumber.setText(Integer.toString(value));
            jbinaryNumber.setText(binary);
        }
        else{
            System.out.println("String is null");
            jdecimalNumber.setText("");
            jbinaryNumber.setText("");
        }

    }

/*
    public void onFocus(JTextField textField){
        textField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals("Mail fra: ")) {
                    textField.setText("");
                }
            }
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText("Mail fra: ");
                }
            }
        });
    }*/


}
class HexKeyListener implements KeyListener {
    private static HexKeyListener hexKeyListener;

    @Override //These arent needed they just kinda need to be here
    public void keyPressed(KeyEvent e) {
        Gui gui = Gui.getInstance();
        System.out.println("Hex has been pressed");
        System.out.println("Active threads: " + Thread.activeCount());
        gui.updateFromHex();
        System.out.println("Current thread: " + Thread.currentThread());

    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
    public void keyTyped(KeyEvent e){
    }


    public static HexKeyListener getInstance(){
        if (hexKeyListener == null){
            hexKeyListener = new HexKeyListener();

        }
        return hexKeyListener;
    }
}
class DecimalKeyListener implements KeyListener {
    Gui gui = Gui.getInstance();
    private static DecimalKeyListener decimalKeyListener;

    public void keyTyped(KeyEvent e) {
        System.out.println("Decimal has been pressed");
    }

    @Override //These arent needed they just kinda need to be here
    public void keyPressed(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {

        }


    public static DecimalKeyListener getInstance(){
        if (decimalKeyListener == null){
            decimalKeyListener = new DecimalKeyListener();

        }
        return decimalKeyListener;
    }
}
class BinaryKeyListener implements KeyListener {
    Gui gui = Gui.getInstance();
    private static BinaryKeyListener binaryKeyListener;

    public void keyTyped(KeyEvent e){
        System.out.println("Binary has been pressed");
        gui.updateFromBinary();
    }

    @Override //These arent needed they just kinda need to be here
    public void keyPressed(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }


    public static BinaryKeyListener getInstance(){
        if (binaryKeyListener == null){
            binaryKeyListener = new BinaryKeyListener();

        }
        return binaryKeyListener;
    }
}